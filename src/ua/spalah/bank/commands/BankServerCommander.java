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

    public static String serverAnswer;

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

    public BankServerCommander() {
        init();
        //showMenu();
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
                e.printStackTrace();
            }
        }
        bank.setAllClients(clientMap);
        initCommands();
        currentBank = bank;
        System.out.println(currentBank);
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
                String[] menu = showMenu();
                line = String.join("\n",menu);
                //line = in.readUTF(); // ожидаем пока клиент пришлет строку текста.
                //System.out.println("The client just sent me this line : " + line);
                //System.out.println("I'm sending it back...\n");
                String input = in.readUTF();
                try {
                    int command = Integer.parseInt(input);
                    //if(input)
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
                //out.writeUTF(line);
                out.writeUTF(serverAnswer); // отсылаем клиенту обратно ту самую строку текста.
                BankServerCommander.serverAnswer = "";
                out.flush(); // заставляем поток закончить передачу данных.
                //System.out.println("Waiting for the next line...");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    public static void main(String[] args) {
        BankServerCommander bankServerCommander = new BankServerCommander();
        bankServerCommander.run();
    }
}
