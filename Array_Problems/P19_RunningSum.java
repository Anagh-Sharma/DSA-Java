package Array_Problems;

import java.util.Arrays;

public class P19_RunningSum {
    static void runningSum(int[] nums) {
        for(int i = 1; i < nums.length; i++) {
            nums[i] = nums[i-1] + nums[i];
        }
        System.out.println(Arrays.toString(nums));
    }
    
    public static void main(String[] args) {
        int nums[] = {1,2,3,4};
        runningSum(nums);
    }
}
