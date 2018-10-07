package homework5;

public class MainClass {
    static final int size = 10000000;
    static final int half = size / 2;

    public static void main(String[] args) {
        float[] arr = new float[size];

        for (int i = 0; i < size; i++) {
            arr[i] = 1;
        }
        MainClass mainClass = new MainClass();
        long time1 = System.currentTimeMillis();
        mainClass.method1(arr);
        System.out.println("Проход по массиву в 1 поток : " + (System.currentTimeMillis() - time1) + " миллисекунд");
        long time2 = System.currentTimeMillis();
        mainClass.method2(arr);
        System.out.println("Проход по массиву в 2 потока : " + (System.currentTimeMillis() - time2) + " миллисекунд");
    }

    void method1(float[] arr)
    {
        for (int i = 0; i < arr.length; i++)
        {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }

    void method2(float[] arr)
    {
        float[] arr1 = new float[half];
        float[] arr2 = new float[half];
        System.arraycopy(arr, 0, arr1, 0, half);
        System.arraycopy(arr, half, arr2, 0, half);
        new Thread(() -> method1(arr1)).start();
        new Thread(() -> method1(arr2)).start();
        System.arraycopy(arr1, 0, arr, 0, half);
        System.arraycopy(arr2, 0, arr, half, half);
    }
}