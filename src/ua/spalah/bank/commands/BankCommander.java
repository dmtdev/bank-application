package ua.spalah.bank.commands;

import ua.spalah.bank.exceptions.ClientAlreadyExistsException;
import ua.spalah.bank.model.Bank;
import ua.spalah.bank.model.CheckingAccount;
import ua.spalah.bank.model.Client;
import ua.spalah.bank.model.SavingAccount;
import ua.spalah.bank.model.enums.Sex;
import ua.spalah.bank.services.Account;
import ua.spalah.bank.services.AccountService;
import ua.spalah.bank.services.BankReportService;
import ua.spalah.bank.services.ClientService;
import ua.spalah.bank.services.impl.AccountServiceImpl;
import ua.spalah.bank.services.impl.BankReportServiceImpl;
import ua.spalah.bank.services.impl.ClientServiceImpl;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by Dmitry on 10.01.2017.
 */
public class BankCommander {
    // хранит в себе банк с кототорым мы работаем
    public static Bank currentBank;

    private Bank bank = new Bank();
    private ClientService clientService = new ClientServiceImpl();
    private AccountService accountService = new AccountServiceImpl();
    private BankReportService bankReportService = new BankReportServiceImpl();
    private Map<String, Client> clientMap = new HashMap<>();

    // хранит в себе клиента с которым мы работаем в данный момент
    public static Client currentClient;

    private static Command[] commands;

    public static Command[] getCommands() {
        return commands;
    }

    public BankCommander() {
        init();
        showMenu();
    }

    private void initCommands(){
        commands = new Command[]{
                new FindClientCommand(clientService),
                new GetAccountsCommand(accountService),
                new AddAccountCommand(clientService),
                new SetActiveAccountCommander(clientService, accountService),
                new DepositCommand(accountService),
                new WithdrawCommand(accountService),
                new TransferCommand(clientService, accountService),
                new AddClientCommand(clientService, accountService),
                new RemoveClientCommand(clientService),
                new GetBankInfoCommand(bankReportService),
                new ShowMenuCommand(),
                new ExitCommand(),
                //new ReturnClientsMapCommand(bankReportService)
        };
    }

    public static void showMenu() {
        for (int i = 0; i < commands.length; i++) {
            System.out.println((i + 1) + ". " + commands[i].getCommandInfo());
        }
    }

    private void init() {
        Scanner scanner = new Scanner(ClassLoader.getSystemResourceAsStream("clients.txt"));
        while (scanner.hasNext()) {
            String[] clientData = scanner.nextLine().split("::");
            clientMap.put(clientData[0], new Client(clientData[0], (clientData[1].equals("MALE") ? Sex.MALE : Sex.FEMALE), clientData[2], clientData[3], clientData[4]));
        }
        scanner = new Scanner(ClassLoader.getSystemResourceAsStream("accounts.txt"));
        while (scanner.hasNext()) {
            String[] clientData = scanner.nextLine().split("::");
            if (clientMap.containsKey(clientData[0])) {
                if (clientData[1].equals("SA")) {
                    clientService.addAccount(clientMap.get(clientData[0]), new SavingAccount(Double.parseDouble(clientData[2])));
                }
                else if (clientData[1].equals("CA")) {
                    clientService.addAccount(clientMap.get(clientData[0]), new CheckingAccount(Double.parseDouble(clientData[2]), Double.parseDouble(clientData[2])));
                }

            }
        }
        for (Map.Entry<String, Client> entry : clientMap.entrySet()) {
            Client client = entry.getValue();
            try {
                clientService.saveClient(bank, client);
            } catch (ClientAlreadyExistsException e) {
                e.printStackTrace();
            }
        }
        bank.setAllClients(clientMap);
        initCommands();
        currentBank = bank;
  }



    public void run() {
        while (true) {
            System.out.println("Enter command number (1-12)");
            Scanner scanner = new Scanner(System.in);
            try {
                int command = Integer.parseInt(scanner.nextLine());
                System.out.println("> " + commands[command - 1].getCommandInfo());
                try {
                    commands[command - 1].execute();
                } catch (ClientAlreadyExistsException e) {
                    e.printStackTrace();
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Wrong command number");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Wrong account number");
            } catch (NumberFormatException e) {
                System.out.println("This is not a number");
            }
        }
    }

    public static void main(String[] args) {
        BankCommander bankCommander = new BankCommander();
        bankCommander.run();
    }
}
