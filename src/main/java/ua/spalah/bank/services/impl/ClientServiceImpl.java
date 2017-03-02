package ua.spalah.bank.services.impl;

import ua.spalah.bank.dao.AccountDao;
import ua.spalah.bank.dao.ClientDao;
import ua.spalah.bank.exceptions.ClientAlreadyExistsException;
import ua.spalah.bank.exceptions.ClientNotFoundException;
import ua.spalah.bank.model.Bank;
import ua.spalah.bank.model.Client;
import ua.spalah.bank.services.Account;
import ua.spalah.bank.services.ClientService;

import java.sql.SQLException;
import java.util.Map;

/**
 * Created by root on 03.01.2017.
 */
public class ClientServiceImpl implements ClientService {

    private ClientDao clientDao;
    private AccountDao accountDao;

    public ClientServiceImpl(ClientDao clientDao, AccountDao accountDao) {
        this.clientDao = clientDao;
        this.accountDao = accountDao;
    }

    @Override
    public void addAccount(Client client, Account account) {
        if (client.getAccountList().size() == 0) {
            client.setActiveAccount(account);
        }
        client.getAccountList().add(account);
    }


    @Override
    public double getTotalBalance(Client client) {
        double totalBalance = 0;
        for (Account account : client.getAccountList()) {
            totalBalance += account.getBalance();
        }
        return totalBalance;
    }

    @Override
    public Account getAccountById(Client client, int id) {
        return client.getAccountList().get(id);
    }

    @Override
    public Client findClientByName(String name) throws ClientNotFoundException {
        Client client = clientDao.findByName(name);
        if (client == null) {
            //throw  new ClientNotFoundException(name);
            return null;
        } else {
            return client;
        }
    }

    //
    @Override
    public Map<String, Client> findAllClients(Bank bank) {
        return bank.getAllClients();
    }

    @Override
    public Client saveClient(Client client) throws ClientAlreadyExistsException, SQLException {
        client = clientDao.save(client);
//        Map<String, Client> clientMap = bank.getAllClients();
//        Client existsClient = null;
//        for (Map.Entry<String, Client> entry : clientMap.entrySet()) {
//            if (entry.getKey().equals(client.getClientName())) {
//                existsClient = entry.getValue();
//                throw new ClientAlreadyExistsException(client.getClientName());
//            }
//        }
//        if (existsClient == null) {
//            bank.getAllClients().put(client.getClientName(), client);
//            return client;
//        }
        return client;
    }

    @Override
    public void saveClients(Bank bank, Map<String, Client> clients) {
        bank.setAllClients(clients);
    }

    @Override
    public void deleteClient(Client client) throws SQLException {
        clientDao.delete(client.getClientId());
    }
}
