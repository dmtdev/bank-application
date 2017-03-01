package ua.spalah.bank.dao;

import ua.spalah.bank.services.Account;

import java.util.List;

/**
 * Created by root on 14.02.2017.
 */
public interface AccountDao {
    Account save(long clientId, Account Account);
    Account update(long clientId, Account Account);
    Account saveOrUpdate(long clientId, Account Account);
    void delete(long id);
    Account find(long id);
    List<Account> findAll();
    List<Account> findByClientId(long clientId);
    Account findActiveAccountByClientName(String clientName);
}
