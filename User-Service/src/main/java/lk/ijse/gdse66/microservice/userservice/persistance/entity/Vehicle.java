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
@Table(name = "vehicle")
public class Vehicle {
    @Id
    @Column(name = "vehicle_number", unique = true, nullable = false)
    private String vehicleNumber;

    @Column(name = "vehicle_model", nullable = false)
    private String vehicleModel;

    @ManyToOne
    @JoinColumn(name = "user_nic" , referencedColumnName = "user_nic")
    private User userNic;

}
