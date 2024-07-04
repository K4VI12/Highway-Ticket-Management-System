package lk.ijse.gdse66.microservice.userservice.api;

import jakarta.validation.Valid;
import lk.ijse.gdse66.microservice.userservice.dto.UserDTO;
import lk.ijse.gdse66.microservice.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/api/v0/users")
@RequiredArgsConstructor
public class UserAPI {
    private final UserService userService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    List<UserDTO>getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    UserDTO saveUser(@Valid @RequestBody UserDTO userDTO){
        return userService.saveUser(userDTO);
    }

    @PutMapping(value = "/{nic}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    void updateUser(@Valid @RequestBody UserDTO userDTO,@PathVariable("nic") String nic){
        userService.updateUser(nic,userDTO);
    }

    @DeleteMapping(value = "/{nic}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    void deleteUser(@PathVariable("nic") String nic){
        userService.deleteUser(nic);
    }

    @GetMapping(value = "/{nic}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    UserDTO getUser(@PathVariable("nic") String nic){
        System.out.println("dfg");
        return userService.getUserDetails(nic);
    }
}
