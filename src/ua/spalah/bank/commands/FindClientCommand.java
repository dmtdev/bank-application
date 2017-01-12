package ua.spalah.bank.commands;

import ua.spalah.bank.exceptions.ClientNotFoundException;
import ua.spalah.bank.services.ClientService;

import java.util.Scanner;

/**
 * Created by root on 12.01.2017.
 */

public class FindClientCommand implements Command {

    private ClientService clientService;

    public FindClientCommand(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public void execute() {
        System.out.println("Enter client name:");
        Scanner scanner = new Scanner(System.in);
        try{
            BankCommander.currentClient = clientService.findClientByName(BankCommander.currentBank,scanner.nextLine());
        } catch (ClientNotFoundException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Current client set to:");
        System.out.println(BankCommander.currentClient.getClientName());

    }

    @Override
    public String printCommandInfo() {
        return "Find Client";
    }
}
