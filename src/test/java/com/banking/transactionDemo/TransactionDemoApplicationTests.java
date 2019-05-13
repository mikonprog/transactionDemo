package com.banking.transactionDemo;

import java.util.Date;
import java.util.List;

import com.banking.transactionDemo.app.Account;
import com.banking.transactionDemo.app.Transaction;
import com.banking.transactionDemo.app.TransactionDemoApplication;
import com.banking.transactionDemo.app.database.AccountRepository;
import com.banking.transactionDemo.app.database.TransactionRepository;
import com.banking.transactionDemo.app.service.TransactionService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
	classes = {TransactionDemoApplication.class},
	loader = AnnotationConfigContextLoader.class
)
public class TransactionDemoApplicationTests {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private TransactionService transactionService;

	private Account creditAccount;

	private Account debitAccount;

	private Transaction testTransaction;

	private static final Long amount = 350L;

	@Before
	public void setup() {
		transactionService = new TransactionService(transactionRepository);

		creditAccount = new Account(18395627L, 25000L);
		debitAccount = new Account(13902143L, 12000L);

		//Accounts are saved in the db when firing up the project from DBDefaultAccounts Class

		testTransaction = new Transaction();
		testTransaction.setTransactionDate(new Date());
		testTransaction.setCreditAccount(creditAccount.getAccountNumber());
		testTransaction.setDebitAccount(debitAccount.getAccountNumber());
		testTransaction.setAmount(amount);

		transactionRepository.save(testTransaction);

	}

	@Test
	public void testExistingAccounts() {
		//Given
		Account account1 = new Account(18395627L, 25000L);

		//When
		final Iterable<Account> all = accountRepository.findAll();
		final Account byAccountNumber = accountRepository.findByAccountNumber(account1.getAccountNumber());

		//Then
		Assert.assertNotNull(all);
		Assert.assertEquals(account1.getAccountNumber(), byAccountNumber.getAccountNumber());

	}

	@Test
	public void testAvailableSimpleTransaction() {
		//Given

		//When
		final Transaction transaction = transactionService.simpleCreditBalance(creditAccount, debitAccount, amount);

		//Then
		Assert.assertNotNull(transaction);
		Assert.assertEquals(amount, transaction.getAmount());
	}

	@Test
	public void testInSufficientTransaction() {
		//Given
		final Long expectedTransactionAmount = 0L;
		final Account inSufficient = new Account(23401924L, 200L);

		//When
		final Transaction failedTransaction = transactionService.simpleCreditBalance(creditAccount, inSufficient, amount);

		//Then
		Assert.assertNotNull(failedTransaction);
		Assert.assertEquals(expectedTransactionAmount, failedTransaction.getAmount());
	}

	@Test
	public void testTransactionHistory() {
		//Given

		//When
		final Iterable<Transaction> allTransactions = transactionRepository.findAll();
		final List<Transaction> allByDebitAccountTransactions = transactionRepository.findAllByDebitAccount(debitAccount.getAccountNumber());

		//Then
		Assert.assertNotNull(allTransactions);
		Assert.assertNotNull(allByDebitAccountTransactions);
		Assert.assertEquals(testTransaction.getCreditAccount(), allByDebitAccountTransactions.get(0).getCreditAccount());

	}
}
