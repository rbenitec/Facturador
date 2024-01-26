package store.rbenitez.biller.transaction.client;

import reactor.core.publisher.Mono;
import store.rbenitez.biller.transaction.dto.CustomerDto;

import org.springframework.stereotype.Service;

@Service
public interface CustomerClient {
    public Mono<CustomerDto> saveCustomer(CustomerDto customer);
}
