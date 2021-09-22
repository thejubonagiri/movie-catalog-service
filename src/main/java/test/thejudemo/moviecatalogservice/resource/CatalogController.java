package test.thejudemo.moviecatalogservice.resource;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.utils.FallbackMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import test.thejudemo.moviecatalogservice.config.DBConfig;
import test.thejudemo.moviecatalogservice.entity.CatalogItem;
import test.thejudemo.moviecatalogservice.entity.CatalogRatings;
import test.thejudemo.moviecatalogservice.entity.MovieDetails;
import test.thejudemo.moviecatalogservice.services.MovieInfo;
import test.thejudemo.moviecatalogservice.services.UserRating;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
@EnableHystrix
@EnableHystrixDashboard
@EnableAutoConfiguration
@RefreshScope
public class CatalogController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    MovieInfo movieInfo;

    @Autowired
    UserRating userRating;

    @Value("${my.greeting}")
    private String greeting;

    @Value("${my.greetingDate}")
    private String greetingDate;

    @Value("${my.greetingList}")
    private List<String> greetingList;

        @Autowired
    public DBConfig dbConfig;


    @RequestMapping("/{userId}")
    //@HystrixCommand(fallbackMethod = "getFallbackMovieCatalogDetails")
    public List<CatalogItem> getmovieCatalogDetails(@PathVariable("userId") String userId) {


        //For each userId get the movieId and rate details
        CatalogRatings catalogRatings = userRating.getCatalogRatings(userId);

        /*
        List<RatingDetails> ratingDetailList = Arrays.asList(
                new RatingDetails(1234, 4),
                new RatingDetails(3456, 5)
        );*/




       /* for (RatingDetails getMovieRatings : ratingDetailList) {
            MovieDetails movieDetails =  restTemplate.getForObject("http://localhost:9092/movies/" + getMovieRatings.getMovieId(), MovieDetails.class);
            CatalogItem catalogItem = new CatalogItem(movieDetails.getMovieName(), movieDetails.getMovieId(), movieDetails.getMovieDescription(),getMovieRatings.getRating() );
            movieCatalogDetails.add(catalogItem) ;
        }
        return movieCatalogDetails;*/

        // Using Streams

//        List<CatalogItem> movieCatalogDetails = new ArrayList<>();
//
//        ratingDetailList.stream().forEach(getMovieRatings -> {
//            MovieDetails movieDetails =  restTemplate.getForObject("http://localhost:9092/movies/" + getMovieRatings.getMovieId(), MovieDetails.class);
//            CatalogItem catalogItem = new CatalogItem(movieDetails.getMovieName(), movieDetails.getMovieId(), movieDetails.getMovieDescription(),getMovieRatings.getRating() );
//            movieCatalogDetails.add(catalogItem) ;
//        });
//
//        return movieCatalogDetails;

        //for each Movie Id, get relevant details from Movie Data service
        return catalogRatings.getRatingDetails().stream().map(getMovieRatings ->
           movieInfo.getCatalogDetails(getMovieRatings)
        ).collect(Collectors.toList());

    }


    // API Call to test @Value, @Configuration & @ Environment
    @RequestMapping("/greetings")
    public String getGreetings ()
    {
        return greeting + greetingDate + greetingList + dbConfig.getAddress() + dbConfig.getHost() + " port: " + dbConfig.getPort();
    }

}

