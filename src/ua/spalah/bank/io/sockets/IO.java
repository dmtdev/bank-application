package ua.spalah.bank.io.sockets;

import java.net.Socket;

/**
 * Created by dd on 27.01.2017.
 */
public interface IO {
    String read();
    void write(String s);
}
