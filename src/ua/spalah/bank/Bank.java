package ua.spalah.bank;

import ua.spalah.bank.listeners.ClientRegistrationListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by root on 23.12.2016.
 */
public class Bank {

    private List<Client> clients = new ArrayList<>();
    private List<ClientRegistrationListener> listeners;

    public void addListener(ClientRegistrationListener listener){
        listeners.add(listener);
    }


    public void addClient(Client client) {
        clients.add(client);
        for (ClientRegistrationListener listener: listeners) {
            listener.onClientAdded(client);
        }
    }
    public List getClients(){
        return  Collections.unmodifiableList(clients);
    }
    public String getClientInfo(String clientName){
        for (Client client : clients) {
            if (client.getClientName().equals(clientName)) {
                return client.toString();
            }
        }
        return "client not found";
    }
}
