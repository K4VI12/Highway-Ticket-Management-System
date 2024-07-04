package lk.ijse.gdse66.microservice.ticketservice.service;

import lk.ijse.gdse66.microservice.ticketservice.dto.TicketDTO;

import java.util.List;


public interface TicketService {
    List<TicketDTO> getAllTicket();
    TicketDTO getTicketDetails(String ticket_id);
    TicketDTO saveTicket(TicketDTO ticketDTO);
    void updateTicket(String ticket_id, TicketDTO ticketDTO);
    void deleteTicket(String ticket_id);
}
