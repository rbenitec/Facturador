package store.rbenitez.biller.customer.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import store.rbenitez.biller.customer.controller.dto.CustomerDto;
import store.rbenitez.biller.customer.entities.Customer;
import store.rbenitez.biller.customer.exception.BusinessException;
import store.rbenitez.biller.customer.exception.RequestException;
import store.rbenitez.biller.customer.service.CustomerService;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

/**
 * Controller component for customer service.
 * @author Roberto Benitez
 */
@RestController
@RequestMapping("/customer")
@Slf4j
public class customerController {

    private final CustomerService customerService;

    public customerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Transaction customer for the pre-venta.
     * @param customerDto info customer.
     * @return Customer save in DB.
     */

    @PostMapping("/transaction")
    public Mono<ResponseEntity<Customer>> customerTransaction(@Valid @RequestBody CustomerDto customerDto){
        //log.info("[POST - saveCustomerTransaction] -> Start transaction customer : {}", customerDto.getDocumentNumber());
        validatedCustomerDto(customerDto);
        return customerService.customerTransaction(customerDto)
                .map(customer -> {
                    log.info("[POST - saveCustomerTransaction] -> Created customer with id: {} - document number: {}",customer.getId(),customer.getDocumentNumber());
                    return ResponseEntity.status(HttpStatus.CREATED).body(customer);
                })
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    private void validatedCustomerDto(CustomerDto customerDto) {
        validateEmail(customerDto.getEmail());
    }

    private void validateEmail(String email) {
        if(Objects.isNull(email)){
            throw new BusinessException("P-300", "email field is required", HttpStatus.BAD_REQUEST);
        }
        if(email.isEmpty()){
            throw new BusinessException("P-301", "email value is required", HttpStatus.BAD_REQUEST);
        }
        String patron = "[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*@[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*[.][a-zA-Z]{2,5}";
        String email_final = email.toLowerCase(Locale.ROOT);
        if (!email_final.matches(patron)) {
            throw new BusinessException("P-302","email provide is invalid",HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PostMapping("/save")
    public Mono<Customer> saveCustomer(@Valid @RequestBody CustomerDto customer){
        validatedCustomerDto(customer);
        return customerService.saveCustomer(customer).doOnNext(customerSave -> log.info("Created customer with id: {} - document number: {}",customerSave.getId(),customerSave.getDocumentNumber()));
    }

    @GetMapping("/all")
    public Flux<Customer> findAllCustomers(){
        return customerService.finAllCustomer();
    }

    @GetMapping("/get/{idCustomer}")
    public Mono<Customer> findCustomer(@PathVariable("idCustomer") Integer idCustomer){
        return customerService.findCustomer(idCustomer).doOnSuccess(customer -> log.info("Find customer with document: {} and id:{}",customer.getDocumentNumber(), customer.getId()));
    }

    @GetMapping("/get-doc/{documentNumber}")
    public Mono<Customer> findCustomerByDocument(@PathVariable("documentNumber") String documentNumber){
        return customerService.findCustomerByDocumentNumber(documentNumber);
    }

    @DeleteMapping("/delete/{idCustomer}")
    public void deleteCustomer(@PathVariable("idCustomer") Integer idCustomer){
        log.warn("Start process delete customer!");
        customerService.deleteCustomer(idCustomer);
        log.info("Customer delete successful: {}", idCustomer);
    }
}
