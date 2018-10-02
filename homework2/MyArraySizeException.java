package homework2;

public class MyArraySizeException extends Exception {
    void printError()
    {
        System.out.println("Массив имеет размер отличный от нужного");
    }
}
