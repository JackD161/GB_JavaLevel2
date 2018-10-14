package Homework7ver3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private ServerSocket server;
    private ArrayList<User> users;
    private final int PORT = 8189;
    public Server()
    {
        users = new ArrayList<>();
        try
        {
            server = new ServerSocket(PORT);
            Socket socket = null;
            while (true)
            {
                System.out.println("Сервер ожидает подключения");
                socket = server.accept();
                System.out.println("Клиент подключился");
            }
        }
        catch (IOException e)
        {
            System.out.println("Ошибка при работе сервера");
        }
        finally
        {
            try
            {
                server.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

}
