package ua.spalah.bank.io.sockets;

        import java.io.*;
        import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        String serverIP = "localhost";
        int serverPort = 5050;
        try {
            Socket socket = new Socket(serverIP, serverPort);

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.flush();
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                System.out.print(in.readUTF());
                out.writeUTF(keyboard.readLine());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}