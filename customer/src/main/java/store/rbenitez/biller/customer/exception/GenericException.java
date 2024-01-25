package store.rbenitez.biller.customer.exception;

import lombok.Data;
import store.rbenitez.biller.customer.controller.dto.ErrorDto;

@Data
public class GenericException extends RuntimeException{

    private ErrorDto errorDto;

    public GenericException(ErrorDto errorDto) {
        super(errorDto.getMessage());
        this.errorDto =  errorDto;
    }
}
