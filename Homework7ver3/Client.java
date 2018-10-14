package Homework7ver3;

import homework7ver2.Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.net.Socket;

public class Client extends JFrame{

    private final static String address = "localhost";
    private JFrame window; // само окно чата
    private JTextArea dialog; // поле в котором отображается текущий диалог
    private JTextField message; // поле в котором набирается сообщение для отправки
    private JTextArea contacts; // окно контактов
    private JButton send; // кнопка отсылающая сообщение
    private JButton reset; // кнопка очищающая поле набора сообщения
    private JMenuBar bar; // создаем панель меню
    private JMenu file; // создаем меню кнопки Файл
    private JMenu help; // создаем меню кнопки Помощь
    private JMenuItem saveAs; // создаем пункт меню для сохранения диалога
    private JMenuItem exit; // создаем пункт меню для выхода из приложения
    private JMenuItem aboutIt; // создаем пункт меню справки описывающий текущую программу
    private JFrame authWindow;
    private JTextField loginField;
    private JTextField passField;
    private JButton ok;
    private JLabel labelLogin;
    private JLabel labelPass;
    private JLabel labelNick;
    private JTextField nickField;
    private String login;
    private String pass;
    private String nick;
    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;

    public Client() {
        authWindow = new JFrame("Окно авторизации");
        labelLogin = new JLabel("Логин");
        labelPass = new JLabel("Пароль");
        labelNick = new JLabel("Ник");
        loginField = new JTextField(12);
        passField = new JTextField(12);
        nickField = new JTextField(12);
        ok = new JButton("OK");
        authWindow.getContentPane();
        authWindow.setLayout(new FlowLayout(FlowLayout.CENTER));
        authWindow.add(labelLogin);
        authWindow.add(loginField);
        authWindow.add(labelPass);
        authWindow.add(passField);
        authWindow.add(labelNick);
        authWindow.add(nickField);
        authWindow.add(ok);
        authWindow.setBounds(300,300,230, 200);
        authWindow.setVisible(true);
        authWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login = loginField.getText();
                pass = passField.getText();
                nick = nickField.getText();
                if (login.isEmpty())
                {
                    loginField.setBackground(Color.red);
                }
                else
                {
                    loginField.setBackground(Color.white);
                }
                if (pass.isEmpty())
                {
                    passField.setBackground(Color.red);
                }
                else
                {
                    passField.setBackground(Color.white);
                }
                if (nick.isEmpty())
                {
                    nickField.setBackground(Color.red);
                }
                else
                {
                    nickField.setBackground(Color.white);
                }
                try
                {
                    out.writeUTF("/auth " + login + " " + pass);
                    loginField.setText("");
                    passField.setText("");
                }
                catch (IOException exception)
                {
                    exception.printStackTrace();
                }
                if (!login.isEmpty() && !pass.isEmpty() && !nick.isEmpty()) {
                    authWindow.setVisible(false);
                    window.setVisible(true); // делаем окно видимым
                }
            }
        });
        window = new JFrame("Текстовый чат"); // создаем само окно с названием
        window.setBounds(300,300,700,400); // задаем координаты и размер окна в пикселах
        window.setDefaultCloseOperation(EXIT_ON_CLOSE); // добавляем что бы наша программа закрывалась по крестику закрытия окна
        window.setLayout(new BorderLayout()); // выбираем менеджер компоновки
        dialog = new JTextArea(); // создаем поля диалога на 8 колонок
        message = new JTextField(30); // создаем поле ввода сообщения на 4 колонки
        contacts = new JTextArea( 20, 10); // создаем окно контактов

        contacts.setBackground(Color.GRAY); // устанавливаем цвет поля контактов
        dialog.setBackground(Color.LIGHT_GRAY); // устанавливаем цвет поля диалога
        send = new JButton("Отправить"); // создаем кнопку "Отправить"
        reset = new JButton("Очистить"); // создаем кнопку для очистки поля ввода
        bar = new JMenuBar(); // создаем панель меню
        file = new JMenu("Файл"); // создаем пункт меню Файл
        help = new JMenu("Помощь"); // создаем пункт меню Помощь
        saveAs = new JMenuItem("Сохранить диалог как..."); // создаем пункт подменю для сохранения диалога
        exit = new JMenuItem("Выход"); // создаем пункт подменю для закрытия окна
        aboutIt = new JMenuItem("О программе"); // создаем пункт подменю для вывода сведений о версии и описании программы
        file.add(saveAs); // добавляем подменю сохранения в меню Файл
        file.add(exit); // добавляем подменю закрытия в меню Файл
        help.add(aboutIt); // добавляем подменю в меню Помощь
        bar.add(file); // добавляем меню Файл на панель меню
        bar.add(help); // добавляем меню Помощь на панель меню
        dialog.setFocusable(false); // запрещаем фокус на поле для диалога
        contacts.setFocusable(false); // запрещаем фокус на поле контактов
        send.setBackground(Color.GREEN); // устанавливаем зеленый фон кнопки отправить
        reset.setBackground(Color.RED); // устанавливаем красный фон кнопки очистить
        JPanel footer = new JPanel(); // создаем нижнию панель панель
        JPanel right = new JPanel(); // создаем правую панель
        JScrollPane scroll = new JScrollPane(dialog); // добавляем прокрутку нашего поля диалога
        footer.setLayout(new FlowLayout()); // устанавливаем нижней панели менеджер компоновки
        right.setLayout(new FlowLayout()); // устанавливаем менеджер размещения правой панели
        right.add(contacts); // добавляем в правую панель поле контактов
        footer.add(message); // добавляем на нижнию панель поле для набора сообщения
        footer.add(send); // добавляем на нижнию панель кнопку отправки
        footer.add(reset); // добавляем на нижнию панель кнопку сброса
        window.getContentPane().add(BorderLayout.NORTH, bar); // добавляем меню в шапку окна
        window.getContentPane().add(BorderLayout.SOUTH, footer); // добавляем поле ввода сообщения в низ окна
        window.getContentPane().add(BorderLayout.CENTER, scroll); // добавляем поле диалога в поле прокрутки в центр окна
        window.getContentPane().add(BorderLayout.EAST, right); // добавляем поле контактов в правую обрасть

        send.addActionListener(new ActionListener() { // добавляем действие для кнопки отправить
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.append("\n" + "Вы: " + message.getText()); // добавляем к нашему диалогу текст из поля сообщения
                try {
                    out.writeUTF(message.getText());
                    message.setText("");
                }
                catch (IOException ex)
                {
                    ex.printStackTrace();
                }
                message.setText(""); // очищаем поле сообщения
            }
        });
        message.addKeyListener(new KeyListener() { // добавляем действие отправки сообщения по кнопке enter
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    dialog.append("\n" + "Вы: " + message.getText());
                    try {
                        out.writeUTF(message.getText());
                        message.setText("");
                    }
                    catch (IOException ex)
                    {
                        ex.printStackTrace();
                    }
                    message.setText("");
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        reset.addActionListener(new ActionListener() { // добавляем действие очистки поля набора сообщения по нажатию кнопки
            @Override
            public void actionPerformed(ActionEvent e) {
                message.setText(""); // очищаем поле ввода сообщения
            }
        });
        aboutIt.addActionListener(new ActionListener() { // добавляем действие по нажатию на пункт меню о программе
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(window, "Версия чата 1.0"); // выводим всплявающее окно с версией чата
            }
        });

        exit.addActionListener(new ActionListener() { // добавляем действие закрытия программы по нажатию на меню выход
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // выходим из программы
            }
        });
        saveAs.addActionListener(new ActionListener() { // добавляем действие сохранения истории чата по нажатию на пункт меню сохранить как
            @Override
            public void actionPerformed(ActionEvent e) {
                /*
                try (FileWriter writer = new FileWriter("C:/temp/dialog.txt", false))

                {
                    writer.write(dialog.getText());
                    writer.flush();
                }
                catch (IOException exception)
                {
                    exception.printStackTrace();
                }
                */
                JOptionPane.showMessageDialog(window,"Ну допустим сохранили диалог");
            }
        });
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
