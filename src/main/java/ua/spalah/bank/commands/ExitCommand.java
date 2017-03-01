package ua.spalah.bank.commands;

import ua.spalah.bank.io.sockets.IO;

/**
 * Created by root on 12.01.2017.
 */
public class ExitCommand extends AbstractCommand {
    public ExitCommand(IO io) {
        super(io);
    }

    @Override
    public void execute() {
        write("Goodbye");
    }

    @Override
    public String getCommandInfo() {
        return "Exit";
    }
}
