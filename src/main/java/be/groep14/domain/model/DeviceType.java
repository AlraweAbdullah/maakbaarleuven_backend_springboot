package be.groep14.domain.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.Set;


@Entity
@Table(
        name = "deviceType",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})}
)

public class DeviceType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @JsonManagedReference
    @OneToMany(mappedBy = "deviceType", cascade = CascadeType.ALL)
    private Set<Device> devices;

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

    public Set<Device> getDevices() {
        return devices;
    }

    public void setDevices(Set<Device> devices) {
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

