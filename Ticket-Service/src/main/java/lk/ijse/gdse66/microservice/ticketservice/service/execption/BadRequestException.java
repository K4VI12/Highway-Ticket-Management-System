package lk.ijse.gdse66.microservice.ticketservice.service.execption;


public class BadRequestException extends ServiceException{
    public BadRequestException(String message) {
        super(message);
    }
}
