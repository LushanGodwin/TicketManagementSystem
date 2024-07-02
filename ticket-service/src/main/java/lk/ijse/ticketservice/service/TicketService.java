package lk.ijse.ticketservice.service;


import lk.ijse.ticketservice.dto.TicketDTO;

import java.util.List;

public interface TicketService {
    void saveTicket(TicketDTO ticketDTO);

    List<TicketDTO> getTicketsByUserId(String userId);

    List<TicketDTO> getTicketsByVehicleId(String vehicleId);

    boolean isTicketExists(String ticketId);

    TicketDTO getTicket(String ticketId);

    List<TicketDTO> getAllTickets();

    void updateTicket(TicketDTO ticketDTO);

    void deleteTicket(String ticketId);
}
