package ua.spalah.bank.model;

import ua.spalah.bank.Account;
import ua.spalah.bank.Sex;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 23.12.2016.
 */
public class Client  {

    private String clientName;
    private Sex sex;
    private List<Account> accountList = new ArrayList<>();
    private Account activeAccount;

    public Client(String clientName, Sex sex) {
        this.clientName = clientName;
        this.sex = sex;
    }

    public String getClientName() {
        return clientName;
    }

    public Sex getSex() {
        return sex;
    }

    public List<Account> getAccountList() {
        return accountList;
        //return Collections.unmodifiableList(accountList);
    }

    public Account getActiveAccount() {
        return activeAccount;
    }

    public void setActiveAccount(Account activeAccount) {
        this.activeAccount = activeAccount;
    }


    @Override
    public String toString() {
        return "Client{" +
                "clientName='" + clientName + '\'' +
                ", sex='" + sex + '\'' +
                ", accountList=" + accountList +
                ", activeAccount=" + activeAccount.toString() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (!clientName.equals(client.clientName)) return false;
        return sex.equals(client.sex);
    }

    @Override
    public int hashCode() {
        int result = clientName.hashCode();
        result = 31 * result + sex.hashCode();
        return result;
    }

}