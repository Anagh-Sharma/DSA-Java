package DSA_2D_Array;

import java.util.Arrays;

public class P67_KWeakestRows {
    /*
     * - Hash map: to store the number of 1s in a row
     * - res: Array of size k to store the result
     * - min: to store the indices of minimum array in the array of arrays, or 2D array.
     * - Iterate k times:
     * - Find the current minimum element in the hash map array
     * - Store its index in res, and set the hash map value at this index to a large value
     */
    static String kWeakestRows(int[][] a, int k)
    {
        int row_size = a.length;
        int col_size = a[0].length;

        int[] hash = new int[row_size];
        for(int i = 0; i < row_size; i++) 
        {
            for(int j = 0; j < col_size; j++) 
            {
                if(a[i][j] == 1)
                {
                    hash[i]++;
                }
            }
        }
        System.out.println(Arrays.toString(hash));
        
        int res[] = new int[k];
        int min = 0;
        int m = 0;
        while(m<3)
        {
            min = 100;
            m++;
            for(int l=0; l<hash.length; l++)
            {
                if(hash[l]!=-1 && hash[l]<min)
                {
                    min = hash[l];
                    res[m] = l;
                }
            }
            for(int l=0; l<hash.length; l++)
            {
                if(hash[l]==min)
                {
                    hash[l] = -1;
                    break;
                }
            }
            k--;
        }
        
        return Arrays.toString(res);
    }
    public static void main(String[] args) {
        int x[][] = {{1,1,0,0,0}, {1,1,1,1,0}, {1,0,0,0,0}, {1,1,0,0,0}, {1,1,1,1,1}};
        int k = 3;
        System.out.println(kWeakestRows(x, k));
    }
}
