package ua.spalah.bank.extensions;

/**
 * Created by root on 06.01.2017.
 */
public class NotEnoughFundsException extends BankExtension{
    public NotEnoughFundsException(String s) {
        super(s);
    }
}
