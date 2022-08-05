package Array_Problems;

import java.util.Arrays;

public class P18_DutchNationalFlagProblem {
    static void swap(int[] arr, int first, int second) {
        int temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;
    }
    
    static void dutchNationalFlag_approach1(int[] nums)
    {
        int n = nums.length;
        // Low pointer: Insert 0s from the beginning
        int low = 0; 
        // High pointer: Insert 2s from the end
		int high = n - 1;
        // Search pointer: Search for 1s and 2s and swap them with Low and High respectively. 
		int search = 0;
		while (search <= high) 
		{ 
			switch (nums[search]) 
			{ 
                case 0:
                    swap(nums, low, search);
                    low++; 
                    search++; 
                    break;
                case 1:
                    search++; 
                    break; 
                case 2:
                    swap(nums, search, high);
                    high--; 
                    break;
			} 
		}
        System.out.println(Arrays.toString(nums));
    }

    static void dutchNationalFlag_approach2(int[] nums) {
        int n = nums.length;
        int count=0, count2=0;
        for(int i=0; i<n; i++)
        {
            if(nums[i]==1)
                count++;
            else if(nums[i]==2)
                count2++;
            nums[i] = 0;
        }
        for(int j=n-1; j>=n-count2-count; j--)
        {
            if(j>=n-count2)
                nums[j]=2;
            else
                nums[j]=1;
        }
        System.out.println(Arrays.toString(nums));
    }
    public static void main(String[] args) {
        int arr[] = {2,0,2,1,1,0};
        dutchNationalFlag_approach1(arr);
        dutchNationalFlag_approach2(arr);
    }
}
