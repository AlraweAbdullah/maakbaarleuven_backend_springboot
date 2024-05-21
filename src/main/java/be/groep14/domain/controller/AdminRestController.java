package be.groep14.domain.controller;

import be.groep14.domain.exception.ServiceException;
import be.groep14.domain.model.*;
import be.groep14.domain.service.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

import static be.groep14.domain.util.ErrorCatcher.catchErrors;
import static be.groep14.domain.util.ToDto.toAdminDto;
import static be.groep14.domain.util.ToDto.toAdminDtoList;

@CrossOrigin(origins = {"http://127.0.0.1:8080"}, methods = {RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.POST})
@RestController
@RequestMapping("/api/admin")
public class AdminRestController {
    @Autowired
    private AdminService adminService;

    @GetMapping("")
    public Iterable<AdminDto> overview() {
        List<Admin> admins = adminService.findAll();
        return toAdminDtoList(admins);
    }

    @GetMapping("/{id}")
    public AdminDto findById(@PathVariable("id") long id) {
        return toAdminDto(adminService.findById(id));
    }

    @PostMapping("/add")
    public AdminDto add(@Valid @RequestBody AdminDto adminDto) {
        return toAdminDto(adminService.create(adminDto));
    }

    @PutMapping("/update/{id}")
    public AdminDto put(@PathVariable("id") long id, @Valid @RequestBody AdminDto adminDto) {
        return toAdminDto(adminService.update(id, adminDto));
    }

    @DeleteMapping("/delete/{id}")
    public AdminDto delete(@PathVariable("id") long id) {
        return toAdminDto(adminService.delete(id));
    }


    @PostMapping("/login")
    public AdminDto login(@Valid @RequestBody AdminDto adminDto) {
        return toAdminDto(adminService.login(adminDto.getEmail(), adminDto.getPassword()));
    }

    @PutMapping("/password")
    public AdminDto changePassword(@Valid @RequestBody AdminDto adminDto) {
        return toAdminDto(adminService.changePassword(adminDto.getEmail(), adminDto.getPassword()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class, ServiceException.class, ResponseStatusException.class})
    public Map<String, String> handleValidationExceptions(Exception ex) {
        return catchErrors(ex);
    }

}
