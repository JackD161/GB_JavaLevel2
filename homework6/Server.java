package homework6;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {;

    public static void main(String[] args) {
        new Server();
    }

    public final static int PORT = 5223;

    public Server() {

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
                        String str = in.readUTF();
                        if (str.split(" ")[1].equals("END")) {
                            out.writeUTF("Завершение работы сервера");
                            break;
                        }
                        System.out.println(str);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
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
}