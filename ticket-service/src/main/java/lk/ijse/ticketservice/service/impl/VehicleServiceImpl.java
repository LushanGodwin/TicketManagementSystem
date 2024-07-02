package lk.ijse.ticketservice.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.ticketservice.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {
    private static final Logger logger = LoggerFactory.getLogger(UserClientServiceImpl.class);
    private final RestTemplate restTemplate;
    @Override
    public boolean isExitsVehicle(String id) {
        try {
            String url = "http://vehicle-service/api/v1/vehicle/vehicleExists/" + id;
            Boolean vehicleExists = restTemplate.getForObject(url, Boolean.class);
            logger.info("Vehicle Exists: {}", vehicleExists);
            return vehicleExists != null && vehicleExists;
        } catch (Exception e) {
            logger.error("Error checking if vehicle exists", e);
        }
        return false;
    }
}
