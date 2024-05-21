package be.groep14.domain.model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface DeviceMaintenanceRepository extends JpaRepository<DeviceMaintenance, Long> {
    List<DeviceMaintenance> findByDeviceId(Long deviceId);
}
