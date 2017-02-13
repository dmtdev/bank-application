package ua.spalah.bank.commands;

import ua.spalah.bank.io.sockets.IO;

import java.lang.reflect.Array;


/**
 * Created by root on 12.01.2017.
 */
public class ShowMenuCommand extends AbstractCommand {

    public ShowMenuCommand(IO io)
    {
        super(io);
    }

    @Override
    public void execute() {
        write(BankServerCommander.serverAnswer = String.join("\n",BankServerCommander.showMenu()));

    }

    @Override
    public String getCommandInfo() {
        return "Show this menu";
    }
}
