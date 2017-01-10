package ua.spalah.bank.model;

import ua.spalah.bank.services.Account;
import ua.spalah.bank.model.enums.AccountType;

/**
 * Created by root on 23.12.2016.
 */
public class SavingAccount implements Account {

    private double balans;
    private AccountType accountType;

    public SavingAccount(double balans, AccountType accountType) {
        this.balans = balans;
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


    //    public SavingAccount(double balance) {
//        super(balance);
//    }
//
//    @Override
//    public void withdraw(double momey) {
//        if (super.getBalance() >= momey){
//            super.setBalance(super.getBalance()-momey);
//        }
//    }
    @Override
    public String toString() {
        return "SavingAccount <-> balance: " + getBalance();
    }



}
