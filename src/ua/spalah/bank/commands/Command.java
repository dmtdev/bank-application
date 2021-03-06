package ua.spalah.bank.commands;


import ua.spalah.bank.exceptions.ClientAlreadyExistsException;

/**
 * Created by Dmitry on 10.01.2017.
 */
public interface Command {
    // взаимодействует с клиетом читая его ввод с консоли и печатая ему ответы
    void execute() throws ClientAlreadyExistsException;
    // выводит информацию о команде в консоль
    String getCommandInfo();
}
