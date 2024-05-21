package be.groep14.domain.util;

import be.groep14.domain.model.*;

import java.util.ArrayList;
import java.util.List;

public class  ToDto {
    public static DeviceMaintenanceDto toDeviceMaintenanceDto(DeviceMaintenance deviceMaintenance) {
        DeviceMaintenanceDto dto = new DeviceMaintenanceDto();
        dto.setId(deviceMaintenance.getId());
        dto.setAction(deviceMaintenance.getAction());
        dto.setPeriod(deviceMaintenance.getPeriod());
        return dto;
    }

    public static List<DeviceMaintenanceDto> toDeviceMaintenanceDtoList(List<DeviceMaintenance> deviceMaintenances) {
        List<DeviceMaintenanceDto> deviceMaintenanceDtoList = new ArrayList<>();

        for (DeviceMaintenance deviceMaintenance : deviceMaintenances) {
            deviceMaintenanceDtoList.add(toDeviceMaintenanceDto(deviceMaintenance));
        }
        return deviceMaintenanceDtoList;
    }

    public static DeviceDto toDeviceDto(Device device) {
        DeviceDto dto = new DeviceDto();
        dto.setId(device.getId());
        dto.setSerial(device.getSerial());
        dto.setUserId(device.getUser().getId());
        dto.setDeviceType(device.getDeviceType().getName());
        dto.setMaintenances(device.getDeviceMaintenances());
        dto.setDeviceStatus(device.getDeviceStatus().getName());
        dto.setMark(device.getMark());
        return dto;
    }

    public static List<DeviceDto> toDeviceDtoList(List<Device> devices) {
        List<DeviceDto> deviceDtoList = new ArrayList<>();

        for (Device device : devices) {
            deviceDtoList.add(toDeviceDto(device));
        }
        return deviceDtoList;
    }

    public static DeviceStatusDto toDeviceStatusDto(DeviceStatus deviceStatus) {
        DeviceStatusDto dto = new DeviceStatusDto();
        dto.setId(deviceStatus.getId());
        dto.setName(deviceStatus.getName());
        return dto;
    }

    public static List<DeviceStatusDto> toDeviceStatusDtoList(List<DeviceStatus> deviceStatuses) {
        List<DeviceStatusDto> deviceStatusDtoList = new ArrayList<>();

        for (DeviceStatus deviceStatus : deviceStatuses) {
            deviceStatusDtoList.add(toDeviceStatusDto(deviceStatus));
        }
        return deviceStatusDtoList;
    }

    public static DeviceTypeDto toDeviceTypeDto(DeviceType deviceType) {
        DeviceTypeDto dto = new DeviceTypeDto();
        dto.setId(deviceType.getId());
        dto.setName(deviceType.getName());
        return dto;
    }

    public static List<DeviceTypeDto> toDeviceTypeDtoList(List<DeviceType> deviceTypes) {
        List<DeviceTypeDto> deviceTypeDtoList = new ArrayList<>();

        for (DeviceType deviceType : deviceTypes) {
            deviceTypeDtoList.add(toDeviceTypeDto(deviceType));
        }
        return deviceTypeDtoList;
    }

    public static UserDto toUserDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setLastname(user.getLastname());
        dto.setEmail(user.getEmail());
        dto.setDevices(user.getDevices());
        dto.setBirthdate(user.getBirthdate());
        dto.setTelephone(user.getTelephone() == 0 ? "Geen" : String.valueOf(user.getTelephone()));
        dto.setPersons(user.getPersons() == 0 ? "Geen" : String.valueOf(user.getPersons()));
        dto.setHouseNr(user.getHouseNr());
        dto.setStreet(user.getStreet());
        dto.setPassword(user.getPassword());
        dto.setDevices(user.getDevices());
        return dto;
    }

    public static List<UserDto> toUserDtoList(List<User> users) {
        List<UserDto> userDtoList = new ArrayList<>();

        for (User user : users) {
            userDtoList.add(toUserDto(user));
        }
        return userDtoList;
    }

    public static AdminDto toAdminDto(Admin admin) {
        AdminDto dto = new AdminDto();
        dto.setId(admin.getId());
        dto.setEmail(admin.getEmail());
        dto.setPassword(admin.getPassword());
        return dto;
    }

    public static List<AdminDto> toAdminDtoList(List<Admin> admins) {
        List<AdminDto> adminDtoList = new ArrayList<>();

        for (Admin admin : admins) {
            adminDtoList.add(toAdminDto(admin));
        }
        return adminDtoList;
    }
}
