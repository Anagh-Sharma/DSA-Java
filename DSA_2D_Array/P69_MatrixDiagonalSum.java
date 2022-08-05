package DSA_2D_Array;

public class P69_MatrixDiagonalSum {
    /*
     * Add elements:
     * - Left diagonal
     *   Add terms of subarrays with indices initialized to zero and then
     *   incremented iteratively.
     * - Right diagonal
     *   Add terms of subarrays with indices initialized to the length of the
     *   subarray - 1 and then decremented iteratively.
     */
    static void diaSum(int[][] a)
    {
        // Row Major
        for(int i = 0; i < a.length; i++) {
            for(int j = 0; j < a[0].length; j++) {
                System.out.print(a[i][j] + ",");
            }
            System.out.println();
        }
        // Running sum
        int sum=0;
        /*
         * Left to right diagonal elements addition:
         * - Initialize column index to 0
         * - Iterate over the subarrays:
         * - Add element of the subarray at column index to the running sum
         * - Increment the column index
         */
        int colIdx = 0;
        for(int b[] : a)
        {
            sum += b[colIdx];
            colIdx++;
        }
        /*
         * Right to left diagonal elements addition:
         * - Initialize column index to subarray - 1.
         * - Iterate over the subarrays:
         * - Add element of the subarray at column index to the running sum
         * - Decrement the column index
         */
        colIdx = a[0].length-1;
        for(int b[] : a)
        {
            sum += b[colIdx];
            colIdx--;
        }
        /*
         * If the square matrix is of even length, then:
         * Print the running sum
         */
        if(a.length%2==0)
            System.out.println("Matrix Diagonal Sum:"+sum);
        /*
         * Else:
         * The middle element has been added twice, subtract it 
         * from the sum
         * Print the running sum
         */
        else
            System.out.println("Matrix Diagonal Sum:"+(sum-a[a.length/2][a.length/2]));
    }

    public static void main(String[] args) {
        int x[][] = {{1,1,1,2}, {1,1,2,1}, {1,2,1,1}, {2,1,1,1}};
        diaSum(x);
    }
}
