package ua.spalah.bank.commands;

import ua.spalah.bank.dao.ClientDao;
import ua.spalah.bank.io.sockets.IO;
import ua.spalah.bank.model.Client;
import ua.spalah.bank.services.ClientService;

/**
 * Created by root on 12.01.2017.
 */

public class FindClientCommand extends AbstractCommand implements Command {

    private ClientService clientService;

    public FindClientCommand(ClientService clientService, IO io) {
        super(io);
        this.clientService = clientService;

    }

    @Override
    public void execute() {
        try {
            write("Enter client name:");
            String clientName = read();
            Client client = clientService.findClientByName(clientName);
            if (client != null) {
                BankServerCommander.currentClient = client;
                write("Current client set to: " + client.getClientName());
            } else {
                write("Client " + clientName + " not found");
            }
            // TODO: 14.02.2017 Add active account if exists..
        } catch (Exception e) {
            write(e.getMessage());
        }
        return;
    }

    @Override
    public String getCommandInfo() {
        return "Find Client";
    }
}
