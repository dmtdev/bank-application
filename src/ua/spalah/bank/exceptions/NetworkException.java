package ua.spalah.bank.exceptions;

/**
 * Created by root on 13.02.2017.
 */
public class NetworkException extends RuntimeException {
    public NetworkException(String message) {
        super(message);
    }

    public NetworkException(String message, Throwable cause) {
        super(message, cause);
    }
}
