package ua.spalah.bank.commands;

import ua.spalah.bank.exceptions.CurrentClientNotSetException;
import ua.spalah.bank.exceptions.NotEnoughFundsException;
import ua.spalah.bank.services.AccountService;
import ua.spalah.bank.services.ClientService;

import java.util.Scanner;

/**
 * Created by root on 13.01.2017.
 */
public class SetActiveAccountCommander implements Command {

    private ClientService clientService;
    private AccountService accountService;

    public SetActiveAccountCommander(ClientService clientService, AccountService accountService) {
        this.clientService = clientService;
        this.accountService = accountService;
    }

    @Override
    public void execute() throws CurrentClientNotSetException{
        if (BankCommander.currentClient == null) {
            throw new CurrentClientNotSetException();
        }
        accountService.getAccountsInfo(BankCommander.currentClient);
        System.out.println("Enter account number to set active account:");
        Scanner scanner = new Scanner(System.in);
        int id = Integer.parseInt(scanner.nextLine())-1;
        BankCommander.currentClient.setActiveAccount(clientService.getAccountById(BankCommander.currentClient,id));
        System.out.println(BankCommander.currentClient.getActiveAccount().toString()+" is Active account.");
    }

    @Override
    public String printCommandInfo() {
        return "Set Active Account";
    }
}
