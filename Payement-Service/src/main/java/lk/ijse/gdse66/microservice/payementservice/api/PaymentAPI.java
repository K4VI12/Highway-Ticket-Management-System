package lk.ijse.gdse66.microservice.payementservice.api;

import jakarta.validation.Valid;
import lk.ijse.gdse66.microservice.payementservice.dto.PaymentDTO;
import lk.ijse.gdse66.microservice.payementservice.dto.TicketDTO;
import lk.ijse.gdse66.microservice.payementservice.service.PaymentService;
import lk.ijse.gdse66.microservice.payementservice.service.execption.BadRequestException;
import lk.ijse.gdse66.microservice.payementservice.service.execption.ServiceException;
import lk.ijse.gdse66.microservice.payementservice.util.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@RestController
@RequestMapping("api/v0/payment")
@RequiredArgsConstructor
public class PaymentAPI {
    private final PaymentService paymentService;
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    List<PaymentDTO> getAllPayment(){
        return paymentService.getAllPayment();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    PaymentDTO savePayment(@Valid @RequestBody PaymentDTO paymentDTO){
        TicketDTO ticketResponse;
        HttpEntity<TicketDTO> requestEntity;
        try {
            ticketResponse = restTemplate.getForObject("http://ticket-service/api/v0/ticket/"+paymentDTO.getTicketId(),TicketDTO.class);
        }catch (Exception e){
            throw new BadRequestException("This Ticket "+ paymentDTO.getTicketId()+" Not Exicts...");
        }

        try {
            ticketResponse.setStatus(Status.PAID);
            String url = "http://ticket-service/api/v0/ticket/" + ticketResponse.getTicketId();
            requestEntity = new HttpEntity<>(ticketResponse);
            ResponseEntity<TicketDTO> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, TicketDTO.class);

        }catch (Exception e){
            throw new ServiceException("Cannot update ticket "+ticketResponse.getTicketId()+" statu. Please try again later...");
        }

        try {
            return paymentService.savePayment(paymentDTO);
        }catch (Exception e) {
            ticketResponse.setStatus(Status.NONPAID);
            String url = "http://ticket-service/api/v0/ticket/" + ticketResponse.getTicketId();
            HttpEntity<TicketDTO> deleterequestEntity = new HttpEntity<>(ticketResponse);
            ResponseEntity<TicketDTO> deleteresponseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, TicketDTO.class);

            throw new ServiceException("Payment not completed...");
        }
    }

    @PutMapping(value = "/{paymentId}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    void updatePayment(@Valid @RequestBody PaymentDTO paymentDTO,@PathVariable("paymentId") String paymentId){
        paymentService.updatePayment(paymentId,paymentDTO);
    }

    @DeleteMapping(value = "/{paymentId}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    void deletePayment(@PathVariable("paymentId") String paymentId){
        paymentService.deletePayment(paymentId);
    }

    @GetMapping(value = "/{paymentId}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    PaymentDTO getPayment(@PathVariable("paymentId") String paymentId){
        return paymentService.getPaymentDetails(paymentId);
    }
}
