package ua.spalah.bank.services;

import ua.spalah.bank.Account;
import ua.spalah.bank.extensions.AmountIsNegativeException;
import ua.spalah.bank.extensions.NotEnoughFundsException;

/**
 * Created by root on 03.01.2017.
 */
public interface AccountService {
    void deposit(Account account, double amount) throws IllegalArgumentException;
    void withdraw(Account account, double amount) throws IllegalArgumentException, NotEnoughFundsException;
    void transfer(Account fromAccount, Account toAccount, double amount) throws NotEnoughFundsException, IllegalArgumentException;
}
