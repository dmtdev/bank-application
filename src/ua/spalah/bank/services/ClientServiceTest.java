package ua.spalah.bank.services;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ua.spalah.bank.exceptions.ClientNotFoundException;
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
public class ClientServiceTest extends Assert {

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

    @Test(expected = ClientNotFoundException.class)
    public void testFindClientByName() throws Exception {
        List<Client> clients = bank.getClients();
        Client client = clientService.findClientByName(bank, "Kostya");
        assertNotNull("Client is found", client);
        assertEquals(client.getClientName(),"Kostya");
        assertEquals(client.getCity(),"township");
        assertEquals(client.getEmail(),"q@w.wwewe");
        client =  clientService.findClientByName(bank,"asdasd");
        assertNull("Client mot found",client);
    }

    @Test
    public void testFindfAllClients() throws Exception {
        List<Client> clients0 = bank.getClients();
        List<Client> clients1 = clientService.findAllClients(bank);
        assertEquals(clients0.get(0).getEmail(),clients1.get(0).getEmail());
        assertEquals(clients0.get(0).getTel(),clients1.get(0).getTel());
    }

    @Test
    public void testSaveClient() throws Exception {
        Client client0 = new Client("Test Name",Sex.MALE);
        Client client1 = clientService.saveClient(bank,client0);
        assertEquals(client0.getSex(),client1.getSex());
        assertEquals(client0.getClientName(),client1.getClientName());
    }
}