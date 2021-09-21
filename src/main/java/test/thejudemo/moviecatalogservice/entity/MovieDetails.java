package test.thejudemo.moviecatalogservice.entity;

public class MovieDetails {

    Integer movieId;
    String movieName;
    String movieDescription;

    public MovieDetails() {
    }

    public MovieDetails(Integer movieId, String movieName, String movieDescription) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.movieDescription = movieDescription;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieDescription() {
        return movieDescription;
    }

    public void setMovieDescription(String movieDescription) {
        this.movieDescription = movieDescription;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }
}
