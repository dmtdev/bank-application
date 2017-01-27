package ua.spalah.bank.services.impl;

import ua.spalah.bank.exceptions.ClientAlreadyExistsException;
import ua.spalah.bank.exceptions.ClientNotFoundException;
import ua.spalah.bank.services.Account;

import ua.spalah.bank.model.Bank;
import ua.spalah.bank.model.Client;
import ua.spalah.bank.services.ClientService;

import java.util.List;
import java.util.Map;

/**
 * Created by root on 03.01.2017.
 */
public class ClientServiceImpl implements ClientService {


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
        return client.getAccountList().get((id));
    }

    @Override
    public Client findClientByName(Bank bank, String name) throws ClientNotFoundException {
//        List<Client> clientList = bank.getClients();
//        for (Client client : clientList) {
//            if (client.getClientName().equals(name)) {
//                return client;
//            }
//        }
        Map<String, Client> clientMap = bank.getAllClients();
        for (Map.Entry<String, Client> entry : clientMap.entrySet()) {
            if (entry.getKey().equals(name)) {
                return entry.getValue();
            }
        }
        throw new ClientNotFoundException(name);

    }

    @Override
    public Map<String, Client> findAllClients(Bank bank) {
        return bank.getAllClients();
    }

    @Override
    public Client saveClient(Bank bank, Client client) throws ClientAlreadyExistsException {
        Map<String, Client> clientMap = bank.getAllClients();
        Client existsClient = null;
        for (Map.Entry<String, Client> entry : clientMap.entrySet()) {
            if (entry.getKey().equals(client.getClientName())) {
                existsClient = entry.getValue();
            }
        }
        if (existsClient == null) {
            bank.getAllClients().put(client.getClientName(),client);
            return client;
        } else {
            throw new ClientAlreadyExistsException(client.getClientName());
        }
    }

    @Override
    public void saveClients(Bank bank, Map<String, Client> clients) {
        bank.setAllClients(clients);
    }

    @Override
    public void deleteClient(Bank bank, Client client) {
        Map<String ,Client> clientMap = bank.getAllClients();
        for (Map.Entry<String,Client> entry : clientMap.entrySet()) {
            if (entry.getValue().equals(client)) {
                clientMap.remove(entry.getKey());
                break;
            }
        }
    }
}
