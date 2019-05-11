package com.banking.transactionDemo;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/demo")
public class TransactionController {

    private static final Logger LOGGER = Logger.getLogger(Logger.class.getName());

    @Autowired
    private BankRepository bankRepository;

    @GetMapping(path = "/creditMoney")
    public @ResponseBody String creditMoney(@RequestParam Long creditAccountNumber, @RequestParam Long debitAccountNumber, @RequestParam Long amount) {

        //Retrieve account by number
        Account creditAccount = new Account(); //findByAccountNumber on DB
        creditAccount.setAccountNumber(creditAccountNumber);
        creditAccount.setBalance(15000L);
        Account debitAccount = new Account(); //findByAccountNumber on DB
        debitAccount.setAccountNumber(debitAccountNumber);
        debitAccount.setBalance(8500L);

        LOGGER.info("Amount to be credited: " + amount);


        Transaction transaction = new Transaction();
        transaction.setCreditAccount(creditAccount);
        transaction.setDebitAccount(debitAccount);
        transaction.setAmount(amount);
        //bankRepository.save(transaction);

//        if (debitAccount.getBalance() >= amount) {
//            debitAccount.setBalance(debitAccount.getBalance()-amount);
//            creditAccount.setBalance(creditAccount.getBalance()+amount);
//        }

        return "Transaction completed successfully!";
    }

}
