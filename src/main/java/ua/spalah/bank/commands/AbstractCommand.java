package ua.spalah.bank.commands;

import ua.spalah.bank.io.sockets.IO;

/**
 * Created by root on 13.02.2017.
 */
public abstract class AbstractCommand implements Command {

    private final IO io;

    public AbstractCommand(IO io) {
        this.io = io;
    }

    protected String read() {
        return io.read();
    }

    protected void write(String s) {
        io.write(s);
    }

    public abstract void execute();

    public abstract String getCommandInfo();
}
