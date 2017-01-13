package ua.spalah.bank.commands;

import ua.spalah.bank.exceptions.CurrentClientNotSetException;
import ua.spalah.bank.services.AccountService;
import ua.spalah.bank.services.BankService;
import ua.spalah.bank.services.ClientService;

/**
 * Created by root on 12.01.2017.
 */
public class GetAccountsCommand implements Command {

    private AccountService accountService;

    public GetAccountsCommand(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void execute() throws CurrentClientNotSetException {
        if (BankCommander.currentClient == null) {
            throw new CurrentClientNotSetException();
        }
        accountService.getAccountsInfo(BankCommander.currentClient);
    }

    @Override
    public String printCommandInfo() {
        return "Get Accounts";
    }
}
