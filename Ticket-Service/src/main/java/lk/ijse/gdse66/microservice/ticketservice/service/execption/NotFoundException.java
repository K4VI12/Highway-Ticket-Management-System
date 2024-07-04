package lk.ijse.gdse66.microservice.ticketservice.service.execption;


public class NotFoundException extends ServiceException{
    public NotFoundException(String message) {
        super(message);
    }
}
