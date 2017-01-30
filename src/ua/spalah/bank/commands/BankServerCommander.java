package ua.spalah.bank.commands;

import ua.spalah.bank.exceptions.ClientAlreadyExistsException;
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

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

/**
 * Created by Dmitry on 10.01.2017.
 */
public class BankServerCommander {
    // хранит в себе банк с кототорым мы работаем
    public static Bank currentBank;

    // хранит в себе клиента с которым мы работаем в данный момент
    public static Client currentClient;

    private static Command[] commands;

    public static Command[] getCommands() {
        return commands;
    }

    public BankServerCommander() {
        init();
        showMenu();
    }
    private void initServer(){

    }


    private void init() {
        Bank bank = new Bank();
        ClientService clientService = new ClientServiceImpl();
        AccountService accountService = new AccountServiceImpl();
        BankReportService bankReportService = new BankReportServiceImpl();
        Map<String, Client> clientMap = new HashMap<>();

        Scanner scanner = new Scanner(ClassLoader.getSystemResourceAsStream("clients.txt"));
        List<String> clientsFile = new ArrayList<>();
        while (scanner.hasNext()) {
            clientsFile.add(scanner.nextLine());
        }

        for (int i = 0; i < clientsFile.size(); i++) {
            String[] clientData = clientsFile.get(i).split("::");
            clientMap.put(clientData[0], new Client(clientData[0], (clientData[1].equals("MALE") ? Sex.MALE : Sex.FEMALE), clientData[2], clientData[3], clientData[4]));
        }

        scanner = new Scanner(ClassLoader.getSystemResourceAsStream("accounts.txt"));
        List<String> accountsFile = new ArrayList<>();
        while (scanner.hasNext()) {
            accountsFile.add(scanner.nextLine());
        }

        for (int i = 0; i < accountsFile.size(); i++) {
            String[] clientData = accountsFile.get(i).split("::");
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
        bank.setAllClients(clientMap);
        initCommands();


        currentBank = bank;

    }
    public static void initCommands(){

    }
    public static void showMenu() {
        for (int i = 0; i < commands.length; i++) {
            System.out.println((i + 1) + ". " + commands[i].getCommandInfo());
        }
    }

    public void run() {

        int port = 5050; // случайный порт (может быть любое число от 1025 до 65535)
        try {
            ServerSocket ss = new ServerSocket(port); // создаем сокет сервера и привязываем его к вышеуказанному порту
            System.out.println("Waiting for a client...");

            Socket socket = ss.accept(); // заставляем сервер ждать подключений и выводим сообщение, когда кто-то связался с сервером
            System.out.println("Got a client :) Finally, someone saw me!\n");

            // Берем входной и выходной потоки сокета, теперь можем получать и отсылать данные клиенту.
            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();

            // Конвертируем потоки в другой тип, чтоб легче обрабатывать текстовые сообщения.
            DataInputStream in = new DataInputStream(sin);
            DataOutputStream out = new DataOutputStream(sout);
            out.flush(); // делаем flush, чтобы убедиться, что поток работает

            String line;
            while (true) {
                line = in.readUTF(); // ожидаем пока клиент пришлет строку текста.
                System.out.println("The client just sent me this line : " + line);
                System.out.println("I'm sending it back...\n");
                out.writeUTF(line); // отсылаем клиенту обратно ту самую строку текста.
                out.flush(); // заставляем поток закончить передачу данных.
                System.out.println("Waiting for the next line...");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


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
        BankServerCommander bankCommander = new BankServerCommander();
        bankCommander.run();
    }
}
