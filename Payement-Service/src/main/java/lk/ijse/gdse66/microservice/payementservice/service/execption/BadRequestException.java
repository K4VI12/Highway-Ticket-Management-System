package lk.ijse.gdse66.microservice.payementservice.service.execption;


public class BadRequestException extends ServiceException{
    public BadRequestException(String message) {
        super(message);
    }
}
