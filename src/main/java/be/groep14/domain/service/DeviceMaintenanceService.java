package be.groep14.domain.service;

import be.groep14.domain.exception.ServiceException;
import be.groep14.domain.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceMaintenanceService {
    @Autowired
    private DeviceMaintenanceRepository deviceMaintenanceRepository;

    @Autowired
    private DeviceService deviceService;

    public DeviceMaintenance createDeviceMaintenance(DeviceMaintenanceDto dto, long deviceId) {
        dto.setDevice(deviceService.findById(deviceId));
        return saveDeviceMaintenance(new DeviceMaintenance(), dto);
    }

    private DeviceMaintenance saveDeviceMaintenance(DeviceMaintenance deviceMaintenance, DeviceMaintenanceDto dto) {
        try {
            deviceMaintenance.setAction(dto.getAction());
            deviceMaintenance.setPeriod(dto.getPeriod());
            deviceMaintenance.setDevice(dto.getDevice());
            return deviceMaintenanceRepository.save(deviceMaintenance);

        } catch (DataIntegrityViolationException e) {
            throw new ServiceException("toevoegen", "Uw toestel heeft  deze onderhoud al");
        }
    }


    public List<DeviceMaintenance> findByDeviceId(long deviceId) {
        return deviceMaintenanceRepository.findByDeviceId(deviceId);

    }

}
