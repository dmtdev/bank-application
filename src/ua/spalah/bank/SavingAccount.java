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
            changeBalance(sum);
        }
    }

    @Override
    public boolean checkBalance() {
        return false;
    }

    @Override
    public String toString() {
        return "SavingAccount{}";
    }
}
