package ua.spalah.bank.services;

import ua.spalah.bank.accounts.Account;

/**
 * Created by root on 03.01.2017.
 */
public class AccountServiceImpl implements AccountService {
    @Override
    public void deposit(Account account, double amount) {
        account.deposit(amount);
    }

    @Override
    public void withdraw(Account account, double amount) {
        account.withdraw(amount);

    }

    @Override
    public void transfer(Account fromAccount, Account toAccount, double amount) {


    }
}
