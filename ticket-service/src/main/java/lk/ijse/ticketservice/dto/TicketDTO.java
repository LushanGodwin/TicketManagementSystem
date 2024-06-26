package lk.ijse.ticketservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDTO {
    private String id;
    private String vehicleNumber;
    private String issueDate;
    private String status;
    private Double amount;
}
