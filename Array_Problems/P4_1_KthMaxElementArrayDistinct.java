package Array_Problems;

public class P4_1_KthMaxElementArrayDistinct {
    static int kMaxDistinct(int[] nums, int k)
    {
        int n = nums.length;
        int max = 0;
        long currMax = 0;
        Long lastMax = Long.MAX_VALUE;// last max is largest possible num as all elements will be
        // smaller and thus will be selected in the if statement of the nested for loop
        // For the next lastMax will be set to curr max or the found maximum element as
        // the max num of the next iteration will have to smaller than this.
        int kMaximum = 0;
        for(int i = 0; i<k; i++)
        {
            currMax = Long.MIN_VALUE;// current max is smallest possible num
            // find num larger than curr max but smaller than lastmax
            for (int j = 0; j < n; j++)
            {
                if (nums[j] < lastMax && nums[j] > currMax) 
                    currMax = nums[j];
            }
            
            if(i==0)// Store maximum
                max = (int)currMax;
            if(i==k-1)
            {
                if(currMax == Long.MIN_VALUE)// Return maximum if no third maximum
                    kMaximum = max;
                else
                    kMaximum = (int)currMax;
            }
            // Lastmax for next iteration is current max 
            lastMax = currMax;
        }
        return kMaximum;
    }
    public static void main(String[] args) {
        int arr[] = {3,2,3,1,2,4,5,5,6};
        int k = 4;
        System.out.println("K largest : "+kMaxDistinct(arr, k));
    }
}
