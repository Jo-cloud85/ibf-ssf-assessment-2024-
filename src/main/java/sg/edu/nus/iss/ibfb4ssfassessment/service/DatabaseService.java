package sg.edu.nus.iss.ibfb4ssfassessment.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.ibfb4ssfassessment.model.Movie;
import sg.edu.nus.iss.ibfb4ssfassessment.repository.MovieRepository;

@Service
public class DatabaseService {

    @Autowired
    MovieRepository repo;

    // TODO: Task 2 (Save to Redis Map)
    @SuppressWarnings("null")
    public void saveRecord(Movie movie) throws IOException {
        repo.saveRecordToRedis(movie);
    }

    // // TODO: Task 3 (Map or List - comment where necesary)
    public long getNumberOfMovies() {
        return repo.getNumberOfMoviesFrRedis();
    }

    // public Movie getMovie(Integer index) {
    //     return repo.getMovie(index);
    // }

    // TODO: Task 4 (Map)
    public Movie getMovieById(Integer movieId) {
        return repo.getMovieFrRedis(movieId);
    }

    // TODO: Task 5
    public List<Movie> getAllMovies() {
        return repo.getAllMoviesFrRedis();
    }

    public void updateMovie(Movie movie) throws IOException {
        repo.updateMovieToRedis(movie);
    }
}
