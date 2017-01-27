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
        return bank.getAllClients().size();
    }

    @Override
    public int getNumberOfAccounts(Bank bank) {
        int count = 0;
        Map<String, Client> clientMap = bank.getAllClients();
        for (Map.Entry<String, Client> entry : clientMap.entrySet()) {
            count += entry.getValue().getAccountList().size();
        }
        return count;
    }

//    @Override
//    public double getTotalAccountSum(Bank bank, Client client) {
//        double sum = 0;
////        List<Account> accounts = client.getAccountList();
////        for (Account account : accounts) {
////            sum += account.getBalance();
////        }
//        Map<String, Client> clientMap = bank.getAllClients();
//        for (Map.Entry<String, Client> entry : clientMap.entrySet()) {
//            List<Account> accounts = entry.getValue().getAccountList();
//            for (Account account : accounts) {
//                sum += account.getBalance();
//            }
//        }
//        return sum;
//    }

    @Override
    public double getTotalAccountSum(Bank bank) {
        double sum = 0;
        Map<String,Client> clientMap = bank.getAllClients();
//        for (Client cl : clientList) {
//            List<Account> accounts = cl.getAccountList();
//            for (Account account : accounts) {
//                sum += account.getBalance();
//            }
//        }
        for (Map.Entry<String,Client> entry : clientMap.entrySet())
        {
            List<Account> accounts = entry.getValue().getAccountList();
            for (Account account :accounts)
                sum+= account.getBalance();
        }
        return sum;
    }

//    @Override
//    public double getBankCreditSum(Bank bank, Client client) {
//        double sum = 0;
//        List<Account> accounts = client.getAccountList();
//        for (Account account : accounts) {
//            if (account instanceof CheckingAccount) {
//                if (account.getBalance() < 0) {
//                    sum += account.getBalance();
//                }
//            }
//        }
//        return sum;
//    }

    @Override
    public double getBankCreditSum(Bank bank) {
        double sum = 0;
        Map<String,Client> clientList = bank.getAllClients();
        for (Map.Entry<String, Client> entry : clientList.entrySet()) {
            List<Account> accounts = entry.getValue().getAccountList();
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
    public Map<String, Client> getClientsSortedByName(Bank bank) {
        Map<String, Client> clientMap = bank.getAllClients();
//        clientMap.sort(new Comparator<Client>() {
//                            @Override
//                            public int compare(Client o1, Client o2) {
//                                return o1.getClientName().compareTo(o2.getClientName());
//                            }
//                        }
//        );
        return clientMap;
    }
    @Override
    public Map<String, List<Client>> getClientsByCity(Bank bank) {
        Map<String, List<Client>> clientsMap = new HashMap<>();
        for (Map.Entry<String,Client> entry : bank.getAllClients().entrySet()) {
            if (!clientsMap.containsKey(entry.getKey().equals(entry.getValue().getCity()))) { 
                List<Client> cl = new ArrayList<>();
                cl.add(entry.getValue());
                clientsMap.put(entry.getValue().getCity(), cl);
            } else {
                List<Client> cl = clientsMap.get(entry.getValue().getCity());
                cl.add(entry.getValue());
            }
        }
        return clientsMap;
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
