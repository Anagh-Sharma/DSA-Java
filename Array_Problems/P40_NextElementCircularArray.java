package Array_Problems;

import java.util.Arrays;

public class P40_NextElementCircularArray {
    static void nextGreaterCircular(int[] nums) 
    {
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) 
        {
            res[i] = -1;
            for (int j = 1; j < nums.length; j++) 
            {
                if (nums[(i + j) % nums.length] > nums[i]) 
                {
                    res[i] = nums[(i + j) % nums.length];
                    break;
                }
            }
        }
        System.out.println(Arrays.toString(res));
    }
    

    public static void main(String[] args) {
        int arr[] = {1,2,1};
        nextGreaterCircular(arr);
    }
}
