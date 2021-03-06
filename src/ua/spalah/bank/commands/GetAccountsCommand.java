package ua.spalah.bank.commands;

import ua.spalah.bank.io.sockets.IO;
import ua.spalah.bank.services.AccountService;


/**
 * Created by root on 12.01.2017.
 */
public class GetAccountsCommand extends AbstractCommand {

    private AccountService accountService;

    public GetAccountsCommand(AccountService accountService,IO io) {

        super(io);
        this.accountService = accountService;
    }

    @Override
    public void execute()  {
        if (BankServerCommander.currentClient == null) {
            write("Current client not set. Please find client by name to set current client.");
            return;
        }
        write(accountService.getAccountsInfo(BankServerCommander.currentClient));
    }

    @Override
    public String getCommandInfo() {
        return "Get Accounts";
    }
}
