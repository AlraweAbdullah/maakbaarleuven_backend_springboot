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
public class DeviceStatusService {
    @Autowired
    private DeviceStatusRepository deviceStatusRepository;


    public List<DeviceStatus> getDeviceStatus() {
        return deviceStatusRepository.findAll();
    }

    public DeviceStatus createDeviceStatus(DeviceStatusDto dto) {
        return saveDeviceStatus(new DeviceStatus(), dto);
    }

    public DeviceStatus update(Long id, DeviceStatusDto dto) {

        return saveDeviceStatus(findById(id), dto);
    }


    public DeviceStatus findById(Long id) {
        return deviceStatusRepository.findById(id)
                .orElseThrow(() -> new ServiceException("get", "Toestel status met id {" + id + "} is niet gevonden."));
    }

    public DeviceStatus findByName(String name) {
        return deviceStatusRepository.findByName(name)
                .orElseThrow(() -> new ServiceException("get", "Toestel status met naam {" + name + "} is niet gevonden."));
    }

    public DeviceStatus deleteDeviceStatus(long id) {
        DeviceStatus deviceStatus = findById(id);
        deviceStatusRepository.delete(deviceStatus);
        return deviceStatus;
    }

    @PostConstruct
    public void seedDeviceStatus() {
        List<String> deviceStatusNames = Arrays.asList("Actief", "Niet actief", "Af staan", "Defect");
        for (String statusName : deviceStatusNames) {
            if (deviceStatusRepository.findByName(statusName).isEmpty()) {
                DeviceStatus deviceStatus = new DeviceStatus();
                deviceStatus.setName(statusName);
                deviceStatusRepository.save(deviceStatus);
            }
        }
    }


    private DeviceStatus saveDeviceStatus(DeviceStatus deviceStatus, DeviceStatusDto dto) {
        try {
            deviceStatus.setDevices(dto.getDevices());
            deviceStatus.setName(dto.getName());
            return deviceStatusRepository.save(deviceStatus);

        } catch (DataIntegrityViolationException e) {
            throw new ServiceException("add", "Toestel status met naam :  {" + deviceStatus.getName() + "} bestaat al");
        }
    }


}
