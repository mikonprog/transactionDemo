package com.banking.transactionDemo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AccountRepository extends CrudRepository<Account, Integer> {

    Account findByAccountNumber(@Param("accountNumber") Long accountNumber);

}
