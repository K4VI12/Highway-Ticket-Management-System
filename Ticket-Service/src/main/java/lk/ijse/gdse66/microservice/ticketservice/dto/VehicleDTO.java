package lk.ijse.gdse66.microservice.ticketservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDTO {
    @NotBlank
    private String vehicleNumber;
    @NotBlank
    private String vehicleModel;
    @NotBlank
    private String userNic;
}
