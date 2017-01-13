package ua.spalah.bank.commands;

import ua.spalah.bank.exceptions.CurrentClientNotSetException;
import ua.spalah.bank.exceptions.NotEnoughFundsException;
import ua.spalah.bank.services.ClientService;

/**
 * Created by root on 13.01.2017.
 */
public class SetActiveAccount implements Command {

    private ClientService clientService;

    public SetActiveAccount(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public void execute() throws CurrentClientNotSetException, NotEnoughFundsException {

    }

    @Override
    public String printCommandInfo() {
        return "Set Active Account";
    }
}
