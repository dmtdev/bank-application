package ua.spalah.bank.services;
import ua.spalah.bank.accounts.*;

/**
 * Created by root on 03.01.2017.
 */
public interface AccountService {
    void deposit(Account account, double amount);
    void withdraw(Account account, double amount);
    void transfer(Account fromAccount, Account toAccount, double amount);
}
