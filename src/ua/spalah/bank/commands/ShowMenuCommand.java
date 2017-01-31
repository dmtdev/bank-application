package ua.spalah.bank.commands;

/**
 * Created by root on 12.01.2017.
 */
public class ShowMenuCommand implements Command {
    @Override
    public void execute() {
        //BankCommander.showMenu();
        BankServerCommander.serverAnswer = String.join("\n",BankServerCommander.showMenu());

    }

    @Override
    public String getCommandInfo() {
        return "Show this menu";
    }
}
