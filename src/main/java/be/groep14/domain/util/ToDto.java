package be.groep14.domain.util;

import be.groep14.domain.model.*;

import java.util.ArrayList;
import java.util.List;

public class  ToDto {
    public static DeviceMaintenanceDto toDeviceMaintenanceDto(DeviceMaintenance deviceMaintenance)  {
        return new DeviceMaintenanceDto(deviceMaintenance.getId(), deviceMaintenance.getAction(), deviceMaintenance.getPeriod());
    }

    public static List<DeviceMaintenanceDto> toDeviceMaintenanceDtoList(List<DeviceMaintenance> deviceMaintenances) {
        List<DeviceMaintenanceDto> deviceMaintenanceDtoList = new ArrayList<>();
        for (DeviceMaintenance deviceMaintenance : deviceMaintenances) {
            deviceMaintenanceDtoList.add(toDeviceMaintenanceDto(deviceMaintenance));
        }
        return deviceMaintenanceDtoList;
    }

    public static DeviceDto toDeviceDto(Device device) {
        return new DeviceDto(device.getId(), device.getSerial(), device.getMark(), device.getUser().getId(), device.getDeviceType().getName(),device.getDeviceStatus().getName(), device.getDeviceMaintenances());
    }

    public static List<DeviceDto> toDeviceDtoList(List<Device> devices) {
        List<DeviceDto> deviceDtoList = new ArrayList<>();
        for (Device device : devices) {
            deviceDtoList.add(toDeviceDto(device));
        }
        return deviceDtoList;
    }

    public static DeviceStatusDto toDeviceStatusDto(DeviceStatus deviceStatus) {
        return new DeviceStatusDto(deviceStatus.getId(),deviceStatus.getName());
    }

    public static List<DeviceStatusDto> toDeviceStatusDtoList(List<DeviceStatus> deviceStatuses) {
        List<DeviceStatusDto> deviceStatusDtoList = new ArrayList<>();

        for (DeviceStatus deviceStatus : deviceStatuses) {
            deviceStatusDtoList.add(toDeviceStatusDto(deviceStatus));
        }
        return deviceStatusDtoList;
    }

    public static DeviceTypeDto toDeviceTypeDto(DeviceType deviceType) {
        return new DeviceTypeDto(deviceType.getId(), deviceType.getName());
    }

    public static List<DeviceTypeDto> toDeviceTypeDtoList(List<DeviceType> deviceTypes) {
        List<DeviceTypeDto> deviceTypeDtoList = new ArrayList<>();

        for (DeviceType deviceType : deviceTypes) {
            deviceTypeDtoList.add(toDeviceTypeDto(deviceType));
        }
        return deviceTypeDtoList;
    }

    public static UserDto toUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getLastname(),
                user.getEmail(),
                user.getDevices(),
                user.getBirthdate(),
                user.getTelephone() == 0 ? "Geen" : String.valueOf(user.getTelephone()),
                user.getPersons() == 0 ? "Geen" : String.valueOf(user.getPersons()),
                user.getHouseNr(),
                user.getStreet(),
                user.getPassword()
        );
    }

    public static List<UserDto> toUserDtoList(List<User> users) {
        List<UserDto> userDtoList = new ArrayList<>();

        for (User user : users) {
            userDtoList.add(toUserDto(user));
        }
        return userDtoList;
    }

    public static AdminDto toAdminDto(Admin admin) {
        return new AdminDto(admin.getId(), admin.getEmail(), admin.getPassword());
    }

    public static List<AdminDto> toAdminDtoList(List<Admin> admins) {
        List<AdminDto> adminDtoList = new ArrayList<>();

        for (Admin admin : admins) {
            adminDtoList.add(toAdminDto(admin));
        }
        return adminDtoList;
    }
}
