package ua.spalah.bank.commands;

import ua.spalah.bank.exceptions.ClientAlreadyExistsException;
import ua.spalah.bank.exceptions.NetworkException;
import ua.spalah.bank.io.sockets.ConsoleIO;
import ua.spalah.bank.io.sockets.IO;
import ua.spalah.bank.io.sockets.SocketIO;
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

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

/**
 * Created by Dmitry on 10.01.2017.
 */
public class BankServerCommander {
    // хранит в себе банк с кототорым мы работаем
    public static Bank currentBank;

    public static String serverAnswer;
    private final IO io;
    private final SocketIO socketIO;

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

    public BankServerCommander(SocketIO socketIO) {

        this.io = socketIO;
        this.socketIO=socketIO;
        init();
    }

    private void initCommands(){
        commands = new Command[]{
                new FindClientCommand(clientService, io),
                new GetAccountsCommand(accountService,io),
                new AddAccountCommand(clientService,io),
                new SetActiveAccountCommander(clientService, accountService, io),
                new DepositCommand(accountService, io),
                new WithdrawCommand(accountService, io),
                new TransferCommand(clientService, accountService, io),
                new AddClientCommand(clientService, accountService, io),
                new RemoveClientCommand(clientService, io),
                new GetBankInfoCommand(bankReportService, io),
                new ShowMenuCommand(io),
                new ExitCommand(io),
        };
    }

    public static String[] showMenu() {

        String[] menu = new String[commands.length];
        for (int i = 0; i < commands.length; i++) {
            menu[i] = (i + 1) + ". " + commands[i].getCommandInfo();
        }
        return menu;
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
                System.out.println(e.getMessage());
            }
        }
        bank.setAllClients(clientMap);
        currentBank = bank;
    }



    public void run() {

        boolean exit = false;

        try {
            ServerSocket ss = new ServerSocket(5050);
            System.out.println("Waiting for a client...");
            Socket socket = ss.accept();
            socketIO.init(socket);
            System.out.println("Got a client. Run bank!");
        } catch (IOException e) {
            new NetworkException("Some problems with network");
        }
        initCommands();

        socketIO.write("Enter command number (1-12)");
        socketIO.write(String.join("\n",showMenu()));
        //Scanner scanner = new Scanner(System.in);
        do{
            try {
                int command = Integer.parseInt(io.read());
                socketIO.write("> " + commands[command - 1].getCommandInfo());
                try {
                    commands[command - 1].execute();
                } catch (ClientAlreadyExistsException e) {
                    e.printStackTrace();
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                socketIO.write("Wrong command number");
            } catch (IndexOutOfBoundsException e) {
                socketIO.write("Wrong account number");
            } catch (NumberFormatException e) {
                socketIO.write("This is not a number");
            }
            //io.read();
        }
        while(!exit);

    }

    public static void main(String[] args) {
        BankServerCommander bankServerCommander = new BankServerCommander(new SocketIO());
        bankServerCommander.run();
    }
}
