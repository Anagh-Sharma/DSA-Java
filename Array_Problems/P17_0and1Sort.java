package Array_Problems;

import java.util.Arrays;

public class P17_0and1Sort {
    static void sort01(int[] nums)
    {
        int n = nums.length;
        int count=0;
        for(int i=0; i<n; i++)
        {
            if(nums[i]==1)
                count++;
            nums[i] = 0;
        }
        for(int j=n-1; j>n-count-1; j--)
        {
            nums[j] = 1;
        }
        System.out.println(Arrays.toString(nums));
    }
    public static void main(String[] args) {
        int arr[] = {0,0,1,1,1,1,0,0,1,0,0,1};
        sort01(arr);
    }
}
