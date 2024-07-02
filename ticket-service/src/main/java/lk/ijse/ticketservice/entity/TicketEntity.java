package lk.ijse.ticketservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lk.ijse.ticketservice.Enum.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TicketEntity {
    @Id
    private String ticketId;
    private LocalDate ticketIssueDate;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String vehicleId;
    private String userId;
}
