package store.rbenitez.biller.customer.exception;


import io.netty.handler.timeout.ReadTimeoutException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import store.rbenitez.biller.customer.controller.dto.ErrorDto;
import store.rbenitez.biller.customer.util.Constanst;
import store.rbenitez.biller.customer.util.DataUtil;

import java.net.ConnectException;
import java.sql.SQLTransientConnectionException;

public final class SqlServerException{

    public SqlServerException() {
    }

    /*    private String timestamp;
    private HttpStatus httpStatus;
    private String code;

    public SqlServerException(String code, String message, HttpStatus httpStatus){
        super(message);
        this.code = code;
        this.httpStatus = httpStatus;
        this.timestamp = DataUtil.getCurrentDate(Constanst.FORMAT_ISO_8601);
    }*/

    public static GenericException handlerException (Exception exception){
        var dto = new ErrorDto();
        dto.setTimestamp(DataUtil.getCurrentDate(Constanst.FORMAT_ISO_8601));
        Class<?> exceptionClass = ExceptionUtils.getRootCause(exception).getClass();
        if(exceptionClass.equals(ReadTimeoutException.class)){
            dto.setMessage("Se genero una exception de REQUEST_TIMEOUT al consultar a Base de datso");
            dto.setCode("8100.2.0");
            dto.setHttpStatus(HttpStatus.REQUEST_TIMEOUT);
        } else if (exceptionClass.equals(SQLTransientConnectionException.class)) {
            dto.setMessage("Se genero una exception de TRANSIENT CONNECTION EXCEPTION al consultar a Base de datso");
            dto.setCode("8100.2.1");
            dto.setHttpStatus(HttpStatus.EXPECTATION_FAILED);
        } else if (exceptionClass.equals(ConnectException.class)) {
            dto.setMessage("Se genero un error de conexion al consultar a Base de datos");
            dto.setCode("8100.2.2");
            dto.setHttpStatus(HttpStatus.EXPECTATION_FAILED);
        } else {
            dto.setMessage("Se genero una exception de INTERNAL SERVER ERROR DESCONOCIDO al consultar a Base de datso");
            dto.setCode("8100.2.3");
            dto.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new GenericException(dto);
    }
}
