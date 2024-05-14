package be.groep14.domain.model;


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

