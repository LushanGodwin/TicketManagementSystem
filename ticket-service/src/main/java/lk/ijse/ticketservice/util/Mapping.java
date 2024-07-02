package lk.ijse.ticketservice.util;

import lk.ijse.ticketservice.dto.TicketDTO;
import lk.ijse.ticketservice.entity.TicketEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class Mapping {

    private final ModelMapper modelMapper;

    public TicketDTO toTicketDTO(Optional<TicketEntity> ticketEntity) {
        return modelMapper.map(ticketEntity, TicketDTO.class);
    }

    public TicketEntity toTicketEntity(TicketDTO ticketDTO) {
        return modelMapper.map(ticketDTO, TicketEntity.class);
    }

    public List<TicketDTO> toTicketDTOList(List<TicketEntity> tickets){
        return modelMapper.map(tickets, List.class);
    }
}
