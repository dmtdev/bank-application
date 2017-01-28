package ua.spalah.bank.tests;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ua.spalah.bank.commands.BankCommander;
import ua.spalah.bank.exceptions.ClientAlreadyExistsException;
import ua.spalah.bank.exceptions.ClientNotFoundException;
import ua.spalah.bank.model.Bank;
import ua.spalah.bank.model.CheckingAccount;
import ua.spalah.bank.model.Client;
import ua.spalah.bank.model.SavingAccount;
import ua.spalah.bank.model.enums.Sex;
import ua.spalah.bank.services.AccountService;
import ua.spalah.bank.services.BankReportService;
import ua.spalah.bank.services.ClientService;
import ua.spalah.bank.services.impl.AccountServiceImpl;
import ua.spalah.bank.services.impl.BankReportServiceImpl;
import ua.spalah.bank.services.impl.ClientServiceImpl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by root on 27.01.2017.
 */
public class ClientServiceTest extends Assert {

    Bank bank = new Bank();
    ClientService clientService = new ClientServiceImpl();
    AccountService accountService = new AccountServiceImpl();
    BankReportService bankReportService = new BankReportServiceImpl();
    Map<String, Client> clientMap = new HashMap<>();

    List<String> clLines = new ArrayList<>();

    @Before
    public void init() {
//        Client cl0 = new Client("Dima", Sex.MALE, "q@w.ww", "+358693216", "city");
//        Client cl1 = new Client("Misha", Sex.MALE, "q@w.wq", "+358691142", "city");
//        Client cl2 = new Client("Masha", Sex.FEMALE, "q@eew.ww", "+35869221", "township");
//        Client cl3 = new Client("Kostya", Sex.MALE, "q@w.wwewe", "+35869000", "township");
//
//        clientService.saveClient(bank, cl0);
//        clientService.saveClient(bank, cl1);
//        clientService.saveClient(bank, cl2);
//        clientService.saveClient(bank, cl3);
//
//        clientService.addAccount(cl0, new SavingAccount(100));
//        clientService.addAccount(cl0, new CheckingAccount(100, 50));
//        clientService.addAccount(cl1, new SavingAccount(1000));
//
//        clientService.addAccount(cl2, new SavingAccount(100));
//        clientService.addAccount(cl2, new SavingAccount(10000));
//        clientService.addAccount(cl2, new CheckingAccount(10000, 500));
//
//        clientService.addAccount(cl3, new SavingAccount(100));
//        clientService.addAccount(cl3, new CheckingAccount(10000, 500));


//        try {
//            //System.out.println(String.valueOf(ClassLoader.getSystemResource("clients.txt")));
//            //clLines = Files.readAllLines(Paths.get(String.valueOf(ClassLoader.getSystemResource("clients.txt"))), StandardCharsets.UTF_8);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        Scanner scanner = new Scanner(ClassLoader.getSystemResourceAsStream("clients.txt"));
        while(scanner.hasNext())
        {
            System.out.println(scanner.nextLine());
        }
        for (int i = 0; i < clLines.size(); i++) {
            String[] clientData = clLines.get(i).split("::");
            clientMap.put(clientData[0], new Client(clientData[0], (clientData[1].equals("Male") ? Sex.MALE : Sex.FEMALE), clientData[2], clientData[3], clientData[4]));
        }

        List<String> accLines = new ArrayList<>();
        try {
            accLines = Files.readAllLines(Paths.get(String.valueOf(ClassLoader.getSystemResource("accounts.txt"))), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < accLines.size(); i++) {
            String[] clientData = accLines.get(i).split("::");
            if (clientMap.containsKey(clientData[0])) {
                if (clientData[1].equals("SA")) {
                    clientService.addAccount(clientMap.get(clientData[0]), new SavingAccount(Double.parseDouble(clientData[2])));
                }
                if (clientData[1].equals("CA")) {
                    clientService.addAccount(clientMap.get(clientData[0]), new CheckingAccount(Double.parseDouble(clientData[2]), Double.parseDouble(clientData[2])));
                }

            }
        }

        bank.setAllClients(clientMap);
    }

//    @After
//    public void clear() {
//        bank.getAllClients().clear();
//    }

    @Test //(expected = ClientNotFoundException.class)
    public void testFindClientByName() throws Exception {
        Client client = clientService.findClientByName(bank, "Kostya");
        assertNotNull("Client is found", client);
        assertEquals(client.getClientName(), "Kostya");
        assertEquals(client.getCity(), "township");
        assertEquals(client.getEmail(), "q@w.wwewe");
    }
    @Test(expected = ClientNotFoundException.class)
    public void testFindClientByNameClientNotFound() throws Exception {
        Client client = clientService.findClientByName(bank, "NotClient");
        //assert("Client not found", client);
    }


    @Test
    public void testFindAllClients() throws Exception {
        Map<String,Client> clientMap0 = bank.getAllClients();
        Map<String,Client> clientMap1 = clientService.findAllClients(bank);
        assertEquals(clientMap0, clientMap1);
    }

    @Test(expected = ClientAlreadyExistsException.class)
    public void testSaveClientClientAlreadyExists() throws Exception {
        Client client0 = new Client("Kostya", Sex.MALE);
        clientService.saveClient(bank, client0);
    }

    @Test
    public void testSaveClientNewClient() throws Exception {
        Client client0 = new Client("NewClient", Sex.MALE);
        Client client1 = clientService.saveClient(bank, client0);
        assertEquals(client0,client1);
    }
    @Test(expected=NullPointerException.class)
    public void testSaveClientClientIsNull() throws Exception {
        Client client0 = null;
        clientService.saveClient(bank, client0);
    }
    @Test(expected=NullPointerException.class)
    public void testSaveClientBankIsNull() throws Exception {
        Client client0 = new Client("NewClient", Sex.MALE);
        clientService.saveClient(null, client0);
    }
}