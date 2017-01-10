package ua.spalah.bank.listeners;

import ua.spalah.bank.model.Client;

/**
 * Created by root on 03.01.2017.
 */
public class EmailNotificationListener implements ClientRegistrationListener {
    public EmailNotificationListener() {
    }

    @Override
    public void onClientAdded(Client c) {
        System.out.println("Notification email for client "+c.getClientName()+" has been sent");
    }
}
