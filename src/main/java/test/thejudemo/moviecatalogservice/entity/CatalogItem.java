package test.thejudemo.moviecatalogservice.entity;

public class CatalogItem {

    public String movieName;
    public Integer movieId;
    public String movieDesc;
    public Integer rating;

    public CatalogItem() {
    }

    public CatalogItem(String movieName, Integer movieId, String movieDesc, Integer rating) {
        this.movieName = movieName;
        this.movieId = movieId;
        this.movieDesc = movieDesc;
        this.rating = rating;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public String getMovieDesc() {
        return movieDesc;
    }

    public void setMovieDesc(String movieDesc) {
        this.movieDesc = movieDesc;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
