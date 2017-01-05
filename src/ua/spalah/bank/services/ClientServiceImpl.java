package ua.spalah.bank.services;

import ua.spalah.bank.Bank;
import ua.spalah.bank.Client;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 03.01.2017.
 */
public class ClientServiceImpl implements ClientService {
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
        bank.addClient(client);
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
<<<<<<<<< Temporary merge branch 1
}

=========
}
>>>>>>>>> Temporary merge branch 2
