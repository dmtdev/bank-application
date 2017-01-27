package ua.spalah.bank.services.impl;

import ua.spalah.bank.exceptions.ClientNotFoundException;
import ua.spalah.bank.model.Bank;
import ua.spalah.bank.model.Client;
import ua.spalah.bank.services.BankService;

import java.util.Map;

/**
 * Created by root on 06.01.2017.
 */
public class BankServiceImpl implements BankService {
    @Override
    public String getClientInfo(Bank bank, String clientName) throws ClientNotFoundException {
        Map<String,Client> clientMap = bank.getAllClients();

        return clientMap.get(clientName).toString();
        //        for (Client client : bank.getClients()) {
//            if (client.getClientName().equals(clientName)) {
//                return client.toString();
//            }
//        }
        //throw new ClientNotFoundException(clientName);
    }
    public Client getClientByName(Bank bank, String clientName) throws ClientNotFoundException {
        Map<String,Client> clientMap = bank.getAllClients();

//        for (Client client : bank.getClients()) {
//            if (client.getClientName().equals(clientName)) {
//                return client;
//            }
//        }
//        throw new ClientNotFoundException(clientName);
        return clientMap.get(clientName);
        //        for (Client client : bank.getClients()) {
//            if (client.getClientName().equals(clientName)) {
//                return client.toString();
//            }
//        }
        //throw new ClientNotFoundException(clientName);
    }
}
