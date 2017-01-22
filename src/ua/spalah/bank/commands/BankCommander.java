package ua.spalah.bank.commands;

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

import java.util.Scanner;

/**
 * Created by Dmitry on 10.01.2017.
 */
public class BankCommander {
    // хранит в себе банк с кототорым мы работаем
    public static Bank currentBank;

    // хранит в себе клиента с которым мы работаем в данный момент
    public static Client currentClient;

    // Список команд которые мы можем выполнять
    private static Command[] commands;

    public static Command[] getCommands() {
        return commands;
    }

    public BankCommander() {
        init();
        showMenu();
    }

    private void init() {

        Bank bank = new Bank();
        ClientService clientService = new ClientServiceImpl();
        AccountService accountService = new AccountServiceImpl();
        BankReportService bankReportService = new BankReportServiceImpl();

        Client cl0 = new Client("Dima", Sex.MALE,"q@w.ww","+358693216","city");
        Client cl1 = new Client("Misha", Sex.MALE,"q@w.wq","+358691142","city");
        Client cl2 = new Client("Masha", Sex.FEMALE,"q@eew.ww","+35869221","township");
        Client cl3 = new Client("Kostya", Sex.MALE,"q@w.wwewe","+35869000","township");
//        Client cl4 = new Client("Vasya", Sex.MALE);

        clientService.saveClient(bank, cl0);
        clientService.saveClient(bank, cl1);
        clientService.saveClient(bank, cl2);
        clientService.saveClient(bank, cl3);
//        clientService.saveClient(bank, cl4);

        clientService.addAccount(cl0, new SavingAccount(100));
        clientService.addAccount(cl0, new CheckingAccount(100, 50));
        clientService.addAccount(cl1, new SavingAccount(1000));

        clientService.addAccount(cl2, new SavingAccount(100));
        clientService.addAccount(cl2, new SavingAccount(10000));
        clientService.addAccount(cl2, new CheckingAccount(10000, 500));

        clientService.addAccount(cl3, new SavingAccount(100));
        clientService.addAccount(cl3, new CheckingAccount(10000, 500));

        commands = new Command[]{
                new FindClientCommand(clientService), 
                new GetAccountsCommand(accountService),
                new AddAccountCommand(clientService),
                new SetActiveAccountCommander(clientService, accountService),
                new DepositCommand(accountService), 
                new WithdrawCommand(accountService), 
                new TransferCommand(clientService,accountService),
                new AddClientCommand(clientService, accountService),
                new RemoveClientCommand(clientService),
                new GetBankInfoCommand(bankReportService),
                new ShowMenuCommand(),
                new ExitCommand(),
               // new ReturnClientsMapCommand(bankReportService)
        };
        currentBank = bank;
        // здесь проводим инициализацию банка начальными данными
        // а также создаем все необходимые объекты команд
    }

    public static void showMenu() {
        for (int i = 0; i < commands.length; i++) {
            System.out.println((i + 1) + ". " + commands[i].getCommandInfo());
        }
    }

    public void run() {
        while (true) {
            System.out.println("Enter command number (1-12)");
            Scanner scanner = new Scanner(System.in);
            try {
                int command = Integer.parseInt(scanner.nextLine());
                System.out.println("> " + commands[command - 1].getCommandInfo());
                commands[command - 1].execute();
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Wrong command number");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Wrong account number");
            } catch (NumberFormatException e) {
                System.out.println("This is not a number");
           }
        }
        // запускаем наше приложение
        // выводим в цикле доступные команды
        // ждем от пользователя выбора
        // после выбора команды передаем управление ей
        // вызываем ее метод execute
    }

    // запуск нашего приложения
    public static void main(String[] args) {
        BankCommander bankCommander = new BankCommander();
        bankCommander.run();
    }
}
