package com.banking.transactionDemo.app.database;

import java.util.List;

import com.banking.transactionDemo.app.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Integer> {

    List<Transaction> findAllByDebitAccount(Long debitAccount);

}
