package com.banking.transactionDemo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "account")
@NamedQueries({
    @NamedQuery(
        name = "findByAccountId",
        query = "FROM Account a WHERE a.accountId = :accountId"
    ),
    @NamedQuery(
        name = "findByAccountNumber",
        query = "FROM Account a WHERE a.accountNumber = :accountNumber"
    )
})
public class Account implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer accountId;

    @Column(nullable = false)
    private Long accountNumber;

    @Column
    private Long balance;

    public Account() {}

    public Account(Long accountNumber, Long balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }
}
