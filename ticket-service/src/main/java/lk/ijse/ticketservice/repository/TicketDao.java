package lk.ijse.ticketservice.repository;

import lk.ijse.ticketservice.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketDao extends JpaRepository<TicketEntity,String> {
}
