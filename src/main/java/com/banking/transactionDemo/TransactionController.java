package com.banking.transactionDemo;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/mkdemo")
public class TransactionController {

    private static final Logger logger = Logger.getLogger(Logger.class.getName());

    private TransactionService transactionService;

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping(path = "/creditMoney")
    public @ResponseBody String creditMoney(@RequestParam Long creditAccountNumber, @RequestParam Long debitAccountNumber, @RequestParam Long amount) {

        Account creditAccount = accountRepository.findByAccountNumber(creditAccountNumber); //findByAccountNumber on DB using NamedQueries

        Account debitAccount = accountRepository.findByAccountNumber(debitAccountNumber);

        logger.info("Amount to be credited: " + amount);

        transactionService.simpleCreditBalance(creditAccount, debitAccount, amount);


        return "Transaction completed successfully!";
    }

}
