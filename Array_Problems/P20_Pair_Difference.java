package Array_Problems;

import java.util.Arrays;

public class P20_Pair_Difference {

    // TC : O(N^2)
    static void pairDifference_approach_1(int arr[], int n, int k)
    {
        System.out.println("Method 1:");
        int flag = 0;

        for(int i = 0; i<n; i++)
            for(int j = 0; j<n; j++)
            {
                if((arr[i]-arr[j]) == k && i!=j)
                {
                    flag = 1;
                    System.out.println("Pair found: "+arr[i]+", "+arr[j]);
                }
            }
        if(flag == 0)
            System.out.println("No such pair found.");
    }

    // TC : O(N)
    static void pairDifference_approach_2(int arr[], int n, int k)
    {
        System.out.println("Method 2:");
        int flag = 0;
        Arrays.sort(arr);
        int i = 0, j = 0;            
        while(i<n)
        {
            if(arr[i] - arr[j] == k)
            {
                flag = 1;
                System.out.println("Pair found: "+arr[i]+", "+arr[j]);
                i++;
                j++;
            }
            else if(arr[i] - arr[j] > k)
                j++;
            else
                i++;
        }
        if(flag == 0)
            System.out.println("No such pair found.");
    }
    static void pairDifference_approach_3(int[] nums, int k)
    {
        int n = nums.length;
        for(int i=0; i<n; i++)
        {
            for(int j=i+1; j<n; j++)
            {
                if(nums[i] - nums[j] == k || nums[i] - nums[j] == -k)
                {
                    System.out.println("Pair: " + nums[i] + "," + nums[j]);
                }
            }
        }
    }

    public static void main(String[] args)
    {
        int arr[] = {3, 1, 4, 1, 5};
        int k = 2;
        pairDifference_approach_1(arr, arr.length, k);
        pairDifference_approach_2(arr, arr.length, k);
        pairDifference_approach_3(arr, k);
    }
}
