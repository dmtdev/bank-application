package ua.spalah.bank.commands;

/**
 * Created by root on 12.01.2017.
 */
public class ExitCommand implements Command {
    @Override
    public void execute() {
        System.out.println("Goodbye");
        System.exit(0);
    }

    @Override
    public String printCommandInfo() {
        return "Exit";
    }
}
