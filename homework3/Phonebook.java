package homework3;

import java.util.HashMap;
import java.util.Map;

public class Phonebook  {
    private String number;
    private String name;
    private Map<String, String> book;
    // поскольку ключи в справочнике должны быть уникальны, а значения могут повторяться
    // в качестве ключа я буду использовать номера телефонов, а в качестве значений фамилию

    public Phonebook()
    {
        book = new HashMap<String, String>();
    }

    public void add(String name, String number) // метод добавления значения в справочник
    {
        book.put(number, name); // добавляем в справочник значение, где номер телефона в качестве ключа, а фамилия в качестве значения
    }

    public void get(String name) // метод поиска по фамилии
    {
        boolean flag = false; // флаг что ты кого нибудь нашли
        for (Map.Entry<String, String> pair : book.entrySet()) // циклом пройдем по всему справочнику в поиске нужной фамилии
        {
            if (pair.getValue().equals(name)) // если у текущей пары ключ-значение значение совпадает с искомым именем
            {
                flag = true; // возводим флаг что мы хоть кого то нашли
                System.out.println(pair.getValue() + " " + pair.getKey()); // выводим искомые данные
            }
        }
        if (!flag) // если флаг так и не будет взведен
        {
            System.out.println("Никого не найдено");
        }
    }

    public static void main(String[] args) {
        Phonebook phonebook = new Phonebook(); // создаем справочник

        // заносим данные
        phonebook.add("Иванов", "+7-123456789");
        phonebook.add("Петров", "+7-123654987");
        phonebook.add("Сидоров", "+7-987456321");
        phonebook.add("Васечкин", "+7-456789123");
        phonebook.add("Абдула", "+7-987852463");
        phonebook.add("Петров", "+7-98753215964");
        phonebook.add("Алибабаев", "+7-3579514682");
        phonebook.add("Алиэкспресов", "+7-9517536842");
        phonebook.add("Петров", "+7-147896325");
        phonebook.add("Мухин", "+7-193764825");

        //ищем
        phonebook.get("Петров");
    }
}
