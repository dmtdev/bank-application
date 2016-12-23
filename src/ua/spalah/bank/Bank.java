package ua.spalah.bank;

import java.util.ArrayList;
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
        return new ArrayList(clients);
        // TODO: Make deep clone
    }
    public String getClient(String clientName){
        for (Client client : clients){
            // TODO: add client search, fix return
        }
        return new Client("","").toString();
    }
}
