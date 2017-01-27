package ua.spalah.bank.model;

import ua.spalah.bank.listeners.ClientRegistrationListener;
import ua.spalah.bank.services.Account;

import java.util.*;

/**
 * Created by root on 23.12.2016.
 */
public class Bank {

    private List<Client> clients = new ArrayList<>();
    private Map<String,Client> allClients = new HashMap<>();
    //private List<ClientRegistrationListener> listeners = new ArrayList<>();

//    public void addListener(ClientRegistrationListener listener){
//        listeners.add(listener);
//    }

//    public List<Client> getClients(){
//        return clients;
//    }
    public Map<String,Client> getAllClients(){
        return allClients;
    }

    public void setAllClients(Map<String, Client> allClients) {
        this.allClients = allClients;
    }


}
