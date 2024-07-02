package lk.ijse.vehicleservice.service.impl;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import lk.ijse.vehicleservice.dto.VehicleDTO;
import lk.ijse.vehicleservice.entity.VehicleEntity;
import lk.ijse.vehicleservice.repository.VehicleDao;
import lk.ijse.vehicleservice.service.VehicleService;
import lk.ijse.vehicleservice.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {
    private final VehicleDao vehicleDao;
    private final Mapping mapping;
    @Override
    public void updateVehicle(String id, VehicleDTO vehicleDTO) {
        if (!vehicleDao.existsById(vehicleDTO.getVehicleCode())) {
            return;
        }
        vehicleDao.save(mapping.mapTo(vehicleDTO, VehicleEntity.class));
    }

    @Override
    public void deleteVehicle(String id) {
        vehicleDao.deleteById(id);
    }

    @Override
    public void saveVehicle(VehicleDTO vehicleDTO) {
        VehicleEntity vehicle = mapping.mapTo(vehicleDTO, VehicleEntity.class);
        System.out.println(vehicle.getVehicleCode());
        vehicleDao.save(vehicle);
    }

    @Override
    public VehicleDTO getVehicle(String id) {
        return mapping.mapTo(vehicleDao.findById(id).get(), VehicleDTO.class);

    }

    @Override
    public List<VehicleDTO> getAllVehicles() {
        return mapping.mapTo(vehicleDao.findAll(), VehicleDTO.class);
    }

    @Override
    public List<VehicleDTO> getVehicleByUserId(String userId) {
        List<VehicleDTO> vehicleDTOs = new ArrayList<>();
        List<VehicleEntity> vehicles = vehicleDao.findAllByUserId(userId);
        for (VehicleEntity vehicle : vehicles) {
            vehicleDTOs.add(mapping.mapTo(vehicle, VehicleDTO.class));
        }
        return vehicleDTOs;
    }

    @Override
    public boolean isVehicleExists(String vehicleId) {
        return vehicleDao.existsById(vehicleId);
    }
}
