package ua.spalah.bank;

/**
 * Created by root on 23.12.2016.
 */
public class CheckingAccount extends Account {

    private double overdraft;
//    public CheckingAccount(double balance) {
//        super(balance);
//    }

    public CheckingAccount(double balance, double overdraft) {
        super(balance);
        this.overdraft = overdraft;
    }

    @Override
    public void takeMoney(double sum) {
        // работает-нет.. не на ви жу!!!
        if (super.getBalance() > sum || (super.getBalance()) > (sum + overdraft)){
            changeBalance(sum);
        }
    }

    @Override
    public boolean checkBalance() {
        return false;
    }

    @Override
    public String toString() {
        return "CheckingAccount{" +
                "overdraft=" + overdraft +
                '}';
    }
}
