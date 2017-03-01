package ua.spalah.bank.services;

import ua.spalah.bank.exceptions.NotEnoughFundsException;
import ua.spalah.bank.model.Client;

/**
 * Created by root on 03.01.2017.
 */
public interface AccountService {
    void deposit(Account account, double amount) ;
    void withdraw(Account account, double amount) throws  NotEnoughFundsException;
    void transfer(Account fromAccount, Account toAccount, double amount) throws NotEnoughFundsException;
    String getAccountsInfo(Client client);
}
