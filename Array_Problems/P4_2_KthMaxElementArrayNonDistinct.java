package Array_Problems;

public class P4_2_KthMaxElementArrayNonDistinct {
    static int kMax(int[] nums, int k)
    {
        int n = nums.length;
        int max = 0;
        long currMax = 0;
        Long lastMax = Long.MAX_VALUE;
        int kMaximum = 0, currMaxIndex=0;
        for(int i=0; i<k; i++)
        {
            currMax = Long.MIN_VALUE;// current max is set to smallest possible num
            // find num larger than curr max but smaller than or equal to the lastmax
            for (int j=0; j<n; j++)
            {
                if (nums[j] <= lastMax && nums[j] > currMax)
                {
                    currMax = nums[j];
                    currMaxIndex = j;
                }
            }
            // Set index where currMax is to avery small value so that it gets skipped
            // in the next iteration of i
            nums[currMaxIndex] = -128;
            if(i==0&&k==1)
                return (int)currMax;
            else if(i==k-1)
            {
                kMaximum = (int)currMax;
            }
            // Lastmax for next iteration is current max 
            lastMax = currMax;
        }
        if(k==1)
            return max;
        else
            return kMaximum;
    }
    public static void main(String[] args) {
    int arr[] = {3,2,3,1,2,4,5,5,6};
    int k = 4;
    System.out.println("K largest : "+kMax(arr, k));
    }
}
