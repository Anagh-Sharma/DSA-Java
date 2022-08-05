package DSA_2D_Array;

import java.util.LinkedList;
import java.util.List;
/* 
 * - Another intuition:
 * - Find the largest number in a column
 * - Check if it is the smallest number in its row as well.
 */
public class P62_LuckyNumberMatrix {
    static void luckyNumber(int[][] arr)
    {
        List<Integer> luckyNums = new LinkedList<>();
        /*maxColNum: store the largest number in the column. */
        int maxColNum = Integer.MIN_VALUE;
        /*maxRowIdx: Store the index of the row with the largest element. */
        int maxRowIdx = 0;
        /* boolean found: Validate if in the row with the maximum element, 
         * a smaller element is present. 
         */
        boolean found = true;
        /*
         * - Iterate through the columns by iterating through subarray indices.
         *   [To find maximum element in each column.]
         */
        for(int i = 0; i < arr[0].length; i++) 
        {
            /*
             * - Find maximum element in a column.
             *   [Iterate through values of a column by iterating through subarrays.]
             * - Store the index of the row with the maximum number.
             */
            for(int j = 0; j < arr.length; j++) 
            {
                if(arr[j][i]>maxColNum)
                {
                    maxColNum = arr[j][i];
                    maxRowIdx = j;
                }
            }
            /*
             * - Iterate through values of the subarray with the maximum element.
             * - If a number smaller than the maximum number is found then: 
             *   - Set found as false.
             */
            for(int b: arr[maxRowIdx])
            {
                
                if(b<maxColNum)
                {
                    found = false;
                    break;
                }
            }
            /*
             * If found is true, then:
             * - The current number, largest in its column is the lucky number
             *   since no number smaller than it was found in its row.
             */
            if(found==true)
            {
                System.out.println("Lucky number: "+maxColNum);
                luckyNums.add(maxColNum);
                break;
            }
            /*
             * Else:
             * - Reset maxColNum and found for the next iteration's computations.
             * Iterate to the next column, as this number could not be the lucky 
             * number since there is number smaller than it in its row.
             */
            else
            {
                maxColNum = Integer.MIN_VALUE;
                found = true;
            }
        }
    }
    /* Incorrect as this solution matches minimum of any row with maximum of column.
     * The lucky number need to be largest in its column and smallest in its row. 
     */

    static void luckyNumber2(int arr[][])
    {
        List<Integer> maxCol = new LinkedList<>();
        List<Integer> minRow = new LinkedList<>();
        List<Integer> result = new LinkedList<>();
        int minRowE = Integer.MAX_VALUE;
        int maxColE = Integer.MIN_VALUE;

        for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j < arr[i].length; j++) {
                minRowE = Math.min(minRowE, arr[i][j]);
            }
            minRow.add(minRowE);
            minRowE = Integer.MAX_VALUE;
        }

        for(int i = 0; i < arr[0].length; i++) {
            for(int j = 0; j < arr.length; j++) {
                maxColE = Math.max(maxColE, arr[j][i]);
            }
            maxCol.add(maxColE);
            maxColE = Integer.MIN_VALUE;
        }

        for(int each : minRow) {
            if(maxCol.contains(each)) {
                result.add(each);
            }
        }

        System.out.println(result);
    }
    public static void main(String[] args) {
        // int arr[][] = {{3,7,8},{9,11,13},{15,16,17}};
        int arr[][] = {{1,10,4,2},{9,3,8,7},{15,16,17,12}};
        luckyNumber(arr);
    }
}
