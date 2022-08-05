package Array_Problems;

import java.util.HashMap;

// Consider the sum up to ever endpoint
// taking every element as a starting
// point, iteratively, and incrementing
// a count variable every time the sum is
// found.
public class P37_SubArraysSumN {
    static void approach_1(int arr[], int sum) {
        for(int i = 0; i < arr.length-1; i++) {
            int total = 0;
            for(int j = i+1; j < arr.length; j++) {
                total += arr[j];
                if(total == sum) {
                    System.out.println("Sum Found...");
                    return;
                }
            }
        }
        System.out.println("Not found...");
    }

    // Using window sliding
    static void approach_2(int arr[], int sum) {
        int startIndex = 0;
        int slideSum = arr[0];
        for(int i = 1; i < arr.length; i++) {
            while(slideSum > sum && startIndex < i - 1) {
                slideSum = slideSum - arr[startIndex];
                startIndex++;
            }
            if(slideSum == sum) {
                System.out.println("Sum Found...");
                return;
            }
            if(i < arr.length) {
                slideSum += arr[i];
            }
        }
        if(slideSum == sum) {
            System.out.println("Sum found...");
            return;
        }
        System.out.println("No Such Sum Found...");
    }
    // Prefix sum
    // to find the sum between two indices
    // subtract the prefix sums of the indices
    static int subarraySum_PrefixSum(int[] nums, int k) {
        int count = 0;
        int[] sum = new int[nums.length + 1];
        sum[0] = 0;
        for (int i = 1; i <= nums.length; i++)
            sum[i] = sum[i - 1] + nums[i - 1];
        for (int start = 0; start < nums.length; start++) {
            for (int end = start + 1; end <= nums.length; end++) {
                if (sum[end] - sum[start] == k)
                    count++;
            }
        }
        return count;
    }
    // Hashmap and Prefix sum
    public int subarraySum_HashMap(int[] nums, int k) {
        int count = 0;
        int sum = 0;
        HashMap < Integer, Integer > map = new HashMap < > ();
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) 
        {
            // Prefix sum at index i
            sum += nums[i];
            /*
             * - Each time there is situation when a value equal to: 
             *   the number k subtracted from prefix sum until an index 
             *   exits in the record then, the condition:
             *   sum[i] – k = sum[j] 
             *   becomes true and an occurrence of the required subarray is found
             *   and count is increased with the number of occurrences of the prefix sum.
             */
            if (map.containsKey(sum - k))
                count += map.get(sum - k);
            /*
             * Increment the count of the prefix in th hash map
             * sum:     key
             * value:   map.getOrDefault(sum, 0) + 1
             * where:   map.getOrDefault(sum, 0) is the orignal value at key "sum"
             *          denoting the sum value at the present iteration
             */
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    public static void main(String[] args) {
        int arr[] = {10,20,30,40,50,60,70,90,290};
        int sum = 160;
        approach_2(arr, sum);
    }

}
