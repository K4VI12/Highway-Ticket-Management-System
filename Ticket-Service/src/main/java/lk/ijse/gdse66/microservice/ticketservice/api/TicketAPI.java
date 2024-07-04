package lk.ijse.gdse66.microservice.ticketservice.api;

import jakarta.validation.Valid;
import lk.ijse.gdse66.microservice.ticketservice.dto.*;
import lk.ijse.gdse66.microservice.ticketservice.service.TicketService;
import lk.ijse.gdse66.microservice.ticketservice.service.execption.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("api/v0/ticket")
@RequiredArgsConstructor
public class TicketAPI {
    private final TicketService ticketService;
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    List<TicketDTO> getAllTicket(){
        return ticketService.getAllTicket();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    TicketDTO saveTicket(@Valid @RequestBody TicketDTO ticketDTO){
        try {
            UserDTO userDTOResponse = restTemplate.getForObject("http://user-service/api/v0/users/" + ticketDTO.getUserNic(), UserDTO.class);
        } catch (Exception e) {
            System.out.println("This User : " + ticketDTO.getUserNic() + " Not Exists!");
            throw new BadRequestException("This User : " + ticketDTO.getUserNic() + " Not Exists!");
        }

        try {
            VehicleDTO vehicleDTOResponse = restTemplate.getForObject("http://vehicle-service/api/v0/vehicle/" + ticketDTO.getVehicleNumber(), VehicleDTO.class);
        } catch (Exception e) {
            System.out.println("This Vehicle : " + ticketDTO.getVehicleNumber() + " Not Found...");
            throw new BadRequestException("This Vehicle : " + ticketDTO.getVehicleNumber() + " Not Found...");
        }

        System.out.println(ticketDTO);
        return ticketService.saveTicket(ticketDTO);
    }

    @PutMapping(value = "/{ticketId}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    void updateTicket(@Valid @RequestBody TicketDTO ticketDTO,@PathVariable("ticketId") String ticketId){
        try {
            UserDTO userDTOResponse = restTemplate.getForObject("http://user-service/api/v0/users/" + ticketDTO.getUserNic(), UserDTO.class);
        } catch (Exception e) {
            System.out.println("This User : " + ticketDTO.getUserNic() + " Not Exists!");
            throw new BadRequestException("This User : " + ticketDTO.getUserNic() + " Not Exists!");
        }

        try {
            VehicleDTO vehicleDTOResponse = restTemplate.getForObject("http://vehicle-service/api/v0/vehicle/" + ticketDTO.getVehicleNumber(), VehicleDTO.class);
        } catch (Exception e) {
            System.out.println("This Vehicle : " + ticketDTO.getVehicleNumber() + " Not Found...");
            throw new BadRequestException("This Vehicle : " + ticketDTO.getVehicleNumber() + " Not Found...");
        }
        ticketService.updateTicket(ticketId,ticketDTO);
    }

    @DeleteMapping(value = "/{ticketId}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    void deleteTicket(@PathVariable("ticketId") String ticketId){
        ticketService.deleteTicket(ticketId);
    }

    @GetMapping(value = "/{ticketId}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    TicketDTO getTicket(@PathVariable("ticketId") String ticketId){
        return ticketService.getTicketDetails(ticketId);
    }
}
