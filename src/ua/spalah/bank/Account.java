package ua.spalah.bank;

/**
 * Created by root on 23.12.2016.
 *
 */
public abstract class Account {
    private double balance;

    public Account(double balance) {
        this.balance = balance;
    }
    public double getBalance(){
        return balance;
    }
    public double getBalance(double sum){
        return balance;
    }

    public void changeBalance(double balance) {
        this.balance = balance;
    }

    public void addMoney(double sum){

    }

    abstract public void takeMoney(double sum);


}
