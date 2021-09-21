package test.thejudemo.moviecatalogservice.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import test.thejudemo.moviecatalogservice.entity.CatalogRatings;
import test.thejudemo.moviecatalogservice.entity.RatingDetails;

import java.util.Arrays;

@Service
public class UserRating {

    @Autowired
    public RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallBackCatalogRatings")
    public CatalogRatings getCatalogRatings (String userId)
    {
        CatalogRatings catalogRatings = new CatalogRatings();
        catalogRatings = restTemplate.getForObject("http://rating-info-service/ratings/users/" + userId , CatalogRatings.class);
        return catalogRatings;
    }

    public CatalogRatings getFallBackCatalogRatings (String userId)
    {
        CatalogRatings catalogRatings = new CatalogRatings();
        catalogRatings.setRatingDetails(Arrays.asList(new RatingDetails(0,0)));
        return catalogRatings;
    }
}
