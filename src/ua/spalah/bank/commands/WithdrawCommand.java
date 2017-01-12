package ua.spalah.bank.commands;

import ua.spalah.bank.exceptions.CurrentClientNotSetException;

/**
 * Created by root on 12.01.2017.
 */
public class WithdrawCommand implements Command {
    @Override
    public void execute() throws CurrentClientNotSetException {
        if (BankCommander.currentClient == null) {
            throw new CurrentClientNotSetException("Current client not set.  Please find client by name to set new current client.");
        }
    }

    @Override
    public String printCommandInfo() {
        return "Withdraw";
    }
}
