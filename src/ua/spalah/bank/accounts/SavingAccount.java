package ua.spalah.bank.accounts;

/**
 * Created by root on 23.12.2016.
 */
public class SavingAccount extends Account {
    public SavingAccount(double balance) {
        super(balance);
    }

    @Override
    public void withdraw(double momey) {
        if (super.getBalance() >= momey){
            super.setBalance(super.getBalance()-momey);
        }
    }
    @Override
    public String toString() {
        return "SavingAccount balance: " + getBalance();
    }
}
