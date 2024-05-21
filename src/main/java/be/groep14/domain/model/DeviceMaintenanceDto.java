package be.groep14.domain.model;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class DeviceMaintenanceDto {
    private long id;

    @NotBlank()
    @Size(min = 2, message = "{deviceMaintenance.action.not.valid}")
    private String action;

    private String period;

    private Device device;

    public DeviceMaintenanceDto() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }
}


