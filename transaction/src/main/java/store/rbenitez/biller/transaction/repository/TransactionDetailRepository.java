package store.rbenitez.biller.transaction.repository;

import store.rbenitez.biller.transaction.entities.TransactionDetail;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionDetailRepository extends ReactiveCrudRepository<TransactionDetail, Integer> {
}
