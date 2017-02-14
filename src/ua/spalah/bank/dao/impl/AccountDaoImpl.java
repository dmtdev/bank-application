package ua.spalah.bank.dao.impl;

import ua.spalah.bank.dao.AccountDao;
import ua.spalah.bank.services.Account;

import java.util.List;

/**
 * Created by root on 14.02.2017.
 */
public class AccountDaoImpl implements AccountDao {
    @Override
    public Account save(long clientId, Account Account) {
        return null;
    }

    @Override
    public Account update(long clientId, Account Account) {
        return null;
    }

    @Override
    public Account saveOrUpdate(long clientId, Account Account) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public Account find(long id) {
        return null;
    }

    @Override
    public List<Account> findAll() {
        return null;
    }

    @Override
    public List<Account> findByClientId(long clientId) {
        return null;
    }

    @Override
    public Account findActiveAccountByClientName(String clientName) {
        return null;
    }
}
