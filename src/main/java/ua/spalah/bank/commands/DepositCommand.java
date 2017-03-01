package ua.spalah.bank.commands;

import ua.spalah.bank.io.sockets.IO;
import ua.spalah.bank.services.AccountService;

import java.util.Scanner;

/**
 * Created by root on 12.01.2017.
 */
public class DepositCommand extends AbstractCommand  {

    private AccountService accountService;
    public DepositCommand(AccountService accountService,IO io) {
        super(io);
        this.accountService=accountService;
    }

    @Override
    public void execute(){
        if(BankServerCommander.currentClient==null)
        {
            write("Current client not set. Please find client by name to set current client.");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        write("Enter deposit sum for "+BankServerCommander.currentClient.getClientName()+ ":");
        accountService.deposit(BankServerCommander.currentClient.getActiveAccount(),Double.parseDouble(read()));
    }

    @Override
    public String getCommandInfo() {
        return "Deposit";
    }
}
