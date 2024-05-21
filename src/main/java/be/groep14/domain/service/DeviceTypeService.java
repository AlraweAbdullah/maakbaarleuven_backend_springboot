package be.groep14.domain.service;

import be.groep14.domain.exception.ServiceException;
import be.groep14.domain.model.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DeviceTypeService {
    @Autowired
    private DeviceTypeRepository deviceTypeRepository;


    public List<DeviceType> getDeviceTypes() {
        return deviceTypeRepository.findAll();
    }


    public DeviceType createDeviceType(DeviceTypeDto dto) {
        return saveDeviceType(new DeviceType(), dto);
    }

    public DeviceType updateDeviceType(Long id, DeviceTypeDto dto) {
        return saveDeviceType(findById(id), dto);
    }

    public DeviceType findById(Long id) {
        return deviceTypeRepository.findById(id).orElseThrow(() -> new ServiceException("get", "Toestel type met id {" + id + "} is niet gevonden."));
    }

    public DeviceType findByName(String name) {
        return deviceTypeRepository.findByName(name).orElseThrow(() -> new ServiceException("get", "Toestel type met naam {" + name + "} is niet gevonden."));
    }

    public DeviceType deleteDeviceType(long id) {
        DeviceType deviceType = findById(id);
        deviceTypeRepository.delete(deviceType);
        return deviceType;
    }

    @PostConstruct
    public void seedDeviceTypes() {
        List<String> deviceTypeNames = Arrays.asList("Stofzuiger", "Wasmachine", "Strijkijzer");
        for (String typeName : deviceTypeNames) {
            if (deviceTypeRepository.findByName(typeName).isEmpty()) {
                DeviceType deviceType = new DeviceType();
                deviceType.setName(typeName);
                deviceTypeRepository.save(deviceType);
            }
        }
    }


    private DeviceType saveDeviceType(DeviceType deviceType, DeviceTypeDto dto) {
        try {
            deviceType.setDevices(dto.getDevices());
            deviceType.setName(dto.getName());
            return deviceTypeRepository.save(deviceType);

        } catch (DataIntegrityViolationException e) {
            throw new ServiceException("add", "Toestel type met naam :  {" + deviceType.getName() + "} bestaat al.");
        }
    }


}
