package ua.spalah.bank.exceptions;

/**
 * Created by root on 06.01.2017.
 */
public class OverdraftLimitExceededException extends NotEnoughFundsException {
    public OverdraftLimitExceededException(String s) {
        super(s);
    }
}
