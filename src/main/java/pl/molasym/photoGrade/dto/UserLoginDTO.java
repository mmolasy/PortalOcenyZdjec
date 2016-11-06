package pl.molasym.photoGrade.dto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by moliq on 06.11.16.
 */

public class UserLoginDTO {
    @NotNull
    @Email
    private String email;
    @NotNull
    @Length(min = 6, message = "Min password length is 6 letters")
    private String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
