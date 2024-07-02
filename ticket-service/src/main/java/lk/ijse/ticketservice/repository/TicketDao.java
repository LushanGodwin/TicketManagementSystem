package lk.ijse.ticketservice.repository;

import lk.ijse.ticketservice.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketDao extends JpaRepository<TicketEntity,String> {
    List<TicketEntity> findAllByUserId(String userId);

    List<TicketEntity> findAllByVehicleId(String vehicleId);
}
