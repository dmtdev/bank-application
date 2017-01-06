package ua.spalah.bank.services.impl;

import ua.spalah.bank.Account;
import ua.spalah.bank.services.AccountService;

/**
 * Created by root on 03.01.2017.
 */
public class AccountServiceImpl implements AccountService {
    @Override
    public void deposit(Account account, double amount) {
        account.setBalance(amount);
    }

    // TODO: 06.01.2017
    @Override
    public void withdraw(Account account, double amount) {
        //account.withdraw(amount);

    }

    // TODO: 06.01.2017
    @Override
    public void transfer(Account fromAccount, Account toAccount, double amount){
        if(fromAccount.getBalance()>=amount){
            toAccount.setBalance(amount);
            //fromAccount.withdraw(amount);
        }
    }
}
