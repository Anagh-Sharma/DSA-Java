package DSA_2D_Array;
/*
 * Rotating a 2D array 90 deg clockwise involves:
 * - Turning the elements of the first subarray to the last elements of the subarrays, and so on.
 * - In other words: Turning the first row into the last column, and so on.
 * 
 * This takes two steps:
 * - Transposing the matrix: The first row becomes the first column, and so on.
 * - Reversing the rows: The first column becomes the last column.
 */
public class P63_2DArrayRotate90Clock {
    static void rotate(int[][] a) 
    {
        System.out.println("Before rotation:");
        // Row Major
        for(int i = 0; i < a.length; i++) {
            for(int j = 0; j < a[0].length; j++) {
                System.out.print(a[i][j] + ",");
            }
            System.out.println();
        }
        /*
         * Transposing the matrix.
         */
        for(int i = 0; i < a.length; i++) 
        {
            for(int j = i + 1; j < a.length; j++) 
            {
                int temp = a[i][j];
                a[i][j] = a[j][i];
                a[j][i] = temp;
            }
        }
        /*
         * - Iterating through each subarray of the array of arrays.
         * - In other words: Iterating through rows of the 2D array
         */
        for(int []b : a) 
        {
            /*
             * - Reversing each subarray of the array of array.
             * - In other words: Reversing each row of the 2D array.
             */
            for(int i = 0, j = a.length - 1; i < a.length/2; i++,j--) 
            {
                int temp = b[i];
                b[i] = b[j];
                b[j] = temp;
            }
        }
        System.out.println("After rotation:");
        // Row Major
        for(int i = 0; i < a.length; i++) {
            for(int j = 0; j < a[0].length; j++) {
                System.out.print(a[i][j] + ",");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        int x[][] = {{2,1,4,6}, {3,4,6,6},{1,4,7,8}, {2,2,5,7}};
        rotate(x);
    }
}
