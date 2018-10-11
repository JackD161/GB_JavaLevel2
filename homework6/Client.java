package homework6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = null;
            try {
                InetAddress address = InetAddress.getByName(null);
                System.out.println("Подлючаемся к адресу " + address);
                socket = new Socket(address, Server.PORT);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                while (true) {
                    String messageIn = in.readLine();
                    if (messageIn.equals("end")) {
                        break;
                    }
                    System.out.println(messageIn);
                    out.println(reader.readLine());
                }
            } finally {
                socket.close();
            }
        }
        catch (IOException e)
        {
            System.out.println("Подключение к серверу не удалось");
            System.out.println();
        }
    }
}
