package be.groep14.domain.model;


import jakarta.validation.constraints.*;

public class DeviceDto {
    private long id;
    @NotBlank()
    @Size(min = 5, message = "device.serial.not.valid")
    private String serial;

    private String userEmail = "unknown";
    @NotBlank()
    private String deviceType;


    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public DeviceDto(){}

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


