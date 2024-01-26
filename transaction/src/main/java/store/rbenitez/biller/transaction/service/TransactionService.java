package store.rbenitez.biller.transaction.service;

import reactor.core.publisher.Mono;
import store.rbenitez.biller.transaction.dto.TransactionDto;
import store.rbenitez.biller.transaction.entities.Transaction;

public interface TransactionService {
    Mono<Transaction> saveTransaction (Transaction transaction);

    Mono<Transaction> getTransaction (Integer idTransaction);

    Mono<Integer> transactionCreated(TransactionDto transactionDto);
}
