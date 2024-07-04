package lk.ijse.gdse66.microservice.payementservice.advice;

import lk.ijse.gdse66.microservice.payementservice.service.execption.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<Map<String,Object>>handleServiceExecption(ServiceException exp){
        Map<String,Object> errorAttribute;
        if(exp instanceof DublicateRecordException){
            errorAttribute = getCommonError(HttpStatus.CONFLICT);
        }else if(exp instanceof NotFoundException){
            errorAttribute = getCommonError(HttpStatus.NOT_FOUND);
        }else if(exp instanceof BadRequestException){
            errorAttribute = getCommonError(HttpStatus.BAD_REQUEST);
        }else{
            errorAttribute = getCommonError(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        errorAttribute.put("message",exp.getMessage());
        return new ResponseEntity<>(errorAttribute,HttpStatus.valueOf((Integer) errorAttribute.get("code")));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object>handleDataValidationException(MethodArgumentNotValidException exp){
        Map<String, Object> errorAttribute = getCommonError(HttpStatus.BAD_REQUEST);
        ArrayList<Map<String,Object>> errorList = new ArrayList<>();
        for (FieldError fieldError : exp.getFieldErrors()) {
            LinkedHashMap<String,Object> errorMap=new LinkedHashMap<>();
            errorMap.put("failed",fieldError.getField());
            errorMap.put("message",fieldError.getDefaultMessage());
            errorMap.put("rejected",fieldError.getRejectedValue());
            errorList.add(errorMap);
        }
        errorAttribute.put("message","Data Validation Failed...!");
        errorAttribute.put("errors",errorList);

        return errorAttribute;
    }

    public Map<String,Object>getCommonError(HttpStatus status){
        LinkedHashMap<String,Object>errorAttribute = new LinkedHashMap<>();
        errorAttribute.put("code",status.value());
        errorAttribute.put("status",status);

        return errorAttribute;
    }
}
