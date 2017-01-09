package ua.spalah.bank.services.impl;

import ua.spalah.bank.Account;
import ua.spalah.bank.AccountType;
import ua.spalah.bank.extensions.AmountIsNegativeException;
import ua.spalah.bank.extensions.NotEnoughFundsException;
import ua.spalah.bank.model.CheckingAccount;
import ua.spalah.bank.model.SavingAccount;
import ua.spalah.bank.services.AccountService;

/**
 * Created by root on 03.01.2017.
 */
public class AccountServiceImpl implements AccountService {
    @Override
    public void deposit(Account account, double amount) throws AmountIsNegativeException {
        if (account == null) {
            throw new NullPointerException();
        } else {
            if (amount < 0) {
                throw new AmountIsNegativeException();
            } else {
                account.setBalance(account.getBalance() + amount);
            }
        }
    }

    @Override
    public void withdraw(Account account, double amount) throws AmountIsNegativeException, NotEnoughFundsException {
        if (account == null) {
            throw new NullPointerException();
        } else {
            if (amount < 0) {
                throw new AmountIsNegativeException();
            } else {
                AccountType accountType = account.getType();
                if (accountType == AccountType.CHECKING) {
                    account = (CheckingAccount) account;
                    // why getOversdraft() need cast after (CheckingAccount)..
                    if ((account.getBalance() + ((CheckingAccount) account).getOverdraft()) >= amount) {
                        // add overdraft and balance check?..
                        account.setBalance(account.getBalance() - amount);
                    } else {
                        throw new NotEnoughFundsException();
                    }
                } else if (accountType == AccountType.SAVING) {
                    if (account.getBalance() >= amount) {
                        account.setBalance(account.getBalance() - amount);
                    }
                }
            }
        }

    }
    @Override
    public void transfer(Account fromAccount, Account toAccount, double amount) throws AmountIsNegativeException, NotEnoughFundsException {
        if (fromAccount == null || toAccount == null) {
            throw new NullPointerException();
        } else {
            if (amount < 0) {
                throw new AmountIsNegativeException();
            } else {
                withdraw(fromAccount, amount);
                deposit(toAccount, amount);
            }
        }
    }
}
