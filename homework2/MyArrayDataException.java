package homework2;

public class MyArrayDataException extends NumberFormatException {
    StackTraceElement[] stackTraceElements = getStackTrace();
    void printError(int cnt1, int cnt2)
    {
        System.out.println("Произошла ошибка в методе " + stackTraceElements[stackTraceElements.length - 1]);
        System.out.println("Ошибка обработки двумерного массива. Ошибка произошла в ячейке " + cnt1 + " номер элемента " + cnt2);
    }
}
