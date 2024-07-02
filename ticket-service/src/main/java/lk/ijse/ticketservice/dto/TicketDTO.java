package lk.ijse.ticketservice.dto;

import lk.ijse.ticketservice.Enum.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDTO {
    private String ticketId;
    private LocalDate ticketIssueDate;
    private Status status;
    private String vehicleId;
    private String userId;
}
