package sg.edu.nus.iss.ibfb4ssfassessment.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import sg.edu.nus.iss.ibfb4ssfassessment.model.Movie;
import sg.edu.nus.iss.ibfb4ssfassessment.util.Util;

@Service
public record FileService() {

    // Reading from movies.json file and create a giant Json string
    public String parseFile(){
        StringBuilder sb = new StringBuilder();
        File file = new File(Util.FILE_PATH);

        if(file.exists()){
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }                    
            } catch (IOException e) {
                System.out.println("IOException: " + e.getMessage());
            }
        } else {
            System.out.println("file not exist: " + Util.FILE_PATH);
        } 

        return sb.toString();
    }

    // TODO: Task 1
    public List<Movie> readFile(String fileName) {
        String jsonStr = parseFile();
        JsonArray jsonArray = Json.createReader(new StringReader(jsonStr)).readArray();

        List<Movie> movieList = new LinkedList<>();

        for (int i=0; i<jsonArray.size(); i++) {
            JsonObject jsonObject = jsonArray.get(i).asJsonObject();
            Movie movie = convertJsonObjToMovie(jsonObject);
            movieList.add(movie);
        }
        return movieList;
    }

    // Helper method
    public Movie convertJsonStrToMovie(String jsonStr) {
        JsonReader reader = Json.createReader(new StringReader(jsonStr));
        JsonObject jsonObject = reader.readObject();
        return convertJsonObjToMovie(jsonObject);
    }
    
    // Helper method
    // Dates in the original Json object string from movies are in epoch
    private Movie convertJsonObjToMovie(JsonObject jsonObject) {
        Movie movie = new Movie();

        movie.setMovieId(jsonObject.getInt("Id"));
        movie.setTitle(jsonObject.getString("Title"));
        movie.setYear(jsonObject.getString("Year"));
        movie.setRated(jsonObject.getString("Rated"));
        Long releasedDate = jsonObject.getJsonNumber("Released").longValue();
        movie.setReleaseDate(releasedDate);
        movie.setRunTime(jsonObject.getString("Runtime"));
        movie.setGenre(jsonObject.getString("Genre"));
        movie.setDirector(jsonObject.getString("Director"));
        movie.setRating(jsonObject.getJsonNumber("Rating").doubleValue());
        Date formattedReleasedDate = new Date(releasedDate);
        movie.setFormattedReleaseDate(formattedReleasedDate);
        movie.setCount(jsonObject.getInt("Count"));

        return movie;
    }

}
