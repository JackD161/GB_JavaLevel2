package homework6;
/**
 * @author Холопкин Юрий
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    // здесь задается порт сервера, для jabber по умолчанию это 5222
    public static final int PORT = 5222;

    public static void main(String[] args) {
        try
        {
/*1*/            ServerSocket serverSocket = new ServerSocket(PORT);
/*2*/            System.out.println("Сервер запущен на " + PORT + " порту");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
/*4*/                Socket socket = serverSocket.accept();
/*3*/            try {
/*5*/                System.out.println("Клиент подключился");
                // получаем входящий поток из входящего потока сокета и передаем в обертку буффера чтения для удобства
/*6*/                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                // вывод выталкиваем из буффера PrintWriter
                // второй параметр указывает, что надо автоматически добавлять в Writer данные
/*7*/                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                while (true) {
/*8*/                    String input = in.readLine();
/*9*/                    if (input.equals("end")) {
                        System.out.println("Разковор закончен");
/*10*/                  break;
                    }

                    if (input.equals("Привет") || input.equals("привет")) {
                        out.println("Ну привет!");
                    }
/*11*/                    System.out.println(input);
                    out.println(reader.readLine());
                }
            }
            finally
            {
                socket.close();
                serverSocket.close();
            }
        }
        catch (IOException e)
        {
            System.out.println("Проблемма инициализации сервера");
        }
    }
}
