package be.groep14.domain.service;

import be.groep14.domain.exception.ServiceException;
import be.groep14.domain.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceTypeService {
    @Autowired
    private DeviceTypeRepository deviceTypeRepository;

    public List<DeviceType> getDeviceTypes() {
        return deviceTypeRepository.findAll();
    }


    public DeviceType createDeviceTypes(DeviceTypeDto dto) {
        return saveDeviceType(new DeviceType(), dto);
    }

    public DeviceType updateDeviceType(Long id, DeviceTypeDto dto) {
        return saveDeviceType(getDeviceTypeById(id), dto);
    }

    public DeviceType getDeviceTypeById(Long id) {
        return deviceTypeRepository.findById(id)
                .orElseThrow(() -> new ServiceException("get", "Toestel type met id {" + id + "} is niet gevonden."));
    }

    public DeviceType deleteDeviceType(long id) {
        DeviceType deviceType = getDeviceTypeById(id);
        deviceTypeRepository.delete(deviceType);
        return deviceType;
    }


    private DeviceType saveDeviceType(DeviceType deviceType, DeviceTypeDto dto) {
        try {
            deviceType.setDevices(dto.getDevices());
            deviceType.setName(dto.getName());
            return deviceTypeRepository.save(deviceType);

        } catch (DataIntegrityViolationException e) {
            throw new ServiceException("add", "Toestel type met name :  {" + deviceType.getName() + "} bestaat al.");
        }
    }


}
