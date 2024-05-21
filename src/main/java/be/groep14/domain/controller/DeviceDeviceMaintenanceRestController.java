package be.groep14.domain.controller;

import be.groep14.domain.exception.ServiceException;
import be.groep14.domain.model.DeviceMaintenanceDto;
import be.groep14.domain.service.DeviceMaintenanceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

import static be.groep14.domain.util.ErrorCatcher.catchErrors;
import static be.groep14.domain.util.ToDto.toDeviceMaintenanceDto;
import static be.groep14.domain.util.ToDto.toDeviceMaintenanceDtoList;

@RestController
@RequestMapping("/api/device-deviceMaintenance")
public class DeviceDeviceMaintenanceRestController {
    @Autowired
    private DeviceMaintenanceService deviceMaintenanceService;

    @GetMapping("/{deviceId}/deviceMaintenances")
    public List<DeviceMaintenanceDto> getUserDevices(@PathVariable long deviceId) {
        return toDeviceMaintenanceDtoList(deviceMaintenanceService.findByDeviceId(deviceId));
    }

    @PostMapping("/{deviceId}/deviceMaintenances")
    public DeviceMaintenanceDto addMaintenanceToDevice(@PathVariable("deviceId") long deviceId, @Valid @RequestBody DeviceMaintenanceDto deviceMaintenanceDto) {
        return toDeviceMaintenanceDto(deviceMaintenanceService.createDeviceMaintenance(deviceMaintenanceDto, deviceId));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class, ServiceException.class, ResponseStatusException.class})
    public Map<String, String> handleValidationExceptions(Exception ex) {
        return catchErrors(ex);
    }
}
