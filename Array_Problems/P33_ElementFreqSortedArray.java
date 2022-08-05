package Array_Problems;

public class P33_ElementFreqSortedArray {

    // TC : O(logN)
    static void elementFreq(int arr[], int search)
    {
        int low = 0;
        int mid = 0;
        int high = arr.length - 1;
        // Iteration counter
        int first=-1, last=-1;
        while(low < high) 
        {
            mid = (low + high) / 2;
            if(search == arr[mid]) 
            {
                // System.out.println("Element found...");
                if(first==-1)
                    first = mid;
                last = mid;
                // return;
            }
            if(search > arr[mid]) 
            {
                low = mid + 1;  // Right half
            }
            else if(search < arr[mid]) 
            {
                high = mid - 1; // Left Half
            }
        }
        System.out.print("Number of elements: " +(last-first));
    }
    public static void main(String[] args) {
        int arr[] = {5,7,7,8,8,10};
        int x = 8;
        elementFreq(arr, x);
    }
}
