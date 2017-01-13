package ua.spalah.bank.commands;

import ua.spalah.bank.exceptions.CurrentClientNotSetException;
import ua.spalah.bank.exceptions.NotEnoughFundsException;
import ua.spalah.bank.services.AccountService;
import ua.spalah.bank.services.ClientService;

import java.util.Scanner;

/**
 * Created by root on 12.01.2017.
 */
public class WithdrawCommand implements Command {

    private AccountService accountService;
    public WithdrawCommand(AccountService accountService) {
        this.accountService=accountService;
    }

    @Override
    public void execute() throws CurrentClientNotSetException, NotEnoughFundsException {
        if (BankCommander.currentClient == null) {
            throw new CurrentClientNotSetException();
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter withdraw sum for "+BankCommander.currentClient.getClientName()+ ":");
        accountService.withdraw(BankCommander.currentClient.getActiveAccount(),Double.parseDouble(scanner.nextLine()));
    }

    @Override
    public String printCommandInfo() {
        return "Withdraw";
    }
}
