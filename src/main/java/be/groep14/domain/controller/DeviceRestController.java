package be.groep14.domain.controller;

import be.groep14.domain.exception.ServiceException;
import be.groep14.domain.model.Device;
import be.groep14.domain.model.DeviceDto;
import be.groep14.domain.model.DeviceType;
import be.groep14.domain.model.DeviceTypeDto;
import be.groep14.domain.service.DeviceService;
import be.groep14.domain.service.DeviceTypeService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@RestController
@RequestMapping("/api/device")
public class DeviceRestController {

    @Autowired
    private DeviceService deviceService;



    private static Logger LOGGER = LoggerFactory.getLogger(DeviceRestController.class);

    @GetMapping("")
    public Iterable<DeviceDto> overview() {
        List<Device> devices = deviceService.getDevices();
        if (devices.isEmpty()) {
            createSampleData();
            devices = deviceService.getDevices();
        }
        return toDto(devices);
    }

    @GetMapping("/{id}")
    public DeviceDto getDeviceById(@PathVariable("id") long id) {
        return toDto(deviceService.getDeviceById(id));
    }

    @PostMapping("/add")
    public DeviceDto add(@Valid @RequestBody DeviceDto deviceDto) {
        return toDto(deviceService.createDevice(deviceDto));
    }


    @DeleteMapping("/delete/{id}")
    public DeviceDto deleteById(@PathVariable("id") long id) {
        return toDto(deviceService.deleteDevice(id));
    }

    @PutMapping("/update/{id}")
    public DeviceDto put(@PathVariable("id") long id, @Valid @RequestBody DeviceDto deviceDto) {
        return toDto(deviceService.updateDevice(id, deviceDto));
    }


    private void createSampleData() {
        DeviceDto deviceDto1 = new DeviceDto();
        DeviceType deviceType = new DeviceType();
        deviceType.setName("Stofzuiger");
        deviceDto1.setSerial("device1");
        deviceDto1.setDeviceType(deviceType.getName());


        DeviceDto deviceDto2 = new DeviceDto();
        DeviceType deviceType2 = new DeviceType();
        deviceType2.setName("Stofzuiger");
        deviceDto2.setSerial("device1");
        deviceDto2.setDeviceType(deviceType2.getName());


        try {
            deviceService.createDevice(deviceDto1);
            deviceService.createDevice(deviceDto2);

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

    private DeviceDto toDto(Device device) {
        DeviceDto dto = new DeviceDto();
        dto.setId(device.getId());
        dto.setSerial(device.getSerial());
        //dto.setUserEmail(device.getUser().getEmail());
        dto.setDeviceType(device.getDeviceType().getName());
        return dto;
    }


    private List<DeviceDto> toDto(List<Device> devices) {
        List<DeviceDto> deviceDtoList = new ArrayList<>();

        for (Device device : devices) {
            deviceDtoList.add(toDto(device));
        }
        return deviceDtoList;
    }

}
