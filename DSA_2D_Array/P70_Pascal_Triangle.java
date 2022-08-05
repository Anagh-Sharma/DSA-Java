package DSA_2D_Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P70_Pascal_Triangle {
    static List<List<Integer>> pascal(int rows) {
        List<List<Integer>> tri = new ArrayList<List<Integer>>();
        tri.add(new ArrayList<>());
        tri.get(0).add(1);

        // System.out.println(tri);

        for(int i = 1; i < rows; i++) {
            List<Integer> row = new ArrayList<>();
            List<Integer> prevRow = tri.get(i - 1);
            // First element of row is always 1
            row.add(1);

            for(int j = 1; j < i; j++) {
                row.add(prevRow.get(j-1) + prevRow.get(j));
            }

            // Last element of row is always 1
            row.add(1);
            tri.add(row);
        }
        return tri;
    }
    /*
     * Triangle matrix: Every row starting from row 0 has row + 1 elements only.
     * 
     * Pascal triangle:
     * - An element in a row is added to its preceding element.
     * - This sum is inserted in the next row at the same index as the original element
     */
    static void pascalTriangle(int[][] arr)
    {
        /*Intializing the first single-element subarray to 1. */
        arr[0][0] = 1;
        /*
         * Iterating over the rows of the triangular matrix.
         */
        for(int i=0; i<arr.length-1; i++)
        {
            /*Initializing the next row's first and last indices to 1. */
            arr[i+1][0] = 1;
            arr[i+1][arr[i+1].length-1] = 1;

            /*
             * Iterating over the elements of a row from the second element to row length-1.
             * Adding the preceding element to the current element and copying the resultant
             * to the next row at the index same as the current element.
             */
            for(int j=1; j<arr[i].length; j++)
            {
                arr[i+1][j] = arr[i][j-1] + arr[i][j];
            }
        }
        for(int[] b : arr)
        {
            System.out.println(Arrays.toString(b));
        }
    }
    public static void main(String[] args) {
        // List<List<Integer>> tri = pascal(5);
        int arr[][] = {{0},{0,0},{0,0,0},{0,0,0,0},{0,0,0,0,0}};
        pascalTriangle(arr);
        // System.out.println(tri);
    }
}
