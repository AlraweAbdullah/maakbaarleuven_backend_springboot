package be.groep14.domain.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "deviceType", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})

public class DeviceType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @JsonManagedReference("deviceType-devices")
    @OneToMany(mappedBy = "deviceType", cascade = CascadeType.ALL)
    private List<Device> devices;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    public void addDevice(Device device) {
        getDevices().add(device);
        device.setDeviceType(this);
    }

    public void remove(Device device) {
        device.setDeviceType(null);
    }
}

