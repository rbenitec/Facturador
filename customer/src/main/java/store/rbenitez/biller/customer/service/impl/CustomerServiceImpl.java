package store.rbenitez.biller.customer.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import store.rbenitez.biller.customer.controller.dto.CustomerDto;
import store.rbenitez.biller.customer.entities.Customer;
import store.rbenitez.biller.customer.exception.SqlServerException;
import store.rbenitez.biller.customer.repository.CustomerRepository;
import store.rbenitez.biller.customer.service.CustomerService;

import java.time.LocalDateTime;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Mono<Customer> saveCustomer(CustomerDto customerDto) {
        return customerRepository.save(getCustomerEntity(customerDto))
                .onErrorMap(Exception.class, ex -> SqlServerException.handlerException(ex));
    }

    @Override
    public void deleteCustomer(Integer idCustomer) {
        customerRepository.deleteById(idCustomer)
                .onErrorMap(Exception.class, ex -> SqlServerException.handlerException(ex));
    }

    @Override
    public Mono<Customer> findCustomer(Integer idCustomer) {
        return customerRepository.findById(idCustomer)
                .onErrorMap(Exception.class, ex -> SqlServerException.handlerException(ex));
    }

    @Override
    public Flux<Customer> finAllCustomer() {
        return customerRepository.findAll();
    }

    @Transactional()
    @Override
    public Mono<Customer> customerTransaction(CustomerDto customerDto) {
        return findCustomerByDocumentNumber(customerDto.getDocumentNumber())
                .flatMap(customer -> {
                    customer.setAddress(customerDto.getAddress());
                    customer.setEmail(customerDto.getEmail().replace(" ",""));
                    customer.setPhoneNumber(customerDto.getPhoneNumber().replace(" ", ""));
                    return customerRepository.save(customer);
                })
                .switchIfEmpty(customerRepository.save(getCustomerEntity(customerDto)))
                .onErrorMap(Exception.class, ex -> SqlServerException.handlerException(ex));
    }

    @Override
    public Mono<Customer> findCustomerByDocumentNumber(String documentNumber) {
        return customerRepository.findByDocumentNumber(documentNumber)
                .onErrorMap(Exception.class, ex -> SqlServerException.handlerException(ex));
    }

    public Customer getCustomerEntity(CustomerDto customerDto){
        return Customer.builder()
                .documentNumber(customerDto.getDocumentNumber())
                .documentType(customerDto.getDocumentType())
                .name(customerDto.getName())
                .lastName(customerDto.getLastName())
                .email(customerDto.getEmail())
                .phoneNumber(customerDto.getPhoneNumber())
                .address(customerDto.getAddress()!=null ? customerDto.getAddress(): "Undefined")
                .createdAt(LocalDateTime.now())
                .build();
    }
}
