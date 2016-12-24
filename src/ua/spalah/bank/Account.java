package ua.spalah.bank;

/**
 * Created by root on 23.12.2016.
 */
public abstract class Account {
    private double balance;

    public Account(double balance) {
        this.balance = balance;
    }
    public double getBalance(){
        return balance;
    }

//    public double getBalance(double sum){
//        return balance;
//    }


    public double changeBalance(double balance) {
        // and for what this..
         return this.balance += balance;
   }



    public void addMoney(double sum){
        if (sum>0){
            balance += sum;
        }

    }

    abstract public void takeMoney(double sum);
    abstract public boolean checkBalance();

}
