package les6;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class JabberClient {
    public static void main(String[] args) throws IOException {
        // если в getByName передать null то получим заглушку для тестирования на машине без сети
        InetAddress address = InetAddress.getByName(null);
        System.out.println("Address: " + address);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Socket socket = new Socket(address, JabberServer.PORT);
        try
        {
            System.out.println("socket: " + socket);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            for (int i = 0; i < 10; i++)
            {
                out.println(reader.readLine());
                String string = in.readLine();
                System.out.println(string);
            }
            out.println("END");
        }
        finally
        {
            System.out.println("closing...");
            socket.close();
        }
    }
}
