package ua.spalah.bank.commands;

import ua.spalah.bank.exceptions.CurrentClientNotSetException;
import ua.spalah.bank.services.AccountService;

import java.util.Scanner;

/**
 * Created by root on 12.01.2017.
 */
public class DepositCommand implements Command {

    private AccountService accountService;
    public DepositCommand(AccountService accountService) {
        this.accountService=accountService;
    }

    @Override
    public void execute() throws CurrentClientNotSetException {
        if(BankCommander.currentClient==null)
        {
            throw new CurrentClientNotSetException();
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter deposit sum for "+BankCommander.currentClient.getClientName()+ ":");
        accountService.deposit(BankCommander.currentClient.getActiveAccount(),Double.parseDouble(scanner.nextLine()));
    }

    @Override
    public String printCommandInfo() {
        return "Deposit";
    }
}
