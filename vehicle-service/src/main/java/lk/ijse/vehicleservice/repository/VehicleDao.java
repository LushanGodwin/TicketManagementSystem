package lk.ijse.vehicleservice.repository;

import lk.ijse.vehicleservice.entity.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleDao extends JpaRepository<VehicleEntity,String> {
    List<VehicleEntity> findAllByUserId(String userId);
}
