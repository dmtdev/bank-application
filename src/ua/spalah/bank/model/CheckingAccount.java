package ua.spalah.bank.model;

import ua.spalah.bank.model.enums.AccountType;

/**
 * Created by root on 23.12.2016.
 */
public class CheckingAccount extends SavingAccount {

    private double overdraft;

    public CheckingAccount(double balans, double overdraft) {
        super(balans);
        super.setAccountType(AccountType.CHECKING);
        this.overdraft = overdraft;
    }

    public void setOverdraft(double overdraft) {
        this.overdraft = overdraft;
    }

    public double getOverdraft() {
        return overdraft;
    }

    @Override
    public String toString() {
        return "CheckingAccount <-> balance: " + getBalance() + ", overdraft: " + overdraft;
    }
}
