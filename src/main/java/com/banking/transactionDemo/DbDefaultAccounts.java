package com.banking.transactionDemo;

import java.util.logging.Logger;

import org.springframework.stereotype.Component;

@Component
public class DbDefaultAccounts {

    private static final Logger logger = Logger.getLogger(Logger.class.getName());
    private AccountRepository accountRepository;

    public DbDefaultAccounts(AccountRepository repository) {
        accountRepository = repository;
        loadAccounts();
    }

    private void loadAccounts() {
        accountRepository.save(new Account(18395627L, 25000L));
        accountRepository.save(new Account(63902143L, 12000L));

        logger.info("$$$$$$$$ Predefined accounts added to db");
    }

}
