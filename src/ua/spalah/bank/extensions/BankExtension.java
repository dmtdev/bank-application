package ua.spalah.bank.extensions;

/**
 * Created by root on 06.01.2017.
 */
public class BankExtension extends Exception {
    String message = new String();
    public BankExtension(String s) {
        message = s;
    }
}
