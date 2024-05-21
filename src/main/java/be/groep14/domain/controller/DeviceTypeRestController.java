package be.groep14.domain.controller;

import be.groep14.domain.exception.ServiceException;
import be.groep14.domain.model.*;
import be.groep14.domain.service.DeviceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Map;
import static be.groep14.domain.util.ErrorCatcher.catchErrors;
import static be.groep14.domain.util.ToDto.toDeviceTypeDtoList;

@RestController
@RequestMapping("/api/deviceType")
public class DeviceTypeRestController {
    @Autowired
    private DeviceTypeService deviceTypeService;

    @GetMapping("")
    public Iterable<DeviceTypeDto> overview() {
        List<DeviceType> devicesTypes = deviceTypeService.getDeviceTypes();
        return toDeviceTypeDtoList(devicesTypes);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class, ServiceException.class, ResponseStatusException.class})
    public Map<String, String> handleValidationExceptions(Exception ex) {
        return catchErrors(ex);
    }
}
