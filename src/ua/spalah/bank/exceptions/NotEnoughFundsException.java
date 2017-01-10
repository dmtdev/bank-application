package ua.spalah.bank.exceptions;

/**
 * Created by root on 06.01.2017.
 */
public class NotEnoughFundsException extends BankException{
    public NotEnoughFundsException(String s) {
        super(s);
    }
}
