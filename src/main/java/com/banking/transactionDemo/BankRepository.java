package com.banking.transactionDemo;

import org.springframework.data.repository.CrudRepository;

public interface BankRepository extends CrudRepository<Account, Integer> {

}
