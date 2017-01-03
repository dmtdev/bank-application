package ua.spalah.bank.listeners;

import ua.spalah.bank.Client;

/**
 * Created by root on 03.01.2017.
 */
public class PrintClientListener implements ClientRegistrationListener{
    @Override
    public void onClientAdded(Client c) {
        System.out.println(c.toString());
    }
}
