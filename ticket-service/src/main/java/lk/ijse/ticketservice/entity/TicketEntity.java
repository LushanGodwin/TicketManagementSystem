package lk.ijse.ticketservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TicketEntity {

    @Id
    private String id;
    private String vehicleNumber;
    private String issueDate;
    private String status;
    private Double amount;
}
