package test.thejudemo.moviecatalogservice.entity;

import java.util.List;

public class CatalogRatings {
    private List<RatingDetails> ratingDetails;

    public CatalogRatings() {
    }

    public CatalogRatings(List<RatingDetails> ratingDetails) {
        this.ratingDetails = ratingDetails;
    }

    public List<RatingDetails> getRatingDetails() {
        return ratingDetails;
    }

    public void setRatingDetails(List<RatingDetails> ratingDetails) {
        this.ratingDetails = ratingDetails;
    }
}
