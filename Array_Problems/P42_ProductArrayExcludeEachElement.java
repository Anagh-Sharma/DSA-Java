package Array_Problems;

import java.util.Arrays;

public class P42_ProductArrayExcludeEachElement {

    // TC : O(N)
    static void approach_1(int arr[], int n)
    {
        int res[] = new int[n];
        Arrays.fill(res, 1);
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<n; j++)
            {
                if(j != i)
                {
                    res[i] *= arr[j];
                }
            }
        }
        System.out.println(Arrays.toString(res));
    }
    public static void main(String[] args) {
        int arr[] = {1, 2, 3, 4};
        approach_1(arr, arr.length);
    }
}
