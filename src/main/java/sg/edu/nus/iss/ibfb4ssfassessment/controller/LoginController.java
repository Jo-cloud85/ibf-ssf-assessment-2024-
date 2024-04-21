package sg.edu.nus.iss.ibfb4ssfassessment.controller;

import java.text.ParseException;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import sg.edu.nus.iss.ibfb4ssfassessment.model.Login;

@Controller
@RequestMapping
public class LoginController {

    // TODO: Task 6
    @GetMapping
    public String login(Model model) {
        Login login = new Login();
        model.addAttribute("newLogin", login);
        return "view0";
    }

    // TODO: Task 7
    @PostMapping
    public String processlogin(
        HttpSession session,
        @ModelAttribute("newLogin") @Valid Login login,
        BindingResult result) throws ParseException {

        // if (login.getBirthdate().equals(new Date())) {
            
        //     FieldError err = new FieldError("newLogin", "birthdateStr", "Birthday cannot be a current date");
        //     result.addError(err);
        //     return "view0";
        // }

        Date currentDate = new Date();

        if (login.getBirthdate().equals(currentDate)) {
            FieldError err = new FieldError("newLogin", "birthdateStr", "Birthday cannot be a current date");
            result.addError(err);
        } else if (login.getBirthdate().after(currentDate)) {
            FieldError err = new FieldError("newLogin", "birthdateStr", "Birthday cannot be in the future");
            result.addError(err);
        }

        if (result.hasErrors()) {
            return "view0";
        } 
        // System.out.println(login.getBirthdate());
        // System.out.println(login.getBirthdateStr());
        session.setAttribute("login", login);
        return "view1";
    }

    // For the logout button shown on View 2
    // On logout, session should be cleared
    @GetMapping("/movies/logout")
    public String logout(HttpSession session) {
        // if (session.getAttribute("login") != null) {
            session.invalidate();
            return "redirect:/";
        // } else {
        //     return "refused";
        // }
    }
}
