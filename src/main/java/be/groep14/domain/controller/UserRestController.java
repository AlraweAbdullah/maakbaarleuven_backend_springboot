package be.groep14.domain.controller;

import be.groep14.domain.exception.ServiceException;
import be.groep14.domain.model.*;
import be.groep14.domain.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

import static be.groep14.domain.util.ErrorCatcher.catchErrors;
import static be.groep14.domain.util.ToDto.toUserDto;
import static be.groep14.domain.util.ToDto.toUserDtoList;

@CrossOrigin(origins = {"http://127.0.0.1:8080"}, methods = {RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.POST})
@RestController
@RequestMapping("/api/user")
public class UserRestController {
    @Autowired
    private UserService userService;
    @GetMapping("")
    public Iterable<UserDto> overview() {
        List<User> users = userService.findAll();
        return toUserDtoList(users);
    }
    @GetMapping("/{id}")
    public UserDto findById(@PathVariable("id") long id) {
        return toUserDto(userService.findById(id));
    }

    @PostMapping("/add")
    public UserDto add(@Valid @RequestBody UserDto userDto) {
        return toUserDto(userService.create(userDto));
    }

    @DeleteMapping("/delete/{id}")
    public UserDto deleteById(@PathVariable("id") long id) {
        return toUserDto(userService.delete(id));
    }

    @PutMapping("/update/{id}")
    public UserDto put(@PathVariable("id") long id, @Valid @RequestBody UserDto userDto) {
        return toUserDto(userService.update(id, userDto));
    }


    @PostMapping("/login")
    public UserDto login(@Valid @RequestBody UserLoginDto userLoginDto) {
        return toUserDto(userService.login(userLoginDto.getEmail(), userLoginDto.getPassword()));
    }

    @PutMapping("/password")
    public UserDto changePassword(@Valid @RequestBody UserLoginDto userLoginDto) {
        return toUserDto(userService.changePassword(userLoginDto.getEmail(), userLoginDto.getPassword()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class, ServiceException.class, ResponseStatusException.class})
    public Map<String, String> handleValidationExceptions(Exception ex) {
        return catchErrors(ex);
    }
}
