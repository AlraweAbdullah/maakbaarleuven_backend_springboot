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
    private DeviceTypeRepository deviceTypeRepository;

    public List<Device> getDevices() {
        return deviceRepository.findAll();
    }



    public Device createDevice(DeviceDto dto){
        return saveDevice(new Device(), dto);
    }

    public Device updateDevice(Long id,DeviceDto dto) {
        return  saveDevice(getDeviceById(id), dto);
    }

    public Device getDeviceById(Long id) {
        return deviceRepository.findById(id)
                .orElseThrow(() -> new ServiceException("get", "Toestel met id {" + id + "} is niet gevonden."));
    }

    public Device deleteDevice(long id) {
        Device device = getDeviceById(id);
        deviceRepository.delete(device);
        return device;
    }


    private Device saveDevice(Device device, DeviceDto dto){
        try {
            device.setSerial(dto.getSerial());
            DeviceType deviceTypeTest = new DeviceType();
            //Test device type
            deviceTypeTest.setName(dto.getDeviceType());
            deviceTypeRepository.save(deviceTypeTest);
            DeviceType deviceType = deviceTypeRepository.findByName(dto.getDeviceType()).orElseThrow(() -> new ServiceException("get", "Toestel type  {" + dto.getDeviceType() + "} is niet gevonden."));
            device.setDeviceType(deviceType);
            return deviceRepository.save(device);

        }catch (DataIntegrityViolationException e){
            throw new ServiceException("add","Toestel met serieel :  {" + device.getSerial() + "} bestaat al.");
        }
    }


}
