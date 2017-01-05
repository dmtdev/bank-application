package ua.spalah.bank.services;

import ua.spalah.bank.Bank;
import ua.spalah.bank.Client;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 03.01.2017.
 */
public class ClientServiceImpl implements ClientService{
    @Override
    public Client findClientByName(Bank bank, String name) {

        Client result;
        List clientList = new ArrayList<>();
        clientList = bank.getClients();
        //Почему не дает использовать Client client..
        for (Object client : clientList) {
            if (client instanceof Client) {
                if(((Client) client).getClientName().equals(name)){
                    return (Client) client;
                }
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
        List clientList = new ArrayList<>();
        clientList = bank.getClients();
        //Почему не дает использовать Client client..
        for (Object cl : clientList) {
            if (cl instanceof Client) {
                if(((Client) cl).equals(client)){
                    clientList.remove(cl);
                }
            }
        }
     }
}
/**/