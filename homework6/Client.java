package homework6;
import java.io.*;
import java.net.Socket;

public class Client {

    private final static String address = "localhost";

    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;

    public static void main(String[] args) {

        new Client();
    }

    private Client() {

        try {
            socket = new Socket(address, Server.PORT);
            BufferedReader message = new BufferedReader(new InputStreamReader(System.in));
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            // поток для получения сообщений
            Thread receiveMessagesThread = new Thread(() -> {
                try {
                    while (true) {
                        String str = in.readUTF();
                        if (str.equals("END")){
                            System.out.println(str);
                            break;
                        }
                        System.out.println(str);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }  finally {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.exit(0);
                }
            });
            receiveMessagesThread.start();

            // поток для отправки сообщений
            Thread sendMessagesThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            String answer = message.readLine();
                            out.writeUTF("Клиент написал" + ": " + answer);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            sendMessagesThread.setDaemon(true);
            sendMessagesThread.start();

            try {
                receiveMessagesThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
