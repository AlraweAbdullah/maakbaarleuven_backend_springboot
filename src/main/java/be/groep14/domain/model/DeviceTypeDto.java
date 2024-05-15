package be.groep14.domain.model;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Set;

public class DeviceTypeDto {
    private long id;
    @NotBlank()
    @Size(min = 3, message = "deviceType.name.not.valid")
    private String name;

    private Set<Device> devices;


    public DeviceTypeDto(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<Device> getDevices() {
        return devices;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDevices(Set<Device> devices) {
        this.devices = devices;
    }

    public String getName() {
        return name;
    }
}


