package Array_Problems;
/*
 * Two pointer approach:
 * Search for positive number with pointer 1
 * Pointer 1: first index
 * Pointer 2: first index
 * Case 1: +- Swap, and increment both pointers
 * Case 2: -+ increment both
 * Case 3: -- increment pointer 1
 * Case 4: ++ increment pointer 2
 */

import java.util.Arrays;

public class P25_ArrayPosNegRightLeft 
{  
    static void swap(int arr[], int i, int j)
    {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
    
    // O(N)
    static void rearrangeArray(int arr[], int n)
    {
        int i=0, j=0;
        while(j<n)
        {
            // Cases 1 and 4
            if(arr[i]>0)
            {
                if(arr[j]<0)
                {
                    swap(arr, i, j);
                    i++;
                    j++;
                }
                else
                    j++;
            }
            // Cases 2 and 3
            else if(arr[i]<0)
            {
                i++;
                j++;
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    public static void main(String[] args) {
        int arr[] = {10, 21, -11, -20, 15, 19, -30};
        int n = arr.length;
    
        rearrangeArray(arr, n);
    }
}