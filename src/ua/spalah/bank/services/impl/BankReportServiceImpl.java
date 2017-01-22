package ua.spalah.bank.services.impl;

import ua.spalah.bank.commands.BankCommander;
import ua.spalah.bank.services.Account;
import ua.spalah.bank.services.BankReportService;
import ua.spalah.bank.model.Bank;
import ua.spalah.bank.model.CheckingAccount;
import ua.spalah.bank.model.Client;

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
        List<Account> accounts = client.getAccountList();
        for (Account account : accounts) {
            sum += account.getBalance();
        }
        return sum;
    }

    @Override
    public double getTotalAccountSum(Bank bank) {
        double sum = 0;
        List<Client> clientList = bank.getClients();
        for (Client cl : clientList) {
            List<Account> accounts = cl.getAccountList();
            for (Account account : accounts) {
                sum += account.getBalance();
            }
        }
        return sum;
    }

    @Override
    public double getBankCreditSum(Bank bank, Client client) {
        double sum = 0;
        List<Account> accounts = client.getAccountList();
        for (Account account : accounts) {
            if (account instanceof CheckingAccount) {
                if (account.getBalance() < 0) {
                    sum += account.getBalance();
                }
            }
        }
        return sum;
    }

    @Override
    public double getBankCreditSum(Bank bank) {
        double sum = 0;
        List<Client> clientList = bank.getClients();
        for (Client cl : clientList) {
            List<Account> accounts = cl.getAccountList();
            for (Account account : accounts) {
                if (account instanceof CheckingAccount) {
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

    @Override
    public Map<String, List<Client>> getClientsByCity(Bank bank) {
        Map<String, List<Client>> clients = new HashMap<>();
        for (Client c : BankCommander.currentBank.getClients()) {
            if (!clients.containsKey(c.getCity())) {
                List<Client> cl = new ArrayList<>();
                cl.add(c);
                clients.put(c.getCity(),cl);
            } else {
                List<Client> cl = (List<Client>) clients.get(c.getCity());
                cl.add(c);
            }
        }
        System.out.println(clients.size());
        return clients;
    }
//    public Map<String, List<Client>> getClientsByCity(Bank bank) {
//        Map<String, List<Client>> clienMap = new HashMap<>();
//        for (Client c : bank.getClients()) {
//            if (clienMap.containsKey(c.getCity())){
//                clienMap.get(c.getCity()).add(c);
//            } else {
//                clienMap.put(c.getCity(), new ArrayList<>());
//                clienMap.get(c.getCity()).add(c);
//            }
//        }
//        System.out.println(clienMap.size());
//        return clienMap;
//    }
}
