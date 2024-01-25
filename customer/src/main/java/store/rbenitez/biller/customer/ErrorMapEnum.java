package store.rbenitez.biller.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMapEnum {
    UNKNOW,
    BAD_REQUEST,
    NOT_FOUND,
    REQUEST_TIMEOUT,
    INTERNAL_SERVER_ERROR,
    CONNECTION_REFUSED,
}
