package ua.spalah.bank.services.impl;

import ua.spalah.bank.model.Account;
import ua.spalah.bank.services.AccountService;

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
    public void transfer(Account fromAccount, Account toAccount, double amount){
        if(fromAccount.getBalance()>=amount){
            toAccount.deposit(amount);
            fromAccount.withdraw(amount);
        }
    }
}
