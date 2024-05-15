package be.groep14.domain.controller;

import be.groep14.domain.exception.ServiceException;
import be.groep14.domain.model.Device;
import be.groep14.domain.model.DeviceDto;
import be.groep14.domain.service.DeviceService;
import be.groep14.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@RestController
@RequestMapping("/api/user-device")
public class UserDeviceRestController {
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private UserService userService;

    @PostMapping("/add/device/{deviceId}/to/user/{userId}")
    public DeviceDto add(@PathVariable("deviceId") long deviceId, @PathVariable("userId") long userId){
        Device device = deviceService.getDeviceById(deviceId);
        return  toDto(userService.addDeviceToUser(userId, device));
    }

    @GetMapping("/user/{userId}/devices")
    public Set<DeviceDto> getUserDevices(@PathVariable long userId){
        Set<Device> userDevices = userService.getDevices(userId);
        return toDto(userDevices);
    }

    @PostMapping("/remove/device/{deviceId}/from/user/{userId}")
    public DeviceDto removeDeviceFromUser(@PathVariable("deviceId") long deviceId, @PathVariable("userId") long userId){
        Device device = deviceService.getDeviceById(deviceId);
        return  toDto(userService.removeDeviceFromUser(userId, device));
    }


    public static DeviceDto toDto(Device device) {
        DeviceDto dto = new DeviceDto();

        dto.setId(device.getId());
        dto.setSerial(device.getSerial());
        if(device.getUser() != null){
            dto.setUserEmail(device.getUser().getEmail());
        }
        return dto;
    }


    private Set<DeviceDto> toDto(Set<Device> devices) {
        Set<DeviceDto> deviceDtoList = new HashSet<>();

        for (Device device : devices) {
            deviceDtoList.add(toDto(device));
        }
        return deviceDtoList;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class, ServiceException.class, ResponseStatusException.class})
    public Map<String, String> handleValidationExceptions(Exception ex) {
        Map<String, String> errors = new HashMap<>();
        if (ex instanceof MethodArgumentNotValidException) {
            ((MethodArgumentNotValidException)ex).getBindingResult().getAllErrors().forEach((error) -> {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                errors.put(fieldName, errorMessage);
            });
        }
        else if (ex instanceof ServiceException) {
            errors.put(((ServiceException) ex).getAction(), ex.getMessage());
        }
        else {
            errors.put(((ResponseStatusException) ex).getReason(), ex.getCause().getMessage());
        }
        return errors;
    }
}
