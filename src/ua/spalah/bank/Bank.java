package ua.spalah.bank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by root on 23.12.2016.
 */
public class Bank {

    private List<Client> clients = new ArrayList<>();

    public void addClient(Client client) {
        clients.add(client);
    }
    public List getClients(){
        return  Collections.unmodifiableList(clients);
    }
    public String getClient(String clientName){
        for (Client client : clients) {
            if (client.getClientName().equals(clientName)) {
                return client.toString();
            }
        }
        return "client not found";
    }
}
