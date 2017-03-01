package ua.spalah.bank.model;

import ua.spalah.bank.model.enums.AccountType;
import ua.spalah.bank.services.Account;

/**
 * Created by root on 23.12.2016.
 */
public class SavingAccount implements Account {

    private double balans;
    private AccountType accountType;

    public SavingAccount(double balans) {
        this.balans = balans;
        this.accountType = AccountType.SAVING;
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
