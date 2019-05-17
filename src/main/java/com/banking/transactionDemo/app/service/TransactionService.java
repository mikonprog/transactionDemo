package com.banking.transactionDemo.app.service;

import java.util.Date;
import java.util.logging.Logger;

import com.banking.transactionDemo.app.Account;
import com.banking.transactionDemo.app.Transaction;
import com.banking.transactionDemo.app.database.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    private static final Logger logger = Logger.getLogger(Logger.class.getName());

    @Autowired
    private TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository repository) {
        transactionRepository = repository;
    }

    public TransactionService() {}

    public Transaction simpleCreditBalance(Account creditAccount, Account debitAccount, Long amount) {
        Transaction transaction = new Transaction();
        transaction.setTransactionDate(new Date());
        transaction.setCreditAccount(creditAccount.getAccountNumber());
        transaction.setDebitAccount(debitAccount.getAccountNumber());

        if (debitAccount.getBalance() >= amount) {
            debitAccount.setBalance(debitAccount.getBalance()-amount);
            creditAccount.setBalance(creditAccount.getBalance()+amount);
            transaction.setCreditAccount(creditAccount.getAccountNumber());
            transaction.setDebitAccount(debitAccount.getAccountNumber());
            transaction.setAmount(amount);
            transaction.setTransactionDate(new Date());
            transactionRepository.save(transaction);
            logger.info("$$$$$$$: Transaction completed successfully");
            return transaction;
        }
        else {
            // If a transaction fails it will still be recorded on the db with amount = null
            transactionRepository.save(transaction);
            logger.info("$$$$$$$: Transaction failed.");
            return transaction;
        }

    }

}
