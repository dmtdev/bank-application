package ua.spalah.bank.commands;

import ua.spalah.bank.exceptions.ClientNotFoundException;
import ua.spalah.bank.exceptions.CurrentClientNotSetException;
import ua.spalah.bank.exceptions.NotEnoughFundsException;

/**
 * Created by Dmitry on 10.01.2017.
 */
public interface Command {
    // взаимодействует с клиетом читая его ввод с консоли и печатая ему ответы
    void execute() throws CurrentClientNotSetException, NotEnoughFundsException, ClientNotFoundException;
    // выводит информацию о команде в консоль
    String printCommandInfo();
}
