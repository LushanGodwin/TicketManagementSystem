package lk.ijse.ticketservice.service.impl;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import lk.ijse.ticketservice.Enum.Status;
import lk.ijse.ticketservice.dto.TicketDTO;
import lk.ijse.ticketservice.entity.TicketEntity;
import lk.ijse.ticketservice.repository.TicketDao;
import lk.ijse.ticketservice.service.TicketService;
import lk.ijse.ticketservice.util.Mapping;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@RequiredArgsConstructor
@Service
public class TicketServiceImpl implements TicketService {
    private final TicketDao ticketDao;
    private final Mapping mapping;

    @Override
    public void saveTicket(TicketDTO ticketDTO) {
        ticketDao.save(mapping.toTicketEntity(ticketDTO));
    }

    @Override
    public List<TicketDTO> getTicketsByUserId(String userId) {
        List<TicketDTO> tickeDTOs = new ArrayList<>();
        List<TicketEntity> tickets = ticketDao.findAllByUserId(userId);
        for (TicketEntity ticket : tickets) {
            tickeDTOs.add(mapping.toTicketDTO(Optional.ofNullable(ticket)));
        }
        return tickeDTOs;
    }

    @Override
    public List<TicketDTO> getTicketsByVehicleId(String vehicleId) {
        List<TicketDTO> tickeDTOs = new ArrayList<>();
        List<TicketEntity> tickets = ticketDao.findAllByVehicleId(vehicleId);
        for (TicketEntity ticket : tickets) {
            tickeDTOs.add(mapping.toTicketDTO(Optional.ofNullable(ticket)));
        }
        return tickeDTOs;
    }

    @Override
    public boolean isTicketExists(String ticketId) {
        if (ticketDao.existsById(ticketId)){
            TicketDTO ticket = getTicket(ticketId);
            ticket.setStatus(Status.PAID);
            updateTicket(ticket);
            return true;
        }
        return false;
    }

    public TicketDTO getTicket(String ticketId) {
        if (!ticketDao.existsById(ticketId)) throw new NotFoundException("Ticket not Found");
        return mapping.toTicketDTO(ticketDao.findById(ticketId));
    }

    @Override
    public List<TicketDTO> getAllTickets() {
        return mapping.toTicketDTOList(ticketDao.findAll());
    }

    public void updateTicket(TicketDTO ticketDTO) {
        if (!ticketDao.existsById(ticketDTO.getTicketId())){
            return;
        }
        ticketDao.save(mapping.toTicketEntity(ticketDTO));
    }

    @Override
    public void deleteTicket(String ticketId) {
        ticketDao.deleteById(ticketId);
    }
}
