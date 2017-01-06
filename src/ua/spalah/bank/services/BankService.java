package ua.spalah.bank.services;

import ua.spalah.bank.listeners.ClientRegistrationListener;
import ua.spalah.bank.model.Bank;
import ua.spalah.bank.model.Client;

/**
 * Created by root on 06.01.2017.
 */
public interface BankService {
    String getClientInfo(Bank bank, String clientName);
//    void addClient(Bank bank,Client client);
//    void addListener(Bank bank,ClientRegistrationListener listener);
}
