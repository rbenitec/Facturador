package store.rbenitez.biller.transaction.service;

import java.util.List;

import reactor.core.publisher.Flux;
import store.rbenitez.biller.transaction.entities.TransactionDetail;

public interface TransactionDetailService {
    Flux<TransactionDetail> saveTransactionDetails(List<TransactionDetail> transactionDetails);
}
