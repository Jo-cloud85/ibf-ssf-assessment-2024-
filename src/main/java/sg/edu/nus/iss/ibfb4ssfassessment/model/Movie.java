package sg.edu.nus.iss.ibfb4ssfassessment.model;

import java.util.Date;

public class Movie {
    private Integer id;
    private String title;
    private String year;
    private String rated;
    private Date released;
    private String runtime;
    private String genre;
    private String director;
    private Double rating;
    private Integer count;

    public Movie() {}

    public Movie(Integer id, String title, String year, String rated, Date released, String runtime, String genre,
            String director, Double rating, Integer count) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.rated = rated;
        this.released = released;
        this.runtime = runtime;
        this.genre = genre;
        this.director = director;
        this.rating = rating;
        this.count = count;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRated() {
        return rated;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    public Date getReleased() {
        return released;
    }

    public void setReleased(Date released) {
        this.released = released;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Movie [id=" + id + ", title=" + title + ", year=" + year + ", rated=" + rated + ", released=" + released
                + ", runtime=" + runtime + ", genre=" + genre + ", director=" + director + ", rating=" + rating
                + ", count=" + count + "]";
    }
}
