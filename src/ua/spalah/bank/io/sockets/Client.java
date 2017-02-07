package ua.spalah.bank.io.sockets;

        import java.io.BufferedReader;
        import java.io.DataInputStream;
        import java.io.DataOutputStream;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.io.OutputStream;
        import java.net.Socket;

public class Client {
    public static void main(String[] args) {

        String server = "localhost"; // это IP-адрес компьютера, где исполняется серверная программа.
        int serverPort = 5050; // здесь обязательно нужно указать порт на котором слушает сервер сервер.
        try {
            // Есть конструкторы, принимающие на вход Sting или объект InetAddress
            Socket socket = new Socket(server, serverPort); // создаем сокет используя IP-адрес и порт сервера.
            System.out.println("Yes! I just have found the server!");

            // Берем входной и выходной потоки сокета, теперь можем получать и отсылать данные клиентом.
            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();

            // Конвертируем потоки в другой тип, чтоб легче обрабатывать текстовые сообщения.
            DataInputStream in = new DataInputStream(sin);
            DataOutputStream out = new DataOutputStream(sout);
            out.flush(); // делаем flush, чтобы убедиться, что поток работает

            // Создаем поток для чтения с клавиатуры.
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            String line;
            System.out.println("Type in something and press enter. We'll send it to the server and tell you what it thinks.");

            while (true) {
                line = keyboard.readLine(); // ждем пока пользователь введет что-то и нажмет кнопку Enter.
                System.out.println("Sending this line to the server...");
                out.writeUTF(line); // отсылаем введенную строку текста серверу.
                out.flush(); // заставляем поток закончить передачу данных.
                line = in.readUTF(); // ждем пока сервер отошлет строку текста.
                System.out.println(line);
                System.out.println("\nGo ahead and enter more lines.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}