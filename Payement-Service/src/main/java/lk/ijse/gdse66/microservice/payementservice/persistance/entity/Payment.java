package lk.ijse.gdse66.microservice.payementservice.persistance.entity;

import jakarta.persistence.*;
import lk.ijse.gdse66.microservice.payementservice.util.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @Column(name = "payment_id", unique = true, nullable = false)
    private String paymentId;
    @Column(name = "ticket_id", nullable = false)
    private String ticketId;
    @Column(name = "amount", nullable = false)
    private Double amount;
    @Column(name = "date", nullable = false)
    private String date;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;
}
