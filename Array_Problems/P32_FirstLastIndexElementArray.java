package Array_Problems;

public class P32_FirstLastIndexElementArray {

    // TC : O(N)
    // Brute force
    static void firstLastIndex(int arr[], int x, int n)
    {
        int first=-1, last=-1;
        for(int i=0; i<n; i++)
        {
            if(arr[i]==x)
            {
                if(first==-1)
                    first = i;
                last = i;
            }
        }

        System.out.print("["+first+", "+last+"]");
    }
    // TC : O(log N)
    // Binary search
    static void firstLastIndex_BinarySearch(int arr[], int search)
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
        System.out.print("["+first+", "+last+"]");
    }
    public static void main(String[] args) {
        int arr[] = {5,7,7,8,8,10};
        int n = arr.length;
        int x = 8;
        firstLastIndex(arr, x, n);
    }
}
