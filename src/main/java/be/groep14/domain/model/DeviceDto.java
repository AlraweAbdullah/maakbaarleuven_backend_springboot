package be.groep14.domain.model;


import jakarta.validation.constraints.*;
import java.util.*;

public class DeviceDto {
    private long id;
    @NotNull()
    @Size(min = 5, message = "{device.serial.not.valid}")
    private String serial;

    @NotNull
    @Size(min = 3, message = "{device.mark.not.valid}")
    private String mark;

    @Min(value = 1, message = "{device.userId.not.valid}")
    private long userId;

    private String deviceType;


    @Pattern(regexp = "Actief|Niet actief|Defect|Af staan", message = "{deviceStatus.name.not.valid}")
    private String deviceStatus;

    private List<DeviceMaintenance> maintenances;


    public DeviceDto() {
        this.maintenances = new ArrayList<>();
    }


    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public List<DeviceMaintenance> getMaintenances() {
        return maintenances;
    }

    public void setMaintenances(List<DeviceMaintenance> maintenances) {
        this.maintenances = maintenances;
    }

    public void addMaintenance(DeviceMaintenance deviceMaintenance) {

        this.maintenances.add(deviceMaintenance);
    }

    public String getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(String deviceStatus) {
        this.deviceStatus = deviceStatus;
    }


    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }


}


