package ua.spalah.bank.commands;

import ua.spalah.bank.exceptions.ClientNotFoundException;
import ua.spalah.bank.model.Client;
import ua.spalah.bank.services.ClientService;

import java.util.Scanner;

/**
 * Created by root on 12.01.2017.
 */
public class RemoveClientCommand implements Command {
    private ClientService clientService;

    public RemoveClientCommand(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public void execute() {
        System.out.println("Enter client name:");
        Scanner scanner = new Scanner(System.in);
        try {
            Client client = clientService.findClientByName(BankCommander.currentBank, scanner.nextLine());
            if (client.equals(BankCommander.currentClient)) {
                BankCommander.currentClient = null;
                System.out.println("WARNING: Current client removed. Please find client by name to set new current client.");
            }
            clientService.deleteClient(BankCommander.currentBank, client);
            System.out.println("Client removed.");
        } catch (ClientNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String printCommandInfo() {
        return "Remove Client";
    }
}
