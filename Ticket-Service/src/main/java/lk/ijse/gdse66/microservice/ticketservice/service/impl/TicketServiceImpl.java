package lk.ijse.gdse66.microservice.ticketservice.service.impl;

import lk.ijse.gdse66.microservice.ticketservice.dto.TicketDTO;
import lk.ijse.gdse66.microservice.ticketservice.persistance.entity.Ticket;
import lk.ijse.gdse66.microservice.ticketservice.persistance.repository.TicketRepository;
import lk.ijse.gdse66.microservice.ticketservice.service.TicketService;
import lk.ijse.gdse66.microservice.ticketservice.service.execption.DublicateRecordException;
import lk.ijse.gdse66.microservice.ticketservice.service.execption.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class TicketServiceImpl implements TicketService {
    TicketRepository ticketRepository;
    ModelMapper modelMapper;

    public TicketServiceImpl(TicketRepository ticketRepository, ModelMapper modelMapper) {
        this.ticketRepository = ticketRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<TicketDTO> getAllTicket() {
        return ticketRepository.findAll().stream().map(
                ticket -> modelMapper.map(ticket,TicketDTO.class)
        ).toList();
    }

    @Override
    public TicketDTO getTicketDetails(String ticket_id) {
        if(!ticketRepository.existsByTicketId(ticket_id)){
            throw new NotFoundException("Ticket "+ticket_id+" Not Found!");
        }
        return modelMapper.map(ticketRepository.findByTicketId(ticket_id), TicketDTO.class);
    }

    @Override
    public TicketDTO saveTicket(TicketDTO ticketDTO) {
        if(ticketRepository.existsByTicketId(ticketDTO.getTicketId())){
            throw new DublicateRecordException("This Ticket "+ticketDTO.getTicketId()+" already exicts...");
        }
        return modelMapper.map(ticketRepository.save(modelMapper.map(
                ticketDTO, Ticket.class)), TicketDTO.class
        );
    }

    @Override
    @Transactional
    public void updateTicket(String ticket_id, TicketDTO ticketDTO) {
        if(!ticketRepository.existsByTicketId(ticket_id)){
            throw new NotFoundException("Ticket"+ ticket_id + "Not Found...");
        }
        ticketDTO.setTicketId(ticket_id);
        ticketRepository.save(modelMapper.map(ticketDTO,Ticket.class));
    }

    @Override
    public void deleteTicket(String ticket_id) {
        if(!ticketRepository.existsByTicketId(ticket_id)){
            throw  new NotFoundException("Ticket"+ ticket_id + "Not Found...");
        }
        ticketRepository.deleteByTicketId(ticket_id);
    }
}
