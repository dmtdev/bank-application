package ua.spalah.bank;

/**
 * Created by root on 23.12.2016.
 */
public class SavingAccount extends Account {
    public SavingAccount(double balance) {
        super(balance);
    }

    @Override
    public double getMoney() {
        return 0;
    }

    @Override
    public String toString() {
        return "SavingAccount{}";
    }
}
