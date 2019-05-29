package com.banking.transactionDemo.app.controller;

import java.util.List;
import java.util.logging.Logger;

import com.banking.transactionDemo.app.Account;
import com.banking.transactionDemo.app.Transaction;
import com.banking.transactionDemo.app.database.AccountRepository;
import com.banking.transactionDemo.app.database.TransactionRepository;
import com.banking.transactionDemo.app.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/mkdemo")
public class TransactionController {

    private static final Logger logger = Logger.getLogger(Logger.class.getName());

    private final TransactionService transactionService;

    private final AccountRepository accountRepository;

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionController(TransactionService transactionService, AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.transactionService = transactionService;
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @GetMapping(path = "/creditMoney")
    public @ResponseBody String creditMoney(@RequestParam Long creditAccountNumber, @RequestParam Long debitAccountNumber, @RequestParam Long amount) {

        final Account creditAccount = accountRepository.findByAccountNumber(creditAccountNumber);
        final Account debitAccount = accountRepository.findByAccountNumber(debitAccountNumber);

        logger.info("$$$$$$$: Amount to be credited: " + amount.toString());

        final Transaction transaction = transactionService.simpleCreditBalance(creditAccount, debitAccount, amount);
        if (transaction.getAmount() != null)
            return "Transaction completed successfully!";
        else
            return "Transaction failed.";

    }

    @GetMapping("/history")
    public List<Transaction> findByAccount(@RequestParam Long debitAccount) {
        return transactionRepository.findAllByDebitAccount(debitAccount);
    }

    @GetMapping("/all")
    public Iterable<Transaction> all() {
        return transactionRepository.findAll();
    }

}
