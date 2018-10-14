package homework7ver2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Vector;

public class Server {

    public final static String address = "localhost";
    public final static int PORT = 5223;
    private static Vector<User> users;

    public Server() {
        users = new Vector<>();
        try {
            ServerSocket server = new ServerSocket(PORT);
            Socket socket = server.accept();
            System.out.println("Клиент подключился");

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            BufferedReader message = new BufferedReader(new InputStreamReader(System.in));

            // поток для получения сообщений
            Thread receiveMessages = new Thread(() -> {
                try {
                    while (true) {
                        String str = in.readUTF().trim();
                        if (str.startsWith("/auth")) {
                            String[] splitedRequest = str.split(" ");
                            if (splitedRequest[0].equals("/auth")) {
                                System.out.println("Пользователь " + splitedRequest[1] + " подал запрос на авторизацию");
                                if (registUser(new User(splitedRequest[1], splitedRequest[2], splitedRequest[3]))) {
                                    System.out.println("Зарегистрирован");
                                    out.writeUTF("/authok");
                                }
                            }
                        }
                        System.out.println(str);
                    }
                }
                catch (SocketException socEx)
                {
                    System.out.println("Клиент внезапно отключился");
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                    finally {
                    try {
                        in.close();
                        out.close();
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            receiveMessages.start();

            // поток для отправки сообщений
            Thread sendMessagesThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            String answer = message.readLine();
                            out.writeUTF("Сервер написал" + ": " + answer);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            sendMessagesThread.setDaemon(true);
            sendMessagesThread.start();

            try {
                receiveMessages.join();
            } catch (InterruptedException e) {
                System.out.println("Прерывание потока пошло не по плану");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void addUser(User user)
    {
        users.add(user);
    }
    private boolean nickOrNameIsBusy(User user)
    {
        for (User u : users)
        {
            if (u.getName().equals(u.getName()) || u.getNick().equals(u.getNick()))
            {
                return true;
            }
        }
        return false;
    }
    public boolean registUser(User user)
    {
        if (!nickOrNameIsBusy(user))
        {
            addUser(user);
            return true;
        }
        else
        {
            System.out.println("Пользователь такой уже существует");
            return false;
        }
    }
}