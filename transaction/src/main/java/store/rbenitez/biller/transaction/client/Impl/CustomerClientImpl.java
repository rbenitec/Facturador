package store.rbenitez.biller.transaction.client.Impl;

import reactor.core.publisher.Mono;
import store.rbenitez.biller.transaction.client.CustomerClient;
import store.rbenitez.biller.transaction.dto.CustomerDto;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class CustomerClientImpl implements CustomerClient {
    @Override
    public Mono<CustomerDto> saveCustomer(CustomerDto customer) {
        return null;
    }
}
