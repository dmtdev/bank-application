package ua.spalah.bank.listeners;

import ua.spalah.bank.Client;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by root on 03.01.2017.
 */
public class RegistrationLoggerListener implements ClientRegistrationListener {
    @Override
    public void onClientAdded(Client c) {
        LocalDateTime currentTime = LocalDateTime.now();
        System.out.println("Client "+c.getClientName()+" added on "+currentTime.toString());
    }
}
