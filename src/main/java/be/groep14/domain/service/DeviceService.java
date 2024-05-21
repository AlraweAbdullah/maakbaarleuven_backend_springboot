package be.groep14.domain.service;

import be.groep14.domain.exception.ServiceException;

import be.groep14.domain.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceService {
    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private DeviceTypeService deviceTypeService;

    @Autowired
    private DeviceStatusService deviceStatusService;


    @Autowired
    private UserService userService;

    public List<Device> getDevices() {
        return deviceRepository.findAll();
    }


    public Device create(DeviceDto deviceDto) {
        User user = userService.findById(deviceDto.getUserId());
        DeviceType deviceType = deviceTypeService.findByName(deviceDto.getDeviceType());
        DeviceStatus deviceStatus = deviceStatusService.findByName(deviceDto.getDeviceStatus());
        return saveDevice(new Device(), deviceDto, user, deviceType, deviceStatus);
    }

    public Device update(Long id, DeviceDto deviceDto) {
        User user = userService.findById(deviceDto.getUserId());
        DeviceType deviceType = deviceTypeService.findByName(deviceDto.getDeviceType());
        DeviceStatus deviceStatus = deviceStatusService.findByName(deviceDto.getDeviceStatus());
        return saveDevice(findById(id), deviceDto, user, deviceType, deviceStatus);
    }

    public Device findById(Long id) {
        return deviceRepository.findById(id)
                .orElseThrow(() -> new ServiceException("get", "Toestel met id {" + id + "} is niet gevonden."));
    }

    public Device delete(long id) {
        Device device = findById(id);
        deviceRepository.delete(device);
        return device;
    }


    private Device saveDevice(Device device, DeviceDto dto, User user, DeviceType deviceType, DeviceStatus deviceStatus) {
        try {
            device.setSerial(dto.getSerial());
            device.setUser(user);
            device.setDeviceType(deviceType);
            device.setDeviceStatus(deviceStatus);
            device.setMark(dto.getMark());
            return deviceRepository.save(device);
        } catch (DataIntegrityViolationException e) {
            throw new ServiceException("add", "Toestel met serieel [" + device.getSerial() + "] bestaat al");
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new ServiceException("add", "Ongeldige toestelstatus {" + dto.getDeviceStatus() + "}");
        }
    }


    public List<Device> findByUserId(long userId) {
        return deviceRepository.findByUserId(userId);
    }

    public Device updateDeviceStatus(long deviceId, String deviceStatus) {
        Device device = findById(deviceId);
        DeviceStatus status = deviceStatusService.findByName(deviceStatus);
        device.setDeviceStatus(status);
        return deviceRepository.save(device);
    }
}
