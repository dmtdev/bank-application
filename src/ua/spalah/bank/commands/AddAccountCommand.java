package ua.spalah.bank.commands;

import ua.spalah.bank.exceptions.CurrentClientNotSetException;
import ua.spalah.bank.exceptions.NotEnoughFundsException;
import ua.spalah.bank.io.sockets.IO;
import ua.spalah.bank.model.CheckingAccount;
import ua.spalah.bank.model.Client;
import ua.spalah.bank.model.SavingAccount;
import ua.spalah.bank.services.Account;
import ua.spalah.bank.services.ClientService;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by root on 13.01.2017.
 */
public class AddAccountCommand extends AbstractCommand {

    private ClientService clientService;

    public AddAccountCommand(ClientService clientService, IO io) {
        super(io);
        this.clientService = clientService;
    }

    @Override
    public void execute()  {
        if(BankCommander.currentClient==null)
        {
            System.out.println("Current client not set. Please find client by name to set current client.");
            return;
        }
        Pattern pattern = Pattern.compile("^[0-9]{0,10}.[0-9]{0,2}:[0-9]{0,10}.[0-9]{0,2}$");
        System.out.println("Enter client account like \"100.01:10\"(balance:overdraft(overdraft = 0 if you want create Saving account))");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine().trim();
        Matcher matcher = pattern.matcher(userInput);
        if (matcher.matches()) {
            String[] accountData = userInput.split(":");
            double balance = Double.parseDouble(accountData[0]);
            double overdraft = Double.parseDouble(accountData[1]);
            Account account;
            if (overdraft == 0) {
                account = new SavingAccount(balance);
            } else {
                account = new CheckingAccount(balance, overdraft);
            }
            clientService.addAccount(BankCommander.currentClient, account);
            System.out.println("Account is added to "+BankCommander.currentClient.getClientName());
        }
    }

    @Override
    public String getCommandInfo() {
        return "Add Account";
    }
}
