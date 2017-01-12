package ua.spalah.bank.commands;

import ua.spalah.bank.model.enums.Sex;
import ua.spalah.bank.services.Account;
import ua.spalah.bank.services.ClientService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by root on 12.01.2017.
 */
public class AddClientCommand implements Command {
    private ClientService clientService;

    public AddClientCommand(ClientService clientService) {
        this.clientService =clientService;

    }



    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        boolean isFill = false;
        CheckClientData checkClientData = new CheckClientData();
        while (!checkClientData.checkData() && !isFill){
            checkClientData.name="name";
        }


    }

    @Override
    public String printCommandInfo() {
        return "Add Client";
    }

    private  class CheckClientData {
        String name;
        Sex sex;
        List<Account> accounts = new ArrayList<>();

        public boolean checkData() {
            if (name != null && sex != null && accounts.size() > 0) {
                return true;
            }
            return false;
        }
    }

}
