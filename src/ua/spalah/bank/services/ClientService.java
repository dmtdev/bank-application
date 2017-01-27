package ua.spalah.bank.services;

import ua.spalah.bank.exceptions.ClientAlreadyExistsException;
import ua.spalah.bank.exceptions.ClientNotFoundException;
import ua.spalah.bank.model.Bank;
import ua.spalah.bank.model.Client;

import java.util.List;
import java.util.Map;

/**
 * Created by root on 03.01.2017.
 */
public interface ClientService {
    Client findClientByName(Bank bank, String name) throws ClientNotFoundException;
    Map<String,Client> findAllClients(Bank bank);
    Client saveClient(Bank bank, Client client) throws ClientAlreadyExistsException;
    void saveClients(Bank bank, Map<String,Client> clients);
    void deleteClient(Bank bank, Client client);
    void addAccount(Client client, Account account);
    double getTotalBalance(Client client);
    Account getAccountById(Client client,int id);
}
