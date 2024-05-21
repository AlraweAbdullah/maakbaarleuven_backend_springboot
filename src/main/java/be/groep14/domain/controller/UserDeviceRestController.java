package be.groep14.domain.controller;

import be.groep14.domain.exception.ServiceException;
import be.groep14.domain.model.*;
import be.groep14.domain.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static be.groep14.domain.util.ErrorCatcher.catchErrors;
import static be.groep14.domain.util.ToDto.toDeviceDtoList;

@RestController
@RequestMapping("/api/user-device")
public class UserDeviceRestController {
    @Autowired
    private DeviceService deviceService;

    @GetMapping("/{userId}/devices")
    public List<DeviceDto> addMaintenanceToDevice(@PathVariable("userId") long userId) {
        return toDeviceDtoList(deviceService.findByUserId(userId));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class, ServiceException.class, ResponseStatusException.class})
    public Map<String, String> handleValidationExceptions(Exception ex) {
        return catchErrors(ex);
    }

}
