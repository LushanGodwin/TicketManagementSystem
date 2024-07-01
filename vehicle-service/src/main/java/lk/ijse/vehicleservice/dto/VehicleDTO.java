package lk.ijse.vehicleservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDTO {
    private String vehicleCode;
    private String registrationNumber;
    private String ownerName;
    private String model;
    private String color;
    private String userId;
}
