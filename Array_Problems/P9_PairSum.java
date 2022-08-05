package Array_Problems;

import java.util.Arrays;
import java.util.HashMap;

public class P9_PairSum {
    // Skipping over brute force approach of two nested for loops

    // Two pointer approach
    // Finds the first pair
    static boolean pairsum_approach_1(int[] nums, int n, int k)
    {
        Arrays.sort(nums);
        int i = 0;
        int j = n - 1;
        while(i < j) 
        {
            if(nums[i] + nums[j] == k) 
            {
                return true;
            }
            else if(nums[i] + nums[j] < k) 
            {
                i++;
            }
            else //if(nums[i] + nums[j] > k) 
            {
                j--;
            }
        }
        return false;
    }
    // Using HashMap / HashTable
    // TC : 0(n), SC : 0(n)
    static void pairsum_approach_2(int arr[], int n, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int element : arr) {
            if(map.get(element) == null) {
                map.put(k - element, element);
            }
            else {
                System.out.println("Pair Sum Found..." + map.get(element) + "," + element);
            }
        }
    }
    static void pairsum_approach_3(int[] nums, int k)
    {
        int n = nums.length;
        for(int i=0; i<n; i++)
        {
            for(int j=i+1; j<n; j++)
            {
                if(nums[i] + nums[j] == k)
                {
                    System.out.println("Pair: " + nums[i] + "," + nums[j]);
                }
            }
        }
    }

    public static void main(String[] args) {
        int arr[] = {2,7,11,5,4};
        int n = arr.length;
        int k = 9;
        pairsum_approach_2(arr, n, k);
        pairsum_approach_3(arr, k);
        System.out.println(pairsum_approach_1(arr, n, k));
    }
}
