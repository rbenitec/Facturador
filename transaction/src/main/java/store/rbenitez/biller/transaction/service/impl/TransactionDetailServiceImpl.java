package store.rbenitez.biller.transaction.service.impl;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import store.rbenitez.biller.transaction.entities.TransactionDetail;
import store.rbenitez.biller.transaction.repository.TransactionDetailRepository;
import store.rbenitez.biller.transaction.service.TransactionDetailService;

import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TransactionDetailServiceImpl implements TransactionDetailService {

    private final TransactionDetailRepository transactionDetailRepository;

    public TransactionDetailServiceImpl(TransactionDetailRepository transactionDetailRepository) {
        this.transactionDetailRepository = transactionDetailRepository;
    }

    @Override
    public Flux<TransactionDetail> saveTransactionDetails(List<TransactionDetail> transactionDetails) {
        return transactionDetailRepository.saveAll(transactionDetails);
    }
}
