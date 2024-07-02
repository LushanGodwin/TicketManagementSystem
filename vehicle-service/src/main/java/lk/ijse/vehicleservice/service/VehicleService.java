package lk.ijse.vehicleservice.service;

import lk.ijse.vehicleservice.dto.VehicleDTO;

import java.util.List;

public interface VehicleService {
    void updateVehicle(String id, VehicleDTO vehicleDTO);

    void deleteVehicle(String id);

    void saveVehicle(VehicleDTO vehicleDTO);

    VehicleDTO getVehicle(String id);

    List<VehicleDTO> getAllVehicles();

    List<VehicleDTO> getVehicleByUserId(String userId);

    boolean isVehicleExists(String vehicleId);
}
