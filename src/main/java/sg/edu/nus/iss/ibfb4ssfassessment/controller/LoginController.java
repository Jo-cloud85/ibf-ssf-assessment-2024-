package sg.edu.nus.iss.ibfb4ssfassessment.controller;

import java.text.SimpleDateFormat;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import sg.edu.nus.iss.ibfb4ssfassessment.model.Login;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class LoginController {

    // // TODO: Task 6
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
        BindingResult result) {

        if (result.hasErrors()) {
            return "view0";
        } 
            
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
