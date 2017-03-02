package ua.spalah.bank.services;

import ua.spalah.bank.exceptions.ClientAlreadyExistsException;
import ua.spalah.bank.exceptions.ClientNotFoundException;
import ua.spalah.bank.model.Bank;
import ua.spalah.bank.model.Client;

import java.sql.SQLException;
import java.util.Map;

/**
 * Created by root on 03.01.2017.
 */
public interface ClientService {
    Client findClientByName(String name) throws ClientNotFoundException;
    Map<String,Client> findAllClients(Bank bank);
    Client saveClient(Client client) throws ClientAlreadyExistsException, SQLException;
    void saveClients(Bank bank, Map<String,Client> clients);
    void deleteClient(Client client) throws SQLException;
    void addAccount(Client client, Account account);
    double getTotalBalance(Client client);
    Account getAccountById(Client client,int id);
}
