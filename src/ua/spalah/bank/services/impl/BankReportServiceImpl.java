package ua.spalah.bank.services.impl;

import ua.spalah.bank.Account;
import ua.spalah.bank.model.Bank;
import ua.spalah.bank.model.CheckingAccount;
import ua.spalah.bank.model.Client;
import ua.spalah.bank.services.BankReportService;

import java.util.*;

/**
 * Created by root on 03.01.2017.
 */
public class BankReportServiceImpl implements BankReportService {
    @Override
    public int getNumberOfClients(Bank bank) {
        return bank.getClients().size();
    }

    @Override
    public int getNumberOfAccounts(Bank bank) {
        int count = 0;
        List<Client> clientList = bank.getClients();
        for (Client client : clientList) {
            count += client.getAccountList().size();
        }
        return count;
    }

    @Override
    public double getTotalAccountSum(Bank bank, Client client) {
        double sum = 0;
        List<Client> clientList = bank.getClients();
        for (Client cl : clientList) {
            List<Account> accounts = cl.getAccountList();
            for (Account account : accounts) {
                sum += account.getBalance();
                //овердрафт это же не деньги клиета?
            }
        }
        return sum;
    }
    @Override
    public double getBankCreditSum(Bank bank, Client client) {
        double sum = 0;
        List<Client> clientList = bank.getClients();
        for (Client cl : clientList) {
            List<Account> accounts = cl.getAccountList();
            for (Account account : accounts) {
                if (account instanceof CheckingAccount) {
                    //это же сколько клиенты лолжны банку?
                    if (account.getBalance() < 0) {
                        sum += account.getBalance();
                    }
                }
            }
        }
        return sum;
    }

    @Override
    public List<Client> getClientsSortedByName(Bank bank) {
        List<Client> clientList = bank.getClients();

        clientList.sort(new Comparator<Client>() {
                            @Override
                            public int compare(Client o1, Client o2) {
                                return o1.getClientName().compareTo(o2.getClientName());
                            }
                        }
        );
        return clientList;
    }
}
