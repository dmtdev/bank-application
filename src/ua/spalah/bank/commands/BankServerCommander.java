package ua.spalah.bank.commands;


import ua.spalah.bank.dao.AccountDao;
import ua.spalah.bank.dao.ClientDao;
import ua.spalah.bank.annotations.DbColumn;
import ua.spalah.bank.dao.impl.AccountDaoImpl;
import ua.spalah.bank.dao.impl.ClientDaoImpl;
import ua.spalah.bank.exceptions.ClientAlreadyExistsException;
import ua.spalah.bank.exceptions.NetworkException;
import ua.spalah.bank.io.sockets.IO;
import ua.spalah.bank.io.sockets.SocketIO;
import ua.spalah.bank.model.Bank;
import ua.spalah.bank.model.Client;
import ua.spalah.bank.services.AccountService;
import ua.spalah.bank.services.BankReportService;
import ua.spalah.bank.services.ClientService;
import ua.spalah.bank.services.impl.AccountServiceImpl;
import ua.spalah.bank.services.impl.BankReportServiceImpl;
import ua.spalah.bank.services.impl.ClientServiceImpl;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dmitry on 10.01.2017.
 */
public class BankServerCommander {
    // хранит в себе банк с кототорым мы работаем
    public static Bank currentBank;

    private final IO io;
    private final SocketIO socketIO;
    public static Connection connection;


    private Bank bank = new Bank();
    private ClientService clientService = new ClientServiceImpl();
    private AccountService accountService = new AccountServiceImpl();
    private BankReportService bankReportService = new BankReportServiceImpl();
    private ClientDao clientDao = new ClientDaoImpl();
    private AccountDao accountDao = new AccountDaoImpl();
    private Map<String, Client> clientMap = new HashMap<>();

    public static Client currentClient;

    private static Command[] commands;

    public static Command[] getCommands() {
        return commands;
    }

    public BankServerCommander(SocketIO socketIO) throws SQLException, ClassNotFoundException {
        this.io = socketIO;
        this.socketIO=socketIO;
        init();
    }

    private void initCommands(){
        commands = new Command[]{
                new FindClientCommand(clientService, io, clientDao),
                new GetAccountsCommand(accountService,io,accountDao),
                new AddAccountCommand(clientService,io),
                new SetActiveAccountCommander(clientService, accountService, io),
                new DepositCommand(accountService, io),
                new WithdrawCommand(accountService, io),
                new TransferCommand(clientService, accountService, io),
                new AddClientCommand(clientService, accountService, io, clientDao),
                new RemoveClientCommand(clientService, io, clientDao),
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

    private void init() throws SQLException, ClassNotFoundException {

        Class.forName("org.h2.Driver");
        String url = "jdbc:h2:tcp://localhost/~/test";
        Connection connection = DriverManager.getConnection(url, "sa", "");
        this.connection = connection;

//        Scanner scanner = new Scanner(ClassLoader.getSystemResourceAsStream("clients.txt"));
//        while (scanner.hasNext()) {
//            String[] clientData = scanner.nextLine().split("::");
//            clientMap.put(clientData[0], new Client(clientData[0], (clientData[1].equals("MALE") ? Sex.MALE : Sex.FEMALE), clientData[2], clientData[3], clientData[4]));
//        }
//
//        scanner = new Scanner(ClassLoader.getSystemResourceAsStream("accounts.txt"));
//        while (scanner.hasNext()) {
//            String[] clientData = scanner.nextLine().split("::");
//            if (clientMap.containsKey(clientData[0])) {
//                if (clientData[1].equals("SA")) {
//                    clientService.addAccount(clientMap.get(clientData[0]), new SavingAccount(Double.parseDouble(clientData[2])));
//                }
//                else if (clientData[1].equals("CA")) {
//                    clientService.addAccount(clientMap.get(clientData[0]), new CheckingAccount(Double.parseDouble(clientData[2]), Double.parseDouble(clientData[2])));
//                }
//            }
//        }
//
//        for (Map.Entry<String, Client> entry : clientMap.entrySet()) {
//            Client client = entry.getValue();
//            try {
//                clientService.saveClient(bank, client);
//            } catch (ClientAlreadyExistsException e) {
//                System.out.println(e.getMessage());
//            }
//        }
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
        }
        while(!exit);

    }

    public static <T> T mapModel(ResultSet resultSet, Class<T> classToMap) throws Exception {

        T model = classToMap.newInstance();

        for (Field field : classToMap.getDeclaredFields()) {

            String name = field.getName();
            if (field.isAnnotationPresent(DbColumn.class)) {
                DbColumn annotation = field.getAnnotation(DbColumn.class);
                name = annotation.columnName();
            }

            Class<?> type = field.getType();
            field.setAccessible(true);

            if (long.class.isAssignableFrom(type)) {
                long value = resultSet.getLong(name);
                field.setLong(model, value);
            } else if (double.class.isAssignableFrom(type)) {
                double value = resultSet.getDouble(name);
                field.setDouble(model, value);
            } else if (String.class.isAssignableFrom(type)) {
                String value = resultSet.getString(name);
                field.set(model, value);
            }
        }

        return model;
    }

    public static void main(String[] args) {
        BankServerCommander bankServerCommander = null;
        try {
            bankServerCommander = new BankServerCommander(new SocketIO());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        bankServerCommander.run();
    }
}
