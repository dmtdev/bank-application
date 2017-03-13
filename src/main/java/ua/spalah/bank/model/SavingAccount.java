package ua.spalah.bank.model;

import ua.spalah.bank.model.enums.AccountType;
import ua.spalah.bank.services.Account;

import javax.persistence.*;

/**
 * Created by root on 23.12.2016.
 */
@Entity
@Table(name="ACCOUNTS")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "ACCOUNT_TYPE")
@DiscriminatorValue("SAVING")
public class SavingAccount implements Account {

    @Id
    //@Column(name = "ACCOUNT_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @OneToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private long id;

    @Column(name="DEPOSIT")
    private double balans;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    public SavingAccount(double balans) {
        this.balans = balans;
        this.accountType = AccountType.SAVING;
    }

    public SavingAccount() {
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public AccountType getType() {
        return accountType;
    }

    @Override
    public double getBalance() {
        return balans;
    }

    @Override
    public void setBalance(double balance) {
        this.balans=balance;
    }


    @Override
    public String toString() {
        return "SavingAccount <-> balance: " + getBalance();
    }



}
