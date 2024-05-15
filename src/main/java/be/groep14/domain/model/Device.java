package be.groep14.domain.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;


@Entity
@Table(
        name = "device",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"serial"})}
)
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String serial;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "usersId")
    private User user;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "deviceTypeId")
    private DeviceType deviceType;

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

