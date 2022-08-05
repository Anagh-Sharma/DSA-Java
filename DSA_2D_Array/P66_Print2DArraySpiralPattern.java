package DSA_2D_Array;
/*
 * - A matrix can be traversed in spiral pattern using the four corners
 *   as indices and incrementally moving the indices to the corners of the
 *   inner rectangle in the matrix:
 * - Top: Top row: Left to right, except last element.
 *   - Aids in traversing the top row, right column, and left column.
 *   - Top row printed as: Array[Top][Left] to Array[Top][Right]
 *   - Incremented after traversal.
 * - Right: Right column: Top to bottom, except first element.
 *   - Aids in traversing the right-most column, top row, and bottom row
 *   - Right column printed as: Array[Top][Right] to Array[Bottom][Right]
 *   - Decremented after traversal.
 * - Bottom: Bottom row: Right to left, except first element. 
 *   - Aids in traversing the bottom row, the right column and left column.
 *   - Bottom row printed as: Array[Bottom][Right] to Array[Bottom][Left]
 *   - Incremented after traversal.
 * - Left: Left column: Bottom to top, except bottom and top elements.
 *   - Aids in traversing the left column, bottom row, and top row.
 *   - Left column printed as: Array[Bottom][Left] to Array[Top][Left]
 *   - Incremented after traversal.
 */
public class P66_Print2DArraySpiralPattern {
    static void spiralMatrix(int[][] arr)
    {
        int top = 0;
        int right = arr[0].length-1;
        int bottom = arr.length-1;
        int left = 0;
        int counter = 0;
        while(top<arr.length && left<arr[0].length)
        {
            /*Top row: Left to right, except last element. */
            for(counter=left; counter<=right; counter++)
            {
                System.out.print(arr[top][counter] + ",");
            }
            top++;

            /*Right column: Top to bottom, except first element. */
            for(counter=top; counter<=bottom; counter++)
            {
                System.out.print(arr[counter][right] + ",");
            }
            right--;

            /*Bottom row: Right to left, except first element. */
            for(counter=right; counter>=left; counter--)
            {
                System.out.print(arr[bottom][counter] + ",");
            }
            bottom--;

            /*Left column: Bottom to top, except bottom and top elements.*/
            for(counter=bottom; counter>=top; counter--)
            {
                System.out.print(arr[counter][left] + ",");
            }
            left++;
        }
    }
    public static void main(String[] args) {
        int arr[][] = {{1,2,3,4}, {5,6,7,8}, {9,10,11,12}, {13,14,15,16}};
        // Row Major
        for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + ",");
            }
            System.out.println();
        }
        spiralMatrix(arr);
    }
}
