package be.groep14.domain.model;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public class DeviceTypeDto {
    private long id;
    @NotBlank()
    @Size(min = 3, message = "deviceType.name.not.valid")
    private String name;

    private List<Device> devices;


    public DeviceTypeDto() {
    }

    public DeviceTypeDto(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    public String getName() {
        return name;
    }
}


