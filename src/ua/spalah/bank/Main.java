package ua.spalah.bank;

import ua.spalah.bank.model.Bank;
import ua.spalah.bank.model.Client;
import ua.spalah.bank.services.impl.BankServiceImpl;
import ua.spalah.bank.services.impl.ClientServiceImpl;



/**
 * Created by Kostya on 23.12.2016.
 */
public class Main {
    public static void main(String[] args) {

        BankServiceImpl bankService = new BankServiceImpl();
        Bank bank = new Bank();

        ClientServiceImpl clientService = new ClientServiceImpl();
        Client cl1 = new Client("First Client",Sex.MALE);
        Client cl2 = new Client("Second Client",Sex.MALE);
        Client cl3 = new Client("Third Client",Sex.FEMALE);

//        bankService.addListener(bank,new PrintClientListener());
//        bankService.addListener(bank,new EmailNotificationListener());
//        bankService.addListener(bank,new RegistrationLoggerListener());

        clientService.saveClient(bank,cl1);
        clientService.saveClient(bank,cl2);
        clientService.saveClient(bank,cl3);

    }
}