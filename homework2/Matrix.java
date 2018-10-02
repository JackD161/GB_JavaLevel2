package homework2;

import java.util.Arrays;

public class Matrix {
    private String[][] matrix;
    private int summ = 0;

    public Matrix()
    {
        this.matrix = new String[4][4];
    }

    public Matrix(String[][] matrix)
    {
        this.matrix = matrix;
    }
    void action() throws MyArraySizeException, MyArrayDataException {
        if (!Arrays.equals(matrix, new String[4][4]))
        {
            throw new MyArraySizeException();
        }
        int cnt1 = 0;
        int cnt2 = 0;
        for (String[] str : matrix)
        {
            cnt1++;
            for (String s : str)
            {
                cnt2++;
                try {
                    summ += Integer.parseInt(s);
                }
                catch (MyArrayDataException e)
                {
                    e.printError(cnt1, cnt2);
                }
            }
        }
    }

    public int getSumm()
    {
        return summ;
    }
}
