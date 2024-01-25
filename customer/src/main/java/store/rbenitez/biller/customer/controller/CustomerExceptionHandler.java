package store.rbenitez.biller.customer.controller;

import io.r2dbc.spi.R2dbcException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.transaction.TransactionException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import store.rbenitez.biller.customer.controller.dto.ErrorDto;
import store.rbenitez.biller.customer.exception.BusinessException;
import store.rbenitez.biller.customer.exception.GenericException;
import store.rbenitez.biller.customer.exception.RequestException;

@RestControllerAdvice
@Slf4j
public class CustomerExceptionHandler {

    /*@ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ErrorDto> runtimeExceptionHandler(RuntimeException ex){
        String message = ex.getMessage();
        String code = handlerCodeMessage(message);
        ErrorDto error = ErrorDto.builder().code(code).message(message).build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }*/

    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<ErrorDto> handlerValidationException(WebExchangeBindException ex){
        var bindError = ex.getBindingResult().getFieldErrors().get(0);
        String message = bindError.getDefaultMessage();
        String code = handlerCodeMessage(message);
        String field = bindError.getField();
        ErrorDto error = ErrorDto.builder().code(code).message(message.concat(" in the field: ").concat(field)).build();
        log.error("[handlerValidationException] - {}", error);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = RequestException.class)
    public ResponseEntity<ErrorDto> requestExceptionHandler(RequestException ex){
        ErrorDto error = ErrorDto.builder().code(ex.getCode()).message(ex.getMessage()).build();
        log.error("[requestExceptionHandler] - {}", error);
        return new ResponseEntity<>(error, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(value = BusinessException.class)
    public ResponseEntity<ErrorDto> businessExceptionHandler(BusinessException ex){
        ErrorDto error = ErrorDto.builder().code(ex.getCode()).message(ex.getMessage()).build();
        log.error("[businessExceptionHandler] - {}", error);
        return new ResponseEntity<>(error, ex.getHttpStatus());
    }

    @ExceptionHandler(value = GenericException.class)
    public ResponseEntity<ErrorDto> sqlServerExceptionHandler(GenericException ex){
        log.error("[SqlServerExceptionHandler] - {}", ex.getErrorDto());
        return new ResponseEntity<>(ex.getErrorDto(), ex.getErrorDto().getHttpStatus());
    }

    @ExceptionHandler(value = R2dbcException.class)
    public ResponseEntity<ErrorDto> r2dbcExceptionHandler(R2dbcException ex){
        ErrorDto error = ErrorDto.builder().code("8100.4.0").message(ex.getMessage()).build();
        log.error("[r2dbcExceptionHandler] - {}", error);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = TransactionException.class)
    public ResponseEntity<ErrorDto> transactionExceptionHandler(CannotCreateTransactionException ex){
        ErrorDto error = ErrorDto.builder().code("8100.3.0").message(ex.getMessage().substring(0,ex.getMessage().indexOf(";"))).build();
        log.error("[transactionExceptionHandler] - {}", error);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public String handlerCodeMessage(String message){

        switch (message) {
            case "documentNumber value is required" : return "8100.0.1";
            case "documentType value is required" : return "8100.0.2";
            case "name value is required" : return "8100.0.3";
            case "lastName value is required" : return "8100.0.4";
            case "email value is required" : return "8100.0.5";
            case "phoneNumber value is required" : return "8100.0.6";
            case "debe coincidir con 8 digitos numericos" : return "8100.0.7";
            case "el tamaño debe estar entre 3 y 3" : return "8100.0.8";
            case "el tamaño debe estar entre 0 y 100" : return "8100.0.9";
            case "debe coincidir con 9 digitos numericos" : return "8100.0.10";
            default: return "8100.0.0";
        }
    }

}
