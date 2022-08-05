package Array_Problems;

public class P34_PeakElement {
    static void peakElement_bruteForce(int[] arr, int n)
    {
        int peak = 0;
        if(n == 1) {
            System.out.println(arr[0]);
            System.exit(0);
        }

        for(int i = 0; i < n; i++) {
            if(i == 0) {
                if(arr[i+1] > arr[i]) {
                    peak = i + 1;
                }
            }
            else if(i == n-1) {
                if(arr[i-1] < arr[i]) {
                    peak = i;
                }
            }
            else {
                if(arr[i-1] < arr[i] && arr[i] > arr[i+1]) {
                    peak = i;
                }
            }
        }
        System.out.println(peak);
    }
    /*
     * Binary Search solution:
     * An array consists of several
     * ascending and descending subarrays.
     * The idea is to discard subarrays, and
     * iteratively find peak as mid,
     * according to the following criteria:
     * 
     * - When a mid element is greater than
     *   the next element than the subarray
     *   following the mid is pruned by changing
     *   high to the current mid.
     * - When a mid element is smaller than or equal to
     *   the next element than the subarray
     *   preceding the mid is pruned by changing
     *   low to the current mid+1.
     */
    static void peakElement_BinarySearch(int[] arr, int n)
    {
        int low = 0, high = arr.length - 1;
        while (low < high) 
        {
            int mid = (low + high) / 2;
            if (arr[mid] > arr[mid + 1])
                high = mid;
            else
                low = mid + 1;
        }
        System.out.println(low);
    }
    static int findPeakElement(int[] nums) 
    {
        /*
         * When traversing the array in linear
         * manner, only the subsequent element
         * at an index needs to be checked if it 
         * is larger before returning that index as
         * peak element because the loop has already 
         * checked the previous element to be smaller
         * by iterating to the current index which 
         * means that the previous index was not
         * larger than the current index. 
         */
        for (int i = 0; i < nums.length - 1; i++) 
        {
            if (nums[i] > nums[i + 1])
                return i;
        }
        // For array sorted in ascending order
        return nums.length - 1;
    }
    public static void main(String[] args) {
        int[] arr = {1,2,1,3,5,6,4};
        int n = arr.length;
        peakElement_bruteForce(arr, n);
        System.out.println(findPeakElement(arr));
    }
}
