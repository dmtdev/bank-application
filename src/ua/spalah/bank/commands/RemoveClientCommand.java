package ua.spalah.bank.commands;

import ua.spalah.bank.dao.ClientDao;
import ua.spalah.bank.exceptions.ClientNotFoundException;
import ua.spalah.bank.io.sockets.IO;
import ua.spalah.bank.model.Client;
import ua.spalah.bank.services.ClientService;

import java.util.Scanner;

/**
 * Created by root on 12.01.2017.
 */
public class RemoveClientCommand extends AbstractCommand {
    private ClientService clientService;
    private ClientDao clientDao;

    public RemoveClientCommand(ClientService clientService, IO io, ClientDao clientDao) {
        super(io);
        this.clientService = clientService;
        this.clientDao = clientDao;
    }

    @Override
    public void execute() {
        write("Enter client name:");
        try {
            Client client = clientDao.findByName(read().trim());
            System.out.println(client.toString());
            if (client.equals(BankServerCommander.currentClient)) {
                BankServerCommander.currentClient = null;
                write("WARNING: Current client removed. Please find client by name to set new current client.");
            }
            boolean result = clientDao.delete(client.getClientId());
            write("Client removed.");
        } catch (Exception e) {
            write(e.getMessage());
        }
    }

    @Override
    public String getCommandInfo() {
        return "Remove Client";
    }
}
