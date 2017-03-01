package ua.spalah.bank.io.sockets;

import ua.spalah.bank.exceptions.NetworkException;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by root on 31.01.2017.
 */
public class SocketIO implements IO {

    private DataInputStream in;
    private DataOutputStream out;

    private StringBuilder buffer;

    public void init(Socket socket) {
        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            buffer = new StringBuilder();
        } catch (IOException e) {
            throw new RuntimeException("Troubles with socket", e);
        }
    }

    @Override
    public String read() {
        try {
            out.writeUTF(buffer.toString());
            out.flush();
            buffer = new StringBuilder();

            return in.readUTF();
        } catch (IOException e) {
            throw new NetworkException("Cannot read from socket", e);
        }
    }

    @Override
    public void write(String s) {
        buffer.append(s+"\n");
    }
}
