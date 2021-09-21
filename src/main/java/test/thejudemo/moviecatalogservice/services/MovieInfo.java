package test.thejudemo.moviecatalogservice.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import test.thejudemo.moviecatalogservice.entity.CatalogItem;
import test.thejudemo.moviecatalogservice.entity.MovieDetails;
import test.thejudemo.moviecatalogservice.entity.RatingDetails;

@Service
public class MovieInfo {

    @Autowired
    public RestTemplate restTemplate;

    @Autowired
    UserRating userRatings;

    @HystrixCommand(fallbackMethod =  "getFallBackCatalogDetails")
    public CatalogItem getCatalogDetails(RatingDetails ratingDetails)
    {
        MovieDetails movieDetails =  restTemplate.getForObject("http://movie-data-service/movies/" + ratingDetails.getMovieId(), MovieDetails.class);
        return new CatalogItem(movieDetails.getMovieName(), movieDetails.getMovieId(), movieDetails.getMovieDescription(),ratingDetails.getRating() );
    }

    public CatalogItem getFallBackCatalogDetails(RatingDetails ratingDetails)
    {
        return new CatalogItem("Movie Not Found" , 0 , "Movie description not found", ratingDetails.getRating() );
    }
}
