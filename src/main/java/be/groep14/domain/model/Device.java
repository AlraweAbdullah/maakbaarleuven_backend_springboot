package be.groep14.domain.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "device", uniqueConstraints = {@UniqueConstraint(columnNames = {"serial"})})
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String serial;

    private String mark;

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    @JsonBackReference("user-devices")
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @JsonBackReference("deviceType-devices")
    @ManyToOne
    @JoinColumn(name = "deviceTypeId")
    private DeviceType deviceType;

    @JsonManagedReference
    @OneToMany(mappedBy = "device", cascade = CascadeType.ALL)
    private List<DeviceMaintenance> deviceMaintenances;

    @JsonBackReference("deviceStatus-devices")
    @ManyToOne
    @JoinColumn(name = "deviceStatusId")
    private DeviceStatus deviceStatus;

    public void setDeviceMaintenances(List<DeviceMaintenance> deviceMaintenances) {
        this.deviceMaintenances = deviceMaintenances;
    }


    public DeviceStatus getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(DeviceStatus deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    public List<DeviceMaintenance> getDeviceMaintenances() {
        return deviceMaintenances;
    }

    public void addDeviceMaintenance(DeviceMaintenance deviceMaintenance) {
        deviceMaintenance.setDevice(this);
        this.deviceMaintenances.add(deviceMaintenance);
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    public long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

