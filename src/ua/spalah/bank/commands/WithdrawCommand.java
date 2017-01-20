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
    public void execute()   {
        if (BankCommander.currentClient == null) {
            System.out.println(new CurrentClientNotSetException().getMessage());
            return;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter withdraw sum for "+BankCommander.currentClient.getClientName()+ ":");
        try {
            accountService.withdraw(BankCommander.currentClient.getActiveAccount(), Double.parseDouble(scanner.nextLine()));
        }
        catch (NotEnoughFundsException e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String getCommandInfo() {
        return "Withdraw";
    }
}
