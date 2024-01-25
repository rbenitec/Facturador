package store.rbenitez.biller.customer.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import store.rbenitez.biller.customer.controller.dto.CustomerDto;
import store.rbenitez.biller.customer.entities.Customer;

@Service
public interface CustomerService {
    Mono<Customer> saveCustomer (CustomerDto customer);
    void deleteCustomer(Integer idCustomer);
    Mono<Customer> findCustomer(Integer idCustomer);
    Flux<Customer> finAllCustomer();
    Mono<Customer> customerTransaction(CustomerDto customerDto);
    Mono<Customer> findCustomerByDocumentNumber(String documentNumber);
}
