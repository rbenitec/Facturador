package store.rbenitez.biller.transaction.controller;

import reactor.core.publisher.Mono;
import store.rbenitez.biller.transaction.client.CustomerClient;
import store.rbenitez.biller.transaction.dto.TransactionDto;
import store.rbenitez.biller.transaction.entities.Transaction;
import store.rbenitez.biller.transaction.service.TransactionDetailService;
import store.rbenitez.biller.transaction.service.TransactionService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Value("${config.client-generic.documentNumber}")
    private String documentClient;

    private final TransactionService transactionService;
    private final TransactionDetailService transactionDetailService;

    public TransactionController(TransactionService transactionService, TransactionDetailService transactionDetailService) {
        this.transactionService = transactionService;
        this.transactionDetailService = transactionDetailService;
    }
    @PostMapping("/created")
    public Mono<Integer> transactionCreated(@RequestBody TransactionDto transactionDto){
        return transactionService.transactionCreated(transactionDto);
    }

    @GetMapping("/get/{idTransaction}")
    public Mono<Transaction> getTransacationById(@PathVariable("idTransaction") Integer idTransaction){
        return transactionService.getTransaction(idTransaction);
    }
}
