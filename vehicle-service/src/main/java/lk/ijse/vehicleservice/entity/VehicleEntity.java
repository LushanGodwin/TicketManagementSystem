package lk.ijse.vehicleservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "vehicle")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleEntity {
    @Id
    private String vehicleCode;
    private String registrationNumber;
    private String ownerName;
    private String model;
    private String color;
    private String userId;
}
