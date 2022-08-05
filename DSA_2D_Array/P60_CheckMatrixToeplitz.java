package DSA_2D_Array;
/*
 * Check Toeplitz matrix
 * - Check if every element in a diagonal is the same.
 * - A 2D array is traversed diagonally by: moving to the next subarray and then, 
 *   moving to the next element of the next subarray.
 * - Visually, the move is one step rightwards and one step downwards.
 * - Iterate over each subarray (or row, when displayed in row major order) of the 2D array:
 * - Each element in a subarray (or row, when displayed in row major order) is compared to 
 *   its corresponding diagonal element, one step to the next subarray and one step to the 
 *   next element of the next subarray.
 */
public class P60_CheckMatrixToeplitz {
    static boolean isToeplitz(int [][]arr) 
    {
        for(int i = 0; i < arr.length-1; i++) 
        {
            for(int j = 0; j < arr[i].length-1; j++) 
            {
                if(arr[i][j] != arr[i+1][j+1]) 
                {
                    return false;
                }
            }
        }
        return true;
    }
    public static void main(String[] args) {
        int arr[][] = {{1,2,3,4}, {5,6,7,7}, {3,2,5,7}, {4,5,6,32}};
        System.out.println(isToeplitz(arr));
    }
}
