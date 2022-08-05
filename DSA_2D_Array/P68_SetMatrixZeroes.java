package DSA_2D_Array;

import java.util.Arrays;
/*
 * - Iterate over the 2D array and find elements that are zero, and store their row and column.
 * - Fill zeroes in the stored rows and columns.
 */
public class P68_SetMatrixZeroes {
    static void setMatrixZeroes(int[][] a)
    {
        int row_size = a.length;
        int rows[] = new int[row_size];
        Arrays.fill(rows, -1);
        int row_iter = 0;

        int col_size = a[0].length;
        int cols[] = new int[col_size];
        Arrays.fill(cols, -1);
        int col_iter = 0;

        System.out.println("Before Set Matrix Zeroes:");
        for(int i = 0; i < row_size; i++) 
        {
            for(int j = 0; j < col_size; j++) 
            {
                System.out.print(a[i][j] + ",");
            }
            System.out.println();
        }
        
        for(int i = 0; i < row_size; i++) 
        {
            for(int j = 0; j < col_size; j++) 
            {
                if(a[i][j] == 0)
                {
                    rows[row_iter] = i;
                    row_iter++;
                    cols[col_iter] = j;
                    col_iter++;
                }
            }
        }

        for(int row : rows)
        {
            if(row!=-1)
            {
                Arrays.fill(a[row], 0);
            }
        }
        for(int col : cols)
        {
            if(col!=-1)
            {
                for (int[] b : a) 
                {
                    b[col] = 0;
                }
            }
        }
        System.out.println("After Set Matrix Zeroes:");
        for(int i = 0; i < row_size; i++) {
            for(int j = 0; j < col_size; j++) {
                System.out.print(a[i][j] + ",");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        int x[][] = {{0,1,1}, {1,0,1}, {1,1,1}};
        setMatrixZeroes(x);
    }
}
