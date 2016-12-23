package ua.spalah.bank;

/**
 * Created by root on 23.12.2016.
 */
public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
        Client client = new Client("","");
        Client client1 = client.clone();
    }
}