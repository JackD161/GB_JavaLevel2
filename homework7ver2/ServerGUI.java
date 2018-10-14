package homework7ver2;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class ServerGUI {
    JLabel title;
    JTextArea log;
    BufferedReader reader;
    DataInputStream in;
    JFrame mainWindow;

    public ServerGUI(DataInputStream in)
    {
        mainWindow = new JFrame("Логирование");
        this.in = in;
        reader = new BufferedReader(new InputStreamReader(in));
        title = new JLabel("Log");
        log = new JTextArea(12, 12);
        log.setFocusable(false);
        mainWindow.setDefaultCloseOperation(EXIT_ON_CLOSE);
        mainWindow.getContentPane();
        mainWindow.setLayout(new FlowLayout(FlowLayout.CENTER));
        mainWindow.add(title);
        mainWindow.add(log);
        mainWindow.setBounds(300,300, 400, 400);
        mainWindow.setVisible(true);
        while(true)
        {
            try {
                log.append(reader.readLine());
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
