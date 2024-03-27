package sg.edu.nus.iss.ibfb4ssfassessment.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Login {

    @NotEmpty(message = "Required field")
    @Email(message = "Comply to format<emailname>@<domain_name>")
    @Size(message = "Up to a maximum of 50 characters")
    private String email;

    @PastOrPresent(message="Birth date must be in the past.")
    @DateTimeFormat(pattern="yyyy-MM-dd")   
    private Date birthdate;

    @Pattern(regexp="((?:0[0-9])|(?:[1-2][0-9])|(?:3[0-1]))\\/((?:0[1-9])|(?:1[0-2]))\\/(\\d{4})", message="Format must be in dd/MM/yyyy.\nBirthday cannot be a current or future date")
    private String birthdateStr;

    public Login() {};

    public Login(String email, Date birthdate, String birthdateStr) {
        this.email = email;
        this.birthdate = birthdate;
        this.birthdateStr = birthdateStr;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthdate() throws ParseException { //from string
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date birthdate = sdf.parse(birthdateStr);
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getBirthdateStr() {
        return birthdateStr;
    }

    public void setBirthdateStr(String birthdateStr) {
        this.birthdateStr = birthdateStr;
    }

    @Override
    public String toString() {
        return "Login [email=" + email + ", birthdate=" + birthdate + "]";
    }

}
