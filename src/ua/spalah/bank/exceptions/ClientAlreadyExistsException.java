package ua.spalah.bank.exceptions;

/**
 * Created by dd on 27.01.2017.
 */
public class ClientAlreadyExistsException extends BankException {
    public ClientAlreadyExistsException(String s) {
        super("Client " + s + " Already Exists");
    }
}
