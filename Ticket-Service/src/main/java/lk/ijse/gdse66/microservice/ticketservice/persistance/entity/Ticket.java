package lk.ijse.gdse66.microservice.ticketservice.persistance.entity;

import jakarta.persistence.*;
import lk.ijse.gdse66.microservice.ticketservice.util.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;


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

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @Column(name = "user_nic", nullable = false)
    private String userNic;

    @Column(name = "vehicle_number", nullable = false)
    private String vehicleNumber;
}
