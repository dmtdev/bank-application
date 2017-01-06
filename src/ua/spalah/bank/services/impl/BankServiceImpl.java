package ua.spalah.bank.services.impl;

import ua.spalah.bank.model.Bank;
import ua.spalah.bank.model.Client;
import ua.spalah.bank.services.BankService;

/**
 * Created by root on 06.01.2017.
 */
public class BankServiceImpl implements BankService {
    @Override
    public String getClientInfo(Bank bank, String clientName) {

        for (Client client : bank.getClients()) {
            if (client.getClientName().equals(clientName)) {
                return client.toString();
            }
        }
        return "client not found";
    }
}