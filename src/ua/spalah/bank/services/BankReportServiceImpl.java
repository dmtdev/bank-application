package ua.spalah.bank.services;

import ua.spalah.bank.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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
        List clientList = new ArrayList<>();
        clientList = bank.getClients();
        //Почему не дает использовать Client client..
        for (Object client : clientList) {
            if (client instanceof Client) {
                count += ((Client) client).getAccountList().size();
            }
        }
        return count;
    }

    @Override
    public double getTotalAccountSum(Bank bank) {
        double sum = 0;
        List clientList = new ArrayList<>();
        clientList = bank.getClients();
        //Почему не дает использовать Client client..
        for (Object client : clientList) {
            if (client instanceof Client) {
                sum += ((Client) client).getTotalBalance();
            }
        }
        return sum;
    }


    @Override
    public double getBankCreditSum(Bank bank) {
        double sum = 0;
        List clientList = new ArrayList<>();
        clientList = bank.getClients();
        //Почему не дает использовать Client client..
        for (Object client : clientList) {
            if (client instanceof Client) {
                if (((Client) client).getTotalBalance() < 0)
                    sum += ((Client) client).getTotalBalance();
            }
        }
        return sum;
    }

    @Override
    public List<Client> getClientsSortedByName(Bank bank) {
        List clientList = bank.getClients();
        clientList.sort(new Comparator() {
                            @Override
                            public int compare(Object o1, Object o2) {
                                if (o1 instanceof Client && o2 instanceof Client) {
                                    Client c1 = (Client) o1;
                                    Client c2 = (Client) o2;
                                    return c1.getClientName().compareTo(c2.getClientName());
                                }
                                return 0;
                            }
                        }
        );
        return clientList;
    }
}
