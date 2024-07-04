package lk.ijse.gdse66.microservice.vehicleservice.service.execption;


public class BadRequestException extends ServiceException{
    public BadRequestException(String message) {
        super(message);
    }
}
