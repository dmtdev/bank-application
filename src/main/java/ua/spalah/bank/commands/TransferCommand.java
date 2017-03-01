package ua.spalah.bank.commands;

import ua.spalah.bank.exceptions.ClientNotFoundException;
import ua.spalah.bank.exceptions.NotEnoughFundsException;
import ua.spalah.bank.io.sockets.IO;
import ua.spalah.bank.model.Client;
import ua.spalah.bank.services.AccountService;
import ua.spalah.bank.services.ClientService;

/**
 * Created by root on 12.01.2017.
 */
public class TransferCommand extends AbstractCommand {

    private AccountService accountService;
    private ClientService clientService;

    public TransferCommand(ClientService clientService, AccountService accountService, IO io) {
        super(io);
        this.accountService = accountService;
        this.clientService = clientService;
    }


    @Override
    public void execute() {
        if (BankServerCommander.currentClient == null) {
            write("Current client not set. Please find client by name to set current client.");
            return;
        }

        write("Enter client name for transit operation:");
        Client client = null;
        try {
            client = clientService.findClientByName(BankServerCommander.currentBank, read());
        } catch (ClientNotFoundException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Enter transfer sum:");
        double sum = Double.parseDouble(read());
        try {
            accountService.transfer(BankServerCommander.currentClient.getActiveAccount(), client.getActiveAccount(), sum);
        } catch (NotEnoughFundsException e) {
            System.out.println(e.getMessage());
        }
        write("$" + sum + " send from " + BankServerCommander.currentClient.getClientName() + " to " + client.getClientName());

    }

    @Override
    public String getCommandInfo() {
        return "Transfer";
    }
}
