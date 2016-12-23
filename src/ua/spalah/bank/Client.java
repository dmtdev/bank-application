package ua.spalah.bank;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 23.12.2016.
 */
public class Client {

    private String clientName;
    private String sex;
    private List<Account> accountList = new ArrayList<>();
    private Account activeAccount;

    public Client(String clientName, String sex) {
        this.clientName = clientName;
        this.sex = sex; // TODO: enum? may be..
    }


    public String getClientName() {
        return clientName;
    }

    public String getSex() {
        return sex;
    }

    public List<Account> getAccountList() {
        return new ArrayList(accountList);
        // TODO: Make deep clone
    }

    public Account getActiveAccount() {
        return activeAccount;
        // TODO: clone() ?....
    }

    public void activeAccount(Account account) {

    }

    @Override
    public String toString() {
        return "Client{" +
                "clientName='" + clientName + '\'' +
                ", sex='" + sex + '\'' +
                ", accountList=" + accountList +
                ", activeAccount=" + activeAccount +
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
    @Override
    protected Client clone() {
        Client clone = null;
        try{
            clone = (Client) super.clone();

        }catch(CloneNotSupportedException e){
            throw new RuntimeException(e);
        }
        return clone;
    }


}
