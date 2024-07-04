package lk.ijse.gdse66.microservice.userservice.persistance.entity;

import jakarta.persistence.*;
import lk.ijse.gdse66.microservice.userservice.util.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @Column(name = "ticket_id", unique = true, nullable = false)
    private String ticketId;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "state", nullable = false)
    private String state;
}
