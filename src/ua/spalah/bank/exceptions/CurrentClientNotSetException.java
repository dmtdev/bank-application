package ua.spalah.bank.exceptions;

/**
 * Created by root on 12.01.2017.
 */
public class CurrentClientNotSetException extends BankException {
    public CurrentClientNotSetException() {
        super("Current client not set.  Please find client by name to set new current client.");
    }
}
