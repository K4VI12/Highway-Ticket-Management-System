package lk.ijse.gdse66.microservice.userservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lk.ijse.gdse66.microservice.userservice.util.Gender;
import lombok.*;


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
    @NotNull
    private Gender gender;
}
