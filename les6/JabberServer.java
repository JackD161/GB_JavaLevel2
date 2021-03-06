package les6;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class JabberServer {
    public static final int PORT = 5222; // задаем порт сервера

    public static void main(String[] args) throws IOException {
        ServerSocket svr = new ServerSocket(PORT);
        System.out.println("Server started " + svr);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try
        {
           Socket socket = svr.accept();
            try
            {
                System.out.println("Connect acceped: " + socket);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                while (true)
                {
                    String string = in.readLine();
                    if (string.equals("END"))
                    {
                        break;
                    }
                    System.out.println("Echoing: " + string);
                    out.println(reader.readLine());
                }
            }
            finally
            {
                System.out.println("closing...");
                socket.close();
            }
        }
        finally
        {
            svr.close();
        }
    }
}
