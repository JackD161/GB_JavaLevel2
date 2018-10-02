package homework4;

import javax.swing.*;

public class WindowChat extends JFrame {
    JFrame window; // само окно чата
    JLabel label; // название окна чата (возможно имя собеседника)
    JTextField dialog; // поле в котором отображается текущий диалог
    JTextField message; // поле в котором набирается сообщение для отправки
    JButton send; // кнопка отсылающая сообщение
    JButton reset; // кнопка очищающая поле набора сообщения
    JMenuBar bar; // создаем панель меню
    JMenu file; // создаем меню кнопки Файл
    JMenu help; // создаем меню кнопки Помощь
    JMenuItem saveAs; // создаем пункт меню для сохранения диалога
    JMenuItem exit; // создаем пункт меню для выхода из приложения
    JMenuItem aboutIt; // создаем пункт меню справки описывающий текущую программу

    WindowChat()
    {
        window = new JFrame("Текстовый чат"); // создаем само окно с названием
        window.setSize(300,300); // задаем размер окна в пикселах
        window.getContentPane();
        window.setDefaultCloseOperation(EXIT_ON_CLOSE); // добавляем что бы наша программа закрывалась по крестику закрытия окна
        label = new JLabel("Собеседник"); // здесь задается имя собеседника
        dialog = new JTextField(8); // создаем поля диалога на 8 колонок
        message = new JTextField(4); // создаем поле ввода сообщения на 4 колонки
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

    }
}
