package store.rbenitez.biller.transaction.repository;

import store.rbenitez.biller.transaction.entities.Transaction;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends ReactiveCrudRepository<Transaction, Integer> {
}
