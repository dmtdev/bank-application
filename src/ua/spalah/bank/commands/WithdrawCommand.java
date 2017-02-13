package ua.spalah.bank.commands;

import ua.spalah.bank.exceptions.NotEnoughFundsException;
import ua.spalah.bank.io.sockets.IO;
import ua.spalah.bank.services.AccountService;

import java.util.Scanner;

/**
 * Created by root on 12.01.2017.
 */
public class WithdrawCommand extends AbstractCommand {

    private AccountService accountService;

    public WithdrawCommand(AccountService accountService, IO io) {
        super(io);
        this.accountService = accountService;
    }

    @Override
    public void execute() {
        if (BankServerCommander.currentClient == null) {
            write("Current client not set. Please find client by name to set current client.");
            return;
        }

        write("Enter withdraw sum for " + BankServerCommander.currentClient.getClientName() + ":");
        try {
            accountService.withdraw(BankServerCommander.currentClient.getActiveAccount(), Double.parseDouble(read()));
        } catch (NotEnoughFundsException e) {
            write(e.getMessage());
        }
    }

    @Override
    public String getCommandInfo() {
        return "Withdraw";
    }
}
