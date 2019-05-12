package com.banking.transactionDemo;

import java.util.logging.Logger;

import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    private static final Logger logger = Logger.getLogger(Logger.class.getName());

    private TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository repository) {
        transactionRepository = repository;
    }

    public void simpleCreditBalance(Account creditAccount, Account debitAccount, Long amount) {

        logger.info("$$$$$$$ Transaction Service called successfully");

        if (debitAccount.getBalance() >= amount) {
            debitAccount.setBalance(debitAccount.getBalance()-amount);
            creditAccount.setBalance(creditAccount.getBalance()+amount);
            Transaction transaction = new Transaction();
            transaction.setCreditAccount(creditAccount);
            transaction.setDebitAccount(debitAccount);
            transaction.setAmount(amount);
            transactionRepository.save(transaction);
        }

    }

}
