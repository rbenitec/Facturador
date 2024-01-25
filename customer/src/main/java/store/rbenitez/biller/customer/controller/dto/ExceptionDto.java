package store.rbenitez.biller.customer.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.http.HttpStatus;
import store.rbenitez.biller.customer.ErrorMapEnum;

public class ExceptionDto {
    private String timestamp;
    private HttpStatus httpStatus;
    private String exceptionMessage;
    private Class<?> exceptionClass;
    @JsonIgnore
    private ErrorMapEnum mapEnum;
    private String code;
    private String message;
    private String details;
}
