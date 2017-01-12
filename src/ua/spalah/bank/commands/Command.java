package ua.spalah.bank.commands;

import ua.spalah.bank.exceptions.CurrentClientNotSetException;

/**
 * Created by Dmitry on 10.01.2017.
 */
public interface Command {
    // взаимодействует с клиетом читая его ввод с консоли и печатая ему ответы
    void execute() throws CurrentClientNotSetException;
    // выводит информацию о команде в консоль
    String printCommandInfo();
}
