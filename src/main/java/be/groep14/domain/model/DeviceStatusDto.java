package be.groep14.domain.model;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public class DeviceStatusDto {
    private long id;
    @NotBlank()
    @Size(min = 3, message = "{deviceStatus.name.not.valid}")
    private String status;

    private List<Device> devices;


    public DeviceStatusDto() {
    }

    public DeviceStatusDto(long id, String status) {
        this.id = id;
        this.status = status;
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

    public void setName(String status) {
        this.status = status;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    public String getName() {
        return status;
    }
}


