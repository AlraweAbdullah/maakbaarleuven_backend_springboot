package be.groep14.domain.controller;

import be.groep14.domain.exception.ServiceException;
import be.groep14.domain.model.*;
import be.groep14.domain.service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

import static be.groep14.domain.util.ErrorCatcher.catchErrors;
import static be.groep14.domain.util.ToDto.toDeviceDto;
import static be.groep14.domain.util.ToDto.toDeviceDtoList;

@RestController
@RequestMapping("/api/device")
public class DeviceRestController {
    @Autowired
    private DeviceService deviceService;

    @GetMapping("")
    public Iterable<DeviceDto> overview() {
        List<Device> devices = deviceService.getDevices();
        if (!devices.isEmpty()) {
            devices = deviceService.getDevices();
        }
        return toDeviceDtoList(devices);
    }

    @GetMapping("/{id}")
    public DeviceDto findById(@PathVariable("id") long id) {
        return toDeviceDto(deviceService.findById(id));
    }

    @PostMapping("")
    public DeviceDto add(@Valid @RequestBody DeviceDto deviceDto) {
        return toDeviceDto(deviceService.create(deviceDto));
    }

    @DeleteMapping("/delete/{id}")
    public DeviceDto delete(@PathVariable("id") long id) {
        return toDeviceDto(deviceService.delete(id));
    }

    @PutMapping("/update/{id}")
    public DeviceDto put(@PathVariable("id") long id, @Valid @RequestBody DeviceDto deviceDto) {
        return toDeviceDto(deviceService.update(id, deviceDto));
    }

    @PutMapping("/{id}/status")
    public DeviceDto put(@PathVariable("id") long id, @RequestBody Map<String, String> requestData) {
        return toDeviceDto(deviceService.updateDeviceStatus(id, requestData.get("status")));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class, ServiceException.class, ResponseStatusException.class})
    public Map<String, String> handleValidationExceptions(Exception ex) {
        return catchErrors(ex);
    }
}
