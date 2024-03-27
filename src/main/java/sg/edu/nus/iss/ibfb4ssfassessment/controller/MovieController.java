package sg.edu.nus.iss.ibfb4ssfassessment.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.task.TaskExecutionProperties.Simple;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import sg.edu.nus.iss.ibfb4ssfassessment.model.Login;
import sg.edu.nus.iss.ibfb4ssfassessment.model.Movie;
import sg.edu.nus.iss.ibfb4ssfassessment.service.DatabaseService;
import sg.edu.nus.iss.ibfb4ssfassessment.util.Util;

@Controller
@RequestMapping
public class MovieController {

    @Autowired
    DatabaseService databaseService;

    // TODO: Task 8
    @GetMapping("/movies")
    public String displayMovies(
        HttpSession session,
        Model model) {
        
        if(session.getAttribute("login") != null) {
            List<Movie> movieList = databaseService.getAllMovies();
            model.addAttribute("movieList", movieList);
            return "view2";
        } else {
            return "refused";
        }
    }

    // TODO: Task 9
    @GetMapping("/movies/movie/book/{id}")
    public String bookMovie(
        HttpSession session,
        Model model,
        @PathVariable("id") String id) throws IOException  {

        Login login = (Login) session.getAttribute("login");

        Integer movieId = Integer.parseInt(id);

        Movie movieToBook = databaseService.getMovieById(movieId);

        if (session.getAttribute("login") != null) {
            int userAge = getAge(login.getBirthdate());
            String movieRating = movieToBook.getRated();
            if ((userAge < 13) || (userAge >= 13 && userAge < 18 && !movieRating.equals("PG-13")) || (userAge < 18 && movieRating.equals("R"))) {
                // underage - only pg-13
                model.addAttribute("showErrorMsg", Util.ERROR_MSG_2);
                return "BookError";
            } else {
                // can book all
                Integer count = movieToBook.getCount();
                movieToBook.setCount(count+1);
                databaseService.updateMovie(movieToBook);
                model.addAttribute("showMovieName", movieToBook.getTitle());
                return "BookSuccess";
            }
        } else {
            return "refused";
        }
    }

    // TODO: Task 9
    // Helper method
    public int getAge(Date dateOfBirth) {
        int age = 0;
        Calendar born = Calendar.getInstance();
        Calendar now = Calendar.getInstance();
        if(dateOfBirth!= null) {
            now.setTime(new Date());
            born.setTime(dateOfBirth);  
            if(born.after(now)) {
                throw new IllegalArgumentException("Can't be born in the future");
            }
            age = now.get(Calendar.YEAR) - born.get(Calendar.YEAR);             
            if(now.get(Calendar.DAY_OF_YEAR) < born.get(Calendar.DAY_OF_YEAR))  {
                age-=1;
            }
        }  
        return age;
    }
}
