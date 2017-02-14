package ua.spalah.bank.model;

import ua.spalah.bank.services.Account;
import ua.spalah.bank.model.enums.Sex;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 23.12.2016.
 */
public class Client {

    private String clientName;
    private Sex sex;
    private String email;
    private String tel;
    private String city;
    private List<Account> accountList = new ArrayList<>();
    private Account activeAccount;
    private long clientId;

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public Client(String clientName, Sex sex) {
        this.clientName = clientName;
        this.sex = sex;
    }

    public Client(String clientName, Sex sex, String email, String tel, String city) {
        this.clientName = clientName;
        this.sex = sex;
        this.email = email;
        this.tel = tel;
        this.city = city;
    }

    public Client() {

    }

    public String getEmail() {
        return email;
    }

    public String getTel() {
        return tel;
    }

    public String getCity() {
        return city;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getClientName() {
        return clientName;
    }

    public Sex getSex() {
        return sex;
    }

    public List<Account> getAccountList() {
        return accountList;
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
                "clientId=" + clientId +
                ", clientName='" + clientName + '\'' +
                ", sex=" + sex +
                ", email='" + email + '\'' +
                ", tel='" + tel + '\'' +
                ", city='" + city + '\'' +
                ", activeAccount=" + activeAccount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (!clientName.equals(client.clientName)) return false;
        if (sex != client.sex) return false;
        if (!email.equals(client.email)) return false;
        if (tel.equals(client.tel)) if (city.equals(client.city)) return true;
        return false;
    }

    @Override
    public int hashCode() {
        int result = clientName.hashCode();
        result = 31 * result + sex.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + tel.hashCode();
        result = 31 * result + city.hashCode();
        return result;
    }
}
