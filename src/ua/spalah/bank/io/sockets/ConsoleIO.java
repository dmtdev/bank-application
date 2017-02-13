package ua.spalah.bank.io.sockets;

import java.util.Scanner;

/**
 * Created by root on 31.01.2017.
 */

public class ConsoleIO implements IO {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public String read() {
        return scanner.nextLine();
    }

    @Override
    public void write(String s) {
        System.out.print(s);
    }
}

