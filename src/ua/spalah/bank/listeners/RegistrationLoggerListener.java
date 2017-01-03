package ua.spalah.bank.listeners;

import ua.spalah.bank.Client;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by root on 03.01.2017.
 */
public class RegistrationLoggerListener implements ClientRegistrationListener {
    public RegistrationLoggerListener() {
    }

    @Override
    public void onClientAdded(Client c) {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY HH:mm:ss");
        System.out.println("Client "+c.getClientName()+" added on "+currentTime.format(formatter));
    }
}
