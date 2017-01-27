package ua.spalah.bank.services;

import junit.framework.TestCase;
//import junit.framework.
import org.junit.Before;
import org.junit.Test;
import ua.spalah.bank.model.Bank;
import ua.spalah.bank.model.CheckingAccount;
import ua.spalah.bank.model.Client;
import ua.spalah.bank.model.SavingAccount;
import ua.spalah.bank.model.enums.Sex;
import ua.spalah.bank.services.impl.AccountServiceImpl;
import ua.spalah.bank.services.impl.BankReportServiceImpl;
import ua.spalah.bank.services.impl.ClientServiceImpl;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by root on 27.01.2017.
 */
public class ClientServiceTest {

    Bank bank = new Bank();
    ClientService clientService = new ClientServiceImpl();
    AccountService accountService = new AccountServiceImpl();
    BankReportService bankReportService = new BankReportServiceImpl();

    @Before
    public void init() {
        Client cl0 = new Client("Dima", Sex.MALE, "q@w.ww", "+358693216", "city");
        Client cl1 = new Client("Misha", Sex.MALE, "q@w.wq", "+358691142", "city");
        Client cl2 = new Client("Masha", Sex.FEMALE, "q@eew.ww", "+35869221", "township");
        Client cl3 = new Client("Kostya", Sex.MALE, "q@w.wwewe", "+35869000", "township");

        clientService.saveClient(bank, cl0);
        clientService.saveClient(bank, cl1);
        clientService.saveClient(bank, cl2);
        clientService.saveClient(bank, cl3);

        clientService.addAccount(cl0, new SavingAccount(100));
        clientService.addAccount(cl0, new CheckingAccount(100, 50));
        clientService.addAccount(cl1, new SavingAccount(1000));

        clientService.addAccount(cl2, new SavingAccount(100));
        clientService.addAccount(cl2, new SavingAccount(10000));
        clientService.addAccount(cl2, new CheckingAccount(10000, 500));

        clientService.addAccount(cl3, new SavingAccount(100));
        clientService.addAccount(cl3, new CheckingAccount(10000, 500));
    }

    @Test
    public void testfindClientByName() throws Exception {
        List<Client> clients = bank.getClients();
        Client client = clientService.findClientByName(bank, "Kostya");
        assertNotNull("Client is not null", client);
    }

}