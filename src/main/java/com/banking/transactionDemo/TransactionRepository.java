package com.banking.transactionDemo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Integer> {

    //Transaction findByTransactionId(@Param("creditAccount") Long creditAccount);
}
