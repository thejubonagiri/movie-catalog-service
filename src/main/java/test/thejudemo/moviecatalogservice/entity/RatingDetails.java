package test.thejudemo.moviecatalogservice.entity;

public class RatingDetails {

    private Integer movieId;
    private Integer rating;

    public RatingDetails() {
    }

    public RatingDetails(Integer movieId, Integer rating) {
        this.movieId = movieId;
        this.rating = rating;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer  movieId) {
        this.movieId = movieId;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
