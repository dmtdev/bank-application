package ua.spalah.bank.model;

import ua.spalah.bank.listeners.ClientRegistrationListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by root on 23.12.2016.
 */
public class Bank {

    private List<Client> clients = new ArrayList<>();
    //private List<ClientRegistrationListener> listeners = new ArrayList<>();

//    public void addListener(ClientRegistrationListener listener){
//        listeners.add(listener);
//    }

    public List<Client> getClients(){
        return clients;
    }

    //public List<ClientRegistrationListener> getListeners() {
       //return listeners;
    //}
}
