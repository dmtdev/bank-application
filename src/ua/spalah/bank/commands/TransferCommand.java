package ua.spalah.bank.commands;

import ua.spalah.bank.services.AccountService;

/**
 * Created by root on 12.01.2017.
 */
public class TransferCommand implements Command {

    private AccountService accountService;
    public TransferCommand(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void execute() {

    }

    @Override
    public String printCommandInfo() {
        return "Transfer";
    }
}
