package les6;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class JabberServer {
    public static final int PORT = 8080; // задаем порт сервера

    public static void main(String[] args) throws IOException {
/*1*/        ServerSocket svr = new ServerSocket(PORT);
/*2*/        System.out.println("Server started " + svr);
BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
/*3*/        try
        {
/*4*/           Socket socket = svr.accept();
            try
            {
/*5*/                System.out.println("Connect acceped: " + socket);
/*6*/                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
/*7*/                PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                while (true)
                {
/*8*/                    String string = in.readLine();
/*9*/                    if (string.equals("END"))
                    {
/*10*/                        break;
                    }
/*11*/                    System.out.println("Echoing: " + string);
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
