package Array_Problems;

import java.util.Arrays;

public class P27_NumbersSmallerThanCurrent {
    static void numbersSmaller_approach1(int[] nums)
    {
        int n = nums.length;
        int res[] = new int[n];
        int count = 0;
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<n; j++)
            {
                if(nums[j]<nums[i])
                    count++;
            }
            res[i] = count;
            count = 0;
        }
        System.out.println(Arrays.toString(res));
    }
    static void numbersSmaller_approach2(int[] nums)
    {
        int n = nums.length;
        // Create a copy of the array
        int nums_copy[] = new int[n];
        for (int k=0; k<n; k++)
            nums_copy[k] = nums[k];
        // Sort the copied array
        Arrays.sort(nums_copy);
        // The position of each element in the sorted array
        // tells us the total number of elements that are 
        // smaller than it.

        // Store the position of each element in a hash map:
        // The value of each element in the sorted array
        // is mapped to its position in the sorted array
        // as index(element value) -> value(position in the sorted array)
        int map[] = new int[nums_copy[n-1]+1];
        for(int i=n-1; i>=0; i--) 
        {
            map[nums_copy[i]] = i;
        }
        // Use each element value to find
        // its position in the sorted array from the map.
        // Store this position in place of the element value
        for(int j=0; j<n; j++)
        {
            nums[j] = map[nums[j]];
        }
        System.out.println(Arrays.toString(nums));
    }
    public static void main(String[] args) {
        int arr[] = {8,1,2,2,3};
        numbersSmaller_approach1(arr);
        numbersSmaller_approach2(arr);
    }
}
