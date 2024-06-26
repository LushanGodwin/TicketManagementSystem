package lk.ijse.ticketservice.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.ticketservice.dto.TicketDTO;
import lk.ijse.ticketservice.repository.TicketDao;
import lk.ijse.ticketservice.service.TicketService;
import lk.ijse.ticketservice.util.Mapping;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
