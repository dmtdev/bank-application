package ua.spalah.bank.accounts;

/**
 * Created by root on 23.12.2016.
 */
public class CheckingAccount extends Account {

    private double overdraft;

    public CheckingAccount(double balance, double overdraft) {
        super(balance);
        this.overdraft = overdraft;
    }

    @Override
    public void withdraw(double money) {
        if (super.getBalance()+ overdraft >= money){
            super.setBalance(super.getBalance()-money);
        }
    }

    @Override
    public String toString() {
        return "CheckingAccount <-> balance: " + getBalance()+ ", overdraft: " + overdraft;
    }
}
