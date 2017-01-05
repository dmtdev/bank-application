package ua.spalah.bank.services;

import ua.spalah.bank.*;
import ua.spalah.bank.Client;

import java.util.List;

/**
 * Created by root on 03.01.2017.
 */
public interface ClientService {
    Client findClientByName(Bank bank, String name);
    List<Client> findAllClients(Bank bank);
    Client saveClient(Bank bank, Client client);
    void deleteClient(Bank bank, Client client);
}
