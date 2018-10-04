package homework4;

import javax.swing.*;
import java.awt.*;

public class WindowChat extends JFrame {
    private JFrame window; // само окно чата
    private JLabel label; // название окна чата (возможно имя собеседника)
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

    public WindowChat()
    {
        window = new JFrame("Текстовый чат"); // создаем само окно с названием
        window.setBounds(300,300,700,400); // задаем координаты и размер окна в пикселах
        window.setDefaultCloseOperation(EXIT_ON_CLOSE); // добавляем что бы наша программа закрывалась по крестику закрытия окна
        window.setLayout(new BorderLayout()); // выбираем менеджер компоновки
        label = new JLabel("Собеседник"); // здесь задается имя собеседника
        dialog = new JTextArea(); // создаем поля диалога на 8 колонок
        message = new JTextField(30); // создаем поле ввода сообщения на 4 колонки
        contacts = new JTextArea( 20, 10); // создаем окно контактов

        contacts.append("Контакт1");
        contacts.append("\nКонтакт2");
        contacts.append("\nКонтакт3");
        contacts.append("\nКонтакт4");
        contacts.append("\nКонтакт5");

        contacts.setBackground(Color.GRAY);
        dialog.setBackground(Color.LIGHT_GRAY);

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
        footer.setLayout(new FlowLayout()); // устанавливаем нижней панели менеджер компоновки
        right.setLayout(new FlowLayout()); // устанавливаем менеджер размещения правой панели
        right.add(contacts); // добавляем в правую панель поле контактов
        footer.add(message); // добавляем на нижнию панель поле для набора сообщения
        footer.add(send); // добавляем на нижнию панель кнопку отправки
        footer.add(reset); // добавляем на нижнию панель кнопку сброса
        window.getContentPane().add(BorderLayout.NORTH, bar); // добавляем меню в шапку окна
        window.getContentPane().add(BorderLayout.SOUTH, footer); // добавляем поле ввода сообщения в низ окна
        window.getContentPane().add(BorderLayout.CENTER, dialog); // добавляем поле диалога в центр окна
        window.getContentPane().add(BorderLayout.EAST, right); // добавляем поле контактов в правую обрасть
        window.setVisible(true); // делаем окно видимым
    }

    public static void main(String[] args) {
        new WindowChat();
    }
}
