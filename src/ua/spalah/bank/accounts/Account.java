package ua.spalah.bank.accounts;

/**
 * Created by root on 23.12.2016.
 **************
 */
public abstract class Account {
    private double balance;

    public Account(double balance) {
        if (balance < 0) {
            System.out.println("Initial balance is less than 0!");
        } else {
            this.balance = balance;
        }
    }

    public double getBalance(){
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double changeBalance(double balance) {
        return this.balance += balance;
    }

    public void deposit(double money) {
        if (money <= 0) {
            System.out.println("You want to add no money!");
        } else {
            balance += money;
        }
    }

    public void withdraw(double money) {
        if (money > balance) {
            System.out.println("You want too much money.");
        } else {
            balance -= money;
        }
    }

    public abstract String toString();

}
