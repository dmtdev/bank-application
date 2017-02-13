package ua.spalah.bank.commands;

import ua.spalah.bank.io.sockets.IO;
import ua.spalah.bank.services.AccountService;
import ua.spalah.bank.services.ClientService;

import java.util.Scanner;

/**
 * Created by root on 13.01.2017.
 */
public class SetActiveAccountCommander extends AbstractCommand  {

    private ClientService clientService;
    private AccountService accountService;

    public SetActiveAccountCommander(ClientService clientService, AccountService accountService,IO io) {
        super(io);
        this.clientService = clientService;
        this.accountService = accountService;
    }

    @Override
    public void execute() {
        if (BankCommander.currentClient == null) {
            System.out.println("Current client not set. Please find client by name to set current client.");
            return;
        }
        accountService.getAccountsInfo(BankCommander.currentClient);
        System.out.println("Enter account number to set active account:");
        Scanner scanner = new Scanner(System.in);
        int id = 0;
        try {
            id = Integer.parseInt(scanner.nextLine())-1;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        BankCommander.currentClient.setActiveAccount(clientService.getAccountById(BankCommander.currentClient,id));
        System.out.println(BankCommander.currentClient.getActiveAccount().toString()+" is Active account.");
    }

    @Override
    public String getCommandInfo() {
        return "Set Active Account";
    }
}
