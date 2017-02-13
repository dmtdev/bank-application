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
        if(BankCommander.currentClient==null)
        {
            System.out.println("Current client not set. Please find client by name to set current client.");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter deposit sum for "+BankCommander.currentClient.getClientName()+ ":");
        accountService.deposit(BankCommander.currentClient.getActiveAccount(),Double.parseDouble(scanner.nextLine()));
    }

    @Override
    public String getCommandInfo() {
        return "Deposit";
    }
}
