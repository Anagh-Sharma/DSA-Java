package Array_Problems;
/*
 * - Binary Search is a searching algorithm used in a sorted array by repeatedly dividing the 
 *   search interval in half. 
 * - The idea of binary search is to use the information that the array is sorted and reduce 
 *   the time complexity to O (Log n).
 * - Binary Search Algorithm: We basically ignore half of the elements just after one comparison.
 * - Compare x with the middle element.
 * - If x matches with the middle element, we return the mid index.
 * - Else If x is greater than the mid element, then x can only lie in the right half subarray 
 *   after the mid element. So, we recur for the right half.
 * - Else (x is smaller) recur for the left half.
 * - Time Complexity: O (log N)
 */

public class P30_BinarySearch {
    public static void main(String[] args) {
        int arr[] = {10,20,30,40,50,60,70,80};
        int search = 70;
        int low = 0;
        int mid = 0;
        int high = arr.length - 1;
        // Iteration counter
        int i = 0;
        while(low < high) 
        {
            mid = (low + high) / 2;
            if(search == arr[mid]) 
            {
                System.out.println("Element found...");
                return;
            }
            if(search > arr[mid]) 
            {
                low = mid + 1;  // Right half
            }
            else if(search < arr[mid]) 
            {
                high = mid - 1; // Left Half
            }
            i++;
            System.out.println("Iteration : " + i);
        }
    }
}
