package lk.ijse.gdse66.microservice.ticketservice.service.execption;



public class ServiceException extends RuntimeException{
    public ServiceException(String message){
        super(message);
    }
}
