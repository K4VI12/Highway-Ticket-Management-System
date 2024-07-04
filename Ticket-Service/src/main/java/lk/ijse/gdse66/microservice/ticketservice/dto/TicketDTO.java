package lk.ijse.gdse66.microservice.ticketservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lk.ijse.gdse66.microservice.ticketservice.util.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDTO {
    @NotBlank
    private String ticketId;
    @NotNull
    private String description;
    @NotNull
    private Status status;
    @NotBlank
    private String userNic;
    @NotBlank
    private String vehicleNumber;
}
