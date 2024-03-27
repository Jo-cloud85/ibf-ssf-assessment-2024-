package sg.edu.nus.iss.ibfb4ssfassessment.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

public class Login {

    @NotEmpty(message = "Required field")
    @Email(message = "Comply to format<emailname>@<domain_name>")
    @Size(message = "Up to a maximum of 50 characters")
    private String email;

    @PastOrPresent(message="Birth date must be in the past.")
    @DateTimeFormat(pattern="yyyy-MM-dd")   
    private Date birthdate;

    public Login() {};

    public Login(String email, Date birthdate) {
        this.email = email;
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public String toString() {
        return "Login [email=" + email + ", birthdate=" + birthdate + "]";
    }
}
