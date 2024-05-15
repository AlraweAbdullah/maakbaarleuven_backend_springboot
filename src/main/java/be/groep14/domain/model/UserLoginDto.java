package be.groep14.domain.model;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Set;

public class UserLoginDto {
    @NotBlank()
    @Size(min = 5, message = "user.password.not.valid")
    private String password;


    @NotBlank()
    @Email
    @Size(min = 5, message = "user.email.not.valid")
    private String email;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserLoginDto(){}




}


