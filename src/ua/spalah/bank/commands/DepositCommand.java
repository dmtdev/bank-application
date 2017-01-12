package ua.spalah.bank.commands;

import ua.spalah.bank.exceptions.CurrentClientNotSetException;
import ua.spalah.bank.services.ClientService;

/**
 * Created by root on 12.01.2017.
 */
public class DepositCommand implements Command {

    private ClientService clientService;
    public DepositCommand(ClientService clientService) {
        this.clientService=clientService;
    }

    @Override
    public void execute() throws CurrentClientNotSetException {
        if(BankCommander.currentClient==null)
        {
            throw new CurrentClientNotSetException("Current client not set.  Please find client by name to set new current client.");
        }
    }

    @Override
    public String printCommandInfo() {
        return "Deposit";
    }
}
