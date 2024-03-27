package sg.edu.nus.iss.ibfb4ssfassessment.repository;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import sg.edu.nus.iss.ibfb4ssfassessment.model.Movie;
import sg.edu.nus.iss.ibfb4ssfassessment.service.FileService;
import sg.edu.nus.iss.ibfb4ssfassessment.util.Util;

@Repository
public class MovieRepository {

    @Autowired
    @Qualifier(Util.KEY_MOVIES)
    RedisTemplate<String, String> template;

    HashOperations<String, String, String> hashOps;

    @Autowired
    FileService fileService;

    // TODO: Task 2 (Save to Redis Map)
    @SuppressWarnings("null")
    public void saveRecordToRedis(Movie movie) throws IOException {
        hashOps = template.opsForHash();
        String movieJsonStr = convertMovieToJsonStr(movie);
        hashOps.putIfAbsent(Util.KEY_MOVIES, movieJsonStr, movieJsonStr);
    }

    // // TODO: Task 3 (Map or List - comment where necesary)
    public long getNumberOfMoviesFrRedis() {
        hashOps = template.opsForHash();
        return (long) hashOps.size(Util.KEY_MOVIES);
    }

    public Movie getMovieFrRedis(Integer movieId) {
        hashOps = template.opsForHash();
        Map<String, String> movieMap = hashOps.entries(Util.KEY_MOVIES);
    
        Movie singleMovie = movieMap.values().stream()
                                        .map(jsonStr -> fileService.convertJsonStrToMovie(jsonStr))
                                        .filter(movie -> movie.getId().equals(movieId))
                                        .findFirst()
                                        .get();
        return singleMovie;
    }

    public List<Movie> getAllMoviesFrRedis() {
        hashOps = template.opsForHash();
        List<Movie> movieList = new LinkedList<>();
        Map<String, String> movieMap = hashOps.entries(Util.KEY_MOVIES);

        for (Map.Entry<String, String> entry : movieMap.entrySet()) {
            String jsonObjStr = entry.getValue(); // each Json str
            Movie movie = fileService.convertJsonStrToMovie(jsonObjStr);
            movieList.add(movie);
        }
        return movieList;
    }

     @SuppressWarnings("null")
    public void updateMovieToRedis(Movie movie) throws IOException {
        hashOps = template.opsForHash();
        String jsonStr = convertMovieToJsonStr(movie);
        hashOps.put(Util.KEY_MOVIES, movie.getId().toString(), jsonStr);
     }

    // Helper method
    // Convert each Movie object to a Json string for Redis
    // Here, time gets stored as epochmilliseconds
    public String convertMovieToJsonStr(Movie movie) throws IOException {
        JsonObject jsonObject = Json.createObjectBuilder()
            .add("Id", movie.getId())
            .add("Title", movie.getTitle())
            .add("Year", movie.getYear())
            .add("Rated", movie.getRated())
            .add("Released", movie.getReleased().getTime())//converting to epoch for saving to redis
            .add("Runtime", movie.getRuntime())
            .add("Genre", movie.getGenre())
            .add("Director", movie.getDirector())
            .add("Rating", movie.getRating())
            .add("Count", movie.getCount())
            .build();

        String movieJsonStr;

        try(Writer writer = new StringWriter()) {
            Json.createWriter(writer).write(jsonObject);
            movieJsonStr = writer.toString();
        }

        return movieJsonStr;
    }
}
