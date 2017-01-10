package ua.spalah.bank.commands;

import ua.spalah.bank.model.Bank;
import ua.spalah.bank.model.Client;

/**
 * Created by Dmitry on 10.01.2017.
 */
public class BankCommander {
    // хранит в себе банк с кототорым мы работаем
    public static Bank currentBank;

    // хранит в себе клиента с которым мы работаем в данный момент
    public static Client currentClient;

    // Список команд которые мы можем выполнять
    private Command[] commands;

    public BankCommander() {
        init();
    }

    private void init() {
        // здесь проводим инициализацию банка начальными данными
        // а также создаем все необходимые объекты команд
    }

    public void run() {
        // запускаем наше приложение
        // выводим в цикле доступные команды
        // ждем от пользователя выбора
        // после выбора команды передаем управление ей
        // вызываем ее метод execute
    }

    // запуск нашего приложения
    public static void main(String[] args) {
        BankCommander bankCommander = new BankCommander();
        bankCommander.run();
    }
}
