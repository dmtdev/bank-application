package ua.spalah.bank;

/**
 * Created by root on 06.01.2017.
 */
public interface Account {

        AccountType getType();

        double getBalance();
        void setBalance(double balance);

}
