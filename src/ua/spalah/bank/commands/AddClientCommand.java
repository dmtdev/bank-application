package ua.spalah.bank.commands;

import ua.spalah.bank.model.CheckingAccount;
import ua.spalah.bank.model.Client;
import ua.spalah.bank.model.SavingAccount;
import ua.spalah.bank.model.enums.Sex;
import ua.spalah.bank.services.Account;
import ua.spalah.bank.services.AccountService;
import ua.spalah.bank.services.ClientService;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by root on 12.01.2017.
 */
public class AddClientCommand implements Command {
    private ClientService clientService;
    private AccountService accountService;

    public AddClientCommand(ClientService clientService, AccountService accountService) {
        this.clientService = clientService;
        this.accountService = accountService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        CheckClientData checkClientData = new CheckClientData();
        while (!checkClientData.checkData()) {
            if (checkClientData.name == null) {
                System.out.println("Enter Client name:");
                String name = scanner.nextLine();
                if (name.length() > 0) {
                    checkClientData.name = name;
                }
            } else if (checkClientData.sex == null) {
                System.out.println("Enter Client sex (m(Male) or f(Female)):");
                String sex = scanner.nextLine().trim().toLowerCase();
                if (sex.equals("m") || sex.equals("f")) {
                    checkClientData.sex = (sex.equals("m") ? Sex.MALE : Sex.FEMALE);
                }
            } else {
                Pattern pattern = Pattern.compile("^[0-9]{0,10}.[0-9]{0,2}:[0-9]{0,10}.[0-9]{0,2}$");
                System.out.println("Enter first client account like \"100.01:10\"(balance:overdraft(overdraft = 0 if you want create Saving account))");
                String userInput = scanner.nextLine().trim();
                Matcher matcher = pattern.matcher(userInput);
                if (matcher.matches()) {
                    String[] accountData = userInput.split(":");
                    double balance = Double.parseDouble(accountData[0]);
                    double overdraft = Double.parseDouble(accountData[1]);
                    Client client = new Client(checkClientData.name, checkClientData.sex);
                    clientService.saveClient(BankCommander.currentBank, client);
                    BankCommander.currentClient = client;
                    Account account;
                    if (overdraft == 0) {
                        account = new SavingAccount(balance);
                    } else {
                        account = new CheckingAccount(balance, overdraft);
                    }
                    checkClientData.account = account;
                    clientService.addAccount(client, account);
                    System.out.println("Client " + client.getClientName() + " added");
                    System.out.println("Current client " + client.getClientName());
                }
            }
        }
    }

    @Override
    public String getCommandInfo() {
        return "Add Client";
    }

    private class CheckClientData {
        String name;
        Sex sex;
        Account account;

        public boolean checkData() {
            if (name != null && sex != null && account != null) {
                return true;
            }
            return false;
        }
    }

}
