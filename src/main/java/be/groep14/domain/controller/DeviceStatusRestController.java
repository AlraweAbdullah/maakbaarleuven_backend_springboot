package be.groep14.domain.controller;

import be.groep14.domain.exception.ServiceException;
import be.groep14.domain.model.DeviceStatus;
import be.groep14.domain.model.DeviceStatusDto;
import be.groep14.domain.service.DeviceStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

import static be.groep14.domain.util.ErrorCatcher.catchErrors;
import static be.groep14.domain.util.ToDto.toDeviceStatusDtoList;

@RestController
@RequestMapping("/api/deviceStatus")
public class DeviceStatusRestController {
    @Autowired
    private DeviceStatusService deviceStatusService;

    @GetMapping("")
    public Iterable<DeviceStatusDto> overview() {
        List<DeviceStatus> deviceStatus = deviceStatusService.getDeviceStatus();
        return toDeviceStatusDtoList(deviceStatus);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class, ServiceException.class, ResponseStatusException.class})
    public Map<String, String> handleValidationExceptions(Exception ex) {
        return catchErrors(ex);
    }
}
