package ua.spalah.bank.commands;

import ua.spalah.bank.exceptions.ClientNotFoundException;
import ua.spalah.bank.exceptions.CurrentClientNotSetException;
import ua.spalah.bank.exceptions.NotEnoughFundsException;
import ua.spalah.bank.model.Client;
import ua.spalah.bank.services.AccountService;
import ua.spalah.bank.services.ClientService;

import java.util.Scanner;

/**
 * Created by root on 12.01.2017.
 */
public class TransferCommand implements Command {

    private AccountService accountService;
    private ClientService clientService;

    public TransferCommand(ClientService clientService, AccountService accountService) {
        this.accountService = accountService;
        this.clientService = clientService;
    }


    @Override
    public void execute() {
        if (BankCommander.currentClient == null) {
            System.out.println("Current client not set. Please find client by name to set current client.");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter client name for transit operation:");
        Client client = null;
        try {
            client = clientService.findClientByName(BankCommander.currentBank, scanner.nextLine());
        } catch (ClientNotFoundException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Enter transfer sum:");
        double sum = Double.parseDouble(scanner.nextLine());
        try {
            accountService.transfer(BankCommander.currentClient.getActiveAccount(), client.getActiveAccount(), sum);
        } catch (NotEnoughFundsException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("$" + sum + " send from " + BankCommander.currentClient.getClientName() + " to " + client.getClientName());

    }

    @Override
    public String getCommandInfo() {
        return "Transfer";
    }
}
