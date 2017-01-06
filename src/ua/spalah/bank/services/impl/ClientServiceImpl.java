package ua.spalah.bank.services.impl;

import ua.spalah.bank.Account;
import ua.spalah.bank.model.Bank;
import ua.spalah.bank.model.Client;
import ua.spalah.bank.services.ClientService;

import java.util.List;

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
    public Client findClientByName(Bank bank, String name) {
        List<Client> clientList = bank.getClients();
        for (Client client : clientList) {
            if (client.getClientName().equals(name)) {
                return client;
            }
        }
        return null;
    }

    @Override
    public List<Client> findAllClients(Bank bank) {
        return bank.getClients();
    }

    @Override
    public Client saveClient(Bank bank, Client client) {
        bank.getClients().add(client);
        return client;
    }

    @Override
    public void deleteClient(Bank bank, Client client) {
        List<Client> clientList = bank.getClients();
        for (Client cl : clientList) {
            if (cl.equals(client)) {
                clientList.remove(cl);
            }
        }
    }
}
