package ua.spalah.bank;

/**
 * Created by root on 23.12.2016.
 *===============================
 *
 */
public abstract class Account {
    private double balance;


    abstract public void takeMoney(double sum);

    public Account(double balance) {
        this.balance = balance;
    }
    public double getBalance(){
        return balance;
    }
    public double getBalance(double sum){
        return balance;
    }

    public double changeBalance(double balance) {
        // and for what this..
         return this.balance += balance;
   }

    public void addMoney(double sum){
        // TODO: abstract? check overdraft?...
        if (sum>0){
            balance += sum;
        }
    }

    public boolean checkBalance(){
        if (balance>0){
            return true;
        }
        return false;
    }

}
