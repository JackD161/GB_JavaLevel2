package homework3;

import java.util.ArrayList;

public class DuplicateWords {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        ArrayList<String[]> dublicate = new ArrayList<>();
        // добавляем в массив 20 слов
        list.add("хамелеон");
        list.add("ротвейлер");
        list.add("краб");
        list.add("выхухоль");
        list.add("удав");
        list.add("скунс");
        list.add("саранча");
        list.add("страус");
        list.add("ленивец");
        list.add("чихуахуа");
        list.add("креветка");
        list.add("динозавр");
        list.add("индюк");
        list.add("шиншилла");
        list.add("колибри");
        list.add("муравьед");
        list.add("чихуахуа");
        list.add("выхухоль");
        list.add("креветка");
        list.add("выхухоль");

        for (String str : list) // пройдем циклом по всему массиву
        {
            if (list.indexOf(str) == list.lastIndexOf(str)) // если первый индекс вхождения текущего слова в массиве
            // совпадает с последним индексом - это значит что слово встречается в массиве один раз
            {
                System.out.println(str); // выводим каждое уникальное слово
            }
            else
            {
                // слово не уникально, делаем что-нибудь
                int cnt = 0;
                for (String s : list)
                {
                    if (s.equals(str))
                    {
                        cnt++;
                    }
                }
                //System.out.println("Слово " + str + " не уникально и встречается " + cnt + " раз в списке");
            }
        }
        for (String str : list)
        {


        }
    }
}
