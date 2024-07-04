package lk.ijse.gdse66.microservice.ticketservice.service.execption;



public class DublicateRecordException extends ServiceException{
    public DublicateRecordException(String message) {
        super(message);
    }
}
