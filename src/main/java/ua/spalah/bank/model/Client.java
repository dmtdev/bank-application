package ua.spalah.bank.model;


import ua.spalah.bank.annotations.DbColumn;
import ua.spalah.bank.model.enums.Sex;
import ua.spalah.bank.services.Account;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 23.12.2016.
 */
@Entity
@Table(name="CLIENTS")
public class Client {
    @Id
    @Column(name = "CLIENT_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long clientId;

    @Column(name = "NAME")
    private String clientName;
    @Column(name = "GENDER")
    private Sex sex;
    @Column(name="EMAIL")
    private String email;
    @Column(name = "PHONE")
    private String tel;
    @Column(name = "CITY_ID")
    private String city;

    //@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="CLIENTS")
    @JoinTable(name = "ACCOUNTS",
            joinColumns = @JoinColumn(name = "CLIENT_ID"),
            inverseJoinColumns=@JoinColumn(table = "CLIENTS", name="CLIENT_ID")
    )
    private List<Account> accountList = new ArrayList<>();
//
    //@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy="CLIENTS")
    //@Column(name="ACCOUNT_ID")
    @JoinTable(name = "ACCOUNTS",
            joinColumns = @JoinColumn(name="ACCOUNT_ID"),
            inverseJoinColumns=@JoinColumn(table = "ACCOUNTS",name = "ACCOUNT_ID")
    )
    private Account activeAccount;



    public Client() {

    }

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

    public Client(String clientName, Sex sex, String email, String tel, String city,long clientId) {
        this.clientName = clientName;
        this.sex = sex;
        this.email = email;
        this.tel = tel;
        this.city = city;
        this.clientId = clientId;
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
