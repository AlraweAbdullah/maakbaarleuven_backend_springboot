package be.groep14.domain.controller;

import be.groep14.domain.exception.ServiceException;
import be.groep14.domain.model.DeviceDto;
import be.groep14.domain.model.User;
import be.groep14.domain.model.UserDto;
import be.groep14.domain.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserRestController {

    @Autowired
    private UserService service;

    private static Logger LOGGER = LoggerFactory.getLogger(UserRestController.class);

    @GetMapping("")
    public Iterable<UserDto> overview() {
        List<User> users = service.findAll();
        if (users.isEmpty()) {
            createSampleData();
            users = service.findAll();
        }
        return toDto(users);
    }

    @GetMapping("/{id}")
    public UserDto getDeviceById(@PathVariable("id") long id) {
        return toDto(service.findById(id));
    }

    @PostMapping("/login")
    public UserDto login(@RequestParam("email") String email, @RequestParam("password") String password) {
        return toDto(service.login(email, password));
    }

    @PostMapping("/changePassword")
    public UserDto changePassword(@RequestParam("email") String email, @RequestParam("password") String password) {
        return toDto(service.changePassword(email, password));
    }

    @PostMapping("/add")
    public UserDto add(@Valid @RequestBody UserDto userDto) {
        return toDto(service.create(userDto));
    }


    @DeleteMapping("/delete/{id}")
    public UserDto deleteById(@PathVariable("id") long id) {
        return toDto(service.delete(id));
    }

    @PutMapping("/update/{id}")
    public UserDto put(@PathVariable("id") long id, @Valid @RequestBody UserDto userDto) {
        return toDto(service.update(id, userDto));
    }


    private void createSampleData() {
        UserDto userDto1 = new UserDto();
        userDto1.setEmail("abdullah@gmail.com");
        userDto1.setName("Abdullah");
        userDto1.setLastname("Alrawe");
        userDto1.setPassword("12345678");

        try {
            service.create(userDto1);

        } catch (Exception e) {
            LOGGER.error("Error adding sample date");
            LOGGER.error(e.getMessage());
        }

    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class, ServiceException.class, ResponseStatusException.class})
    public Map<String, String> handleValidationExceptions(Exception ex) {
        Map<String, String> errors = new HashMap<>();
        if (ex instanceof MethodArgumentNotValidException) {
            ((MethodArgumentNotValidException) ex).getBindingResult().getAllErrors().forEach((error) -> {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                errors.put(fieldName, errorMessage);
            });
        } else if (ex instanceof ServiceException) {
            errors.put(((ServiceException) ex).getAction(), ex.getMessage());
        } else {
            errors.put(((ResponseStatusException) ex).getReason(), ex.getCause().getMessage());
        }
        return errors;
    }

    private UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setLastname(user.getLastname());
        dto.setPassword(user.getPassword());
        dto.setEmail(user.getEmail());
        return dto;

    }


    private List<UserDto> toDto(List<User> users) {
        List<UserDto> userDtoList = new ArrayList<>();

        for (User user : users) {
            userDtoList.add(toDto(user));
        }
        return userDtoList;
    }

}
