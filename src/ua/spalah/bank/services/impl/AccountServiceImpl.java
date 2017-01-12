package ua.spalah.bank.services.impl;

import ua.spalah.bank.exceptions.OverdraftLimitExceededException;
import ua.spalah.bank.services.Account;
import ua.spalah.bank.model.enums.AccountType;
import ua.spalah.bank.exceptions.NotEnoughFundsException;
import ua.spalah.bank.model.CheckingAccount;
import ua.spalah.bank.services.AccountService;

/**
 * Created by root on 03.01.2017.
 */
public class AccountServiceImpl implements AccountService {
    @Override
    public void deposit(Account account, double amount) {

        if (amount < 0) {
            throw new IllegalArgumentException();
        } else {
            account.setBalance(account.getBalance() + amount);
        }
    }

    @Override
    public void withdraw(Account account, double amount) throws NotEnoughFundsException{
        if (amount < 0) {
            throw new IllegalArgumentException();
        } else {
            AccountType accountType = account.getType();
                if (accountType == AccountType.CHECKING) {
                    if ((account.getBalance() + ((CheckingAccount) account).getOverdraft()) >= amount) {
                        account.setBalance(account.getBalance() - amount);
                    } else {
                        throw new OverdraftLimitExceededException("Overdraft limit exceeded");
                    }
                } else if (accountType == AccountType.SAVING) {
                    if (account.getBalance() >= amount) {
                        account.setBalance(account.getBalance() - amount);
                    } else {
                        throw new NotEnoughFundsException("Not Enough Funds");
                    }
                } else {
                throw new IllegalArgumentException("Unknown account type");
            }
        }

    }

    @Override
    public void transfer(Account fromAccount, Account toAccount, double amount) throws NotEnoughFundsException {
        withdraw(fromAccount, amount);
        deposit(toAccount, amount);
    }

}
