package ua.spalah.bank.services;

import ua.spalah.bank.model.enums.AccountType;

import javax.persistence.*;

/**
 * Created by root on 06.01.2017.
 */
@MappedSuperclass
public interface Account {
        AccountType getType();
        double getBalance();
        void setBalance(double balance);

}
