package Array_Problems;

import java.util.Arrays;

public class P16_MissingNumber {
    // This only works if the array has only natural numbers
    // For whole numbers calculate sum f first N natural numbers with N as size
    // of the array instead of size+1.
    // and the array either starts with 1 or if 1 is missing.
    static void approach_1(int arr[], int n) {
        int sum = 0;
        // Calculating the running sum
        for(int i = 0; i < n; i++) {
            sum = sum + arr[i];
        }
        // We take the sum of first N natural numbers as the expected result
        // N value is size+1 as one number is expected to be missing
        int total = (n+1) * (n+2) / 2;
        int missing = total - sum;
        System.out.println("Missing Number is : " + missing);
    }

    static void approach_2(int arr[], int n) {
        int x = 0;
        int y = 0;

        // True cumulative XOR of the array values
        for(int i = 0; i < n; i++) {
            x = x ^ arr[i];
        }
        // Expected cumulative XOR of the array values
        // If the array is of whole numbers then the loop will run
        // until N
        for(int i = 0; i <= n+1; i++) {
            y = y ^ i;
        }
        // XOR of true cumulative XOR and expected cumulative XOR
        // The pairs will become zero and the single odd occurring
        // mising element will be identified.
        int missing = x ^ y;
        System.out.println("Missing Number is : " + missing);
    }

    static void approach_3(int[] arr, int n)
    {
        // Sort the array
        Arrays.sort(arr);
        // Iterate from the min value to the min+size of array values
        for(int i=arr[0], j=0; i<n+arr[0]; i++, j++)
        {
            // The XOR of iterator and array element should be same
            // and the XOR of these values
            // will not be zero when they are not same 
            if((i^arr[j])!=0)
            {
                System.out.println("Missing Number is : "+i);
                break;
            }
        }
    }

    public static void main(String[] args) {
        int arr[] = {1,2,3,5,6,7,8};
        // int arr[] = {3,0,1};
        int n = arr.length;
        approach_1(arr, n);
        approach_2(arr, n);
        approach_3(arr, n);
    }
}
