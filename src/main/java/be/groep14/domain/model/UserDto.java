package be.groep14.domain.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

public class UserDto {
    private long id;
    @NotBlank()
    @Size(min = 3, message = "{user.name.not.valid}")
    private String name;

    @NotBlank()
    @Size(min = 3, message = "{user.lastname.not.valid}")
    private String lastname;

    @NotBlank()
    @Size(min = 5, message = "{user.street.not.valid}")
    private String street;

    @Size(min = 1, message = "{user.houseNr.not.valid}")
    private String houseNr;

    @NotNull
    private LocalDate birthdate;

    @NotBlank()
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Size(min = 5, message = "{user.password.not.valid}")
    private String password;

    @Email(message = "{user.email.not.valid}")

    private String email;

    private String persons;

    private String telephone;
    private List<Device> devices;

    public UserDto() { }

    public UserDto(long id, String name, String lastname, String email, List<Device> devices, LocalDate birthdate, String telephone, String persons, String houseNr, String street, String password) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.devices = devices;
        this.birthdate = birthdate;
        this.telephone = telephone;
        this.persons = persons;
        this.houseNr = houseNr;
        this.street = street;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNr() {
        return houseNr;
    }

    public void setHouseNr(String houseNr) {
        this.houseNr = houseNr;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
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

    public String getPersons() {
        return persons;
    }

    public void setPersons(String persons) {
        this.persons = persons;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }
}

