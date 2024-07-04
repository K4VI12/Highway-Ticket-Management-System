package lk.ijse.gdse66.microservice.ticketservice.persistance.repository;

import lk.ijse.gdse66.microservice.ticketservice.persistance.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket,String> {
    Ticket findByTicketId(String ticket_id);
    Boolean existsByTicketId(String ticket_id);
    void deleteByTicketId(String ticket_id);
}
