package Array_Problems;

public class P38_WindowElementOccurrance {
    // Using Window Sliding
    static void approach_2(int arr[], int x, int k) {
        int n = arr.length;
        int occurrence = 0;
        int window = 0;
        /*
         * Store the occurrances of x in first
         * window of size k starting from index
         * 0 and up to index k-1.
         */
        for(int i = 0; i < k; i++) 
        {
            if(arr[i] == x)
                occurrence++;
        }
        window++;
        System.out.println("Window "+window+" :"+occurrence);
        /*
         * Iterate through the array from index k to size-1.
         * The last index i, of the window acts as the window's
         * index. 
         * A shift to the next window requires inclusion
         * of the element at i + 1 and exclusion of the element
         * at i - k. Where, k is the size of the window.
         */
        for(int j = k; j < n; j++) 
        {
            window++;
            /*
             * When the element at the index
             * included for the new window is
             * equal to x, the occurrence is
             * incremented.
             */
            if(arr[j] == x)
            {
                occurrence++;
            }
            /*
             * When the element at the index
             * excluded for the new window is
             * equal to x, the occurrence is
             * decremented.
             */
            if(arr[j - k] == x)
            {
                occurrence--;
            }
            // Printing the occurrences of x in a window
            System.out.println("Window "+window+" :"+occurrence);
        }
    }

    public static void main(String[] args) {
        int arr[] = {1,2,3,4,5,6};
        int k = 3;
        int x = 2;
        approach_2(arr, x, k);
    }
}
