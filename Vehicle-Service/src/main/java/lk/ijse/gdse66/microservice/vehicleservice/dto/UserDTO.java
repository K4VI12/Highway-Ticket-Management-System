package lk.ijse.gdse66.microservice.vehicleservice.dto;

import jakarta.validation.constraints.NotBlank;
import lk.ijse.gdse66.microservice.vehicleservice.util.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @NotBlank
    private String userNic;
    @NotBlank
    private String userName;
    @NotBlank
    private String userAddress;
    @NotBlank
    private Gender gender;
}
