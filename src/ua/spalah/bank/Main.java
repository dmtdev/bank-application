package ua.spalah.bank;

import ua.spalah.bank.accounts.CheckingAccount;
import ua.spalah.bank.accounts.SavingAccount;
import ua.spalah.bank.accounts.Sex;
import ua.spalah.bank.listeners.ClientRegistrationListener;
import ua.spalah.bank.listeners.EmailNotificationListener;
import ua.spalah.bank.listeners.PrintClientListener;
import ua.spalah.bank.listeners.RegistrationLoggerListener;

import java.util.Scanner;

/**
 * Created by Kostya on 23.12.2016.
 */
public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();
        bank.addListener(new PrintClientListener());
        bank.addListener(new EmailNotificationListener());
        bank.addListener(new RegistrationLoggerListener());

        Client kostya = new Client("Kostya", Sex.MALE);
        Client vasya = new Client("Vasya", Sex.FEMALE);
        Client vasya2 = new Client("Kostya", Sex.MALE);
        SavingAccount sv = new SavingAccount(1000);
        CheckingAccount ck = new CheckingAccount(450, 200);
        kostya.addAccount(sv);
        kostya.addAccount(ck);
        kostya.setActiveAccount(ck);
        bank.addClient(kostya);
        vasya2.addAccount(new SavingAccount(500));
        bank.addClient(vasya2);
        Scanner sc = new Scanner(System.in);
        System.out.println("Type client's name: ");
        String name = sc.nextLine();
        System.out.println(bank.getClientInfo(name));
        System.out.println(kostya.equals(vasya2));
        System.out.println(kostya.hashCode());
        System.out.println(vasya2.hashCode());
        ck.withdraw(800);
        System.out.println(ck.getBalance());
        System.out.println(Sex.MALE.getSalutation());
    }
}