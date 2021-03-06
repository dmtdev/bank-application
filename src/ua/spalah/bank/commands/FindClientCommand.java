package ua.spalah.bank.commands;

import ua.spalah.bank.exceptions.ClientNotFoundException;
import ua.spalah.bank.io.sockets.IO;
import ua.spalah.bank.services.ClientService;

import java.util.Scanner;

/**
 * Created by root on 12.01.2017.
 */

public class FindClientCommand extends AbstractCommand implements Command{

    private ClientService clientService;

    public FindClientCommand(ClientService clientService, IO io) {
        super(io);
        this.clientService = clientService;
    }

    @Override
    public void execute() {
        try{
            write("Enter client name:");
            Scanner scanner = new Scanner(System.in);
            BankServerCommander.currentClient = clientService.findClientByName(BankServerCommander.currentBank,read());
            write("Current client set to:");
            write(BankServerCommander.currentClient.getClientName());
            write(BankServerCommander.currentClient.getActiveAccount().toString());
        } catch (ClientNotFoundException e) {
            write(e.getMessage());
        }
 }

    @Override
    public String getCommandInfo() {
        return "Find Client";
    }
}
