package DSA_2D_Array;
/*
 * Matrix transpose
 * - Swap elements: arr[i][j] with arr[j][i]
 * - Element at first index as subarray index and second index as element index
 * - Element at first index as element index and second index as row index

 */
public class P61_Transponse2DArray 
{
    public static void main(String[] args) 
    {
        int arr[][] = {{1,2,3}, {4,5,6}, {7,8,9}};
        int temp = 0;
        for(int i = 0; i < arr.length; i++)  {
            for(int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + ",");
            }
            System.out.println();
        }

        for(int i = 0; i < arr.length; i++)  {
            for(int j = i+1; j < arr[i].length; j++) {
                temp = arr[i][j];
                arr[i][j] = arr[j][i];
                arr[j][i] = temp;
            }
        }

        for(int i = 0; i < arr.length; i++)  {
            for(int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + ",");
            }
            System.out.println();
        }

    }
}
