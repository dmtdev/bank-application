package ua.spalah.bank;

/**
 * Created by root on 23.12.2016.
 */
public class SavingAccount extends Account {
    public SavingAccount(double balance) {
        super(balance);
    }

    @Override
    public void takeMoney(double sum) {
        if (super.getBalance() > sum) {
            changeBalance(super.getBalance(sum));
        }
    }

    @Override
    public String toString() {
        return "SavingAccount{}";
    }
}
