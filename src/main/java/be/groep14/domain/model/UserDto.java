package be.groep14.domain.model;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserDto {
    private long id;
    @NotBlank()
    @Size(min = 5, message = "user.name.not.valid")
    private String name;

    @NotBlank()
    @Size(min = 5, message = "user.lastname.not.valid")
    private String lastname;


    @NotBlank()
    @Size(min = 5, message = "user.password.not.valid")
    private String password;


    @NotBlank()
    @Email
    @Size(min = 5, message = "user.email.not.valid")
    private String email;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

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

    public UserDto(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }



}


