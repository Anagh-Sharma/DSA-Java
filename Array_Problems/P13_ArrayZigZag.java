package Array_Problems;

import java.util.Arrays;

public class P13_ArrayZigZag {

    static void swap(int arr[], int i, int j)
    {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    // TN : O(N)
    // Approach: Sort the array, then swap the elements at odd
    // index with its next element 
    static void arrayZigZag_approach_1(int arr[], int n)
    {
        Arrays.sort(arr);
        System.out.println("Sorted array before ZigZag:");
        System.out.println(Arrays.toString(arr));
        for(int i = 1; i <= n - 2; i = i + 2)
            swap(arr, i, i+1);
        System.out.println("Sorted array after ZigZag:");
        System.out.println(Arrays.toString(arr));
    }
    
    // TN : O(N)
    // Approach: Compare three elements and put the largest in the middle,
    // then move to the next such subset.
    static void arrayZigZag_approach_2(int arr[], int n)
    {
        System.out.println("Array before ZigZag:");
        System.out.println(Arrays.toString(arr));
        for(int i=1; i <= n - 2; i = i + 2)
        {
            // Check if i is smaller than either i-1 or i+1 
            if(arr[i] < arr[i-1] || arr[i] < arr[i+1])
            {
                // If i-1 is largest put it in the middle
                if(arr[i-1] > arr[i] && arr[i-1] > arr[i+1])
                    swap(arr, i, i-1);
                // Otherwise put i+1 in the middle
                else 
                    swap(arr, i, i+1);
            }
        }
        System.out.println("Array after ZigZag:");
        System.out.println(Arrays.toString(arr));
    }

    public static void main(String[] args) {
        int arr1[] = {4, 3, 7, 8, 6, 2, 1};
        int arr2[] = {4, 3, 7, 8, 6, 2, 1};
        arrayZigZag_approach_1(arr1, arr1.length);
        arrayZigZag_approach_2(arr2, arr2.length);   
    }
}
