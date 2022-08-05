package Array_Problems;

import java.util.Arrays;

public class P41_NFibonacciSeries {
    // Wrong: nFibonacciSeries_bruteForce
    static void nFibonacciSeries_bruteForce(int n, int size) {
        // Assuming m >= n.
        int[] arr = new int[size];
        arr[n - 1] = 1;

        for (int i = n; i < size; i++)
            for (int j = i - n; j < i; j++)
                arr[i] += arr[j];
       
        for (int i = 0; i < size; i++)
            System.out.print(arr[i] + " ");
    }
    static void nFibonacciSeries_windowSliding(int n, int size) 
    {
        int[] nFibSeries = new int[size];
        int value = 0;
        /*
         * Insert initial N values.
         * Calculate sum of first N values.
         */
        for(int i=0; i<n; i++)
        {
            nFibSeries[i] = i;
            value +=i;
        }
        /*
         * Iterate from N to size - 1.
         */
        for(int j=n; j<size; j++)
        {
            /*
             * Sores the sum of values from 
             * indices j-N to j-1 at index j.
             */
            nFibSeries[j] = value;
            /*
             * - Calulate the value for the next window with its last index j+1.
             * - For every subsequent index j+1, add value at j and subtract value at j-N.
             */
            value += nFibSeries[j] - nFibSeries[j-n];
        }
        System.out.println(Arrays.toString(nFibSeries));
        System.out.println(value);
    }

    public static void main(String args[])
    {
        int N = 4, size = 20;
        // nFibonacciSeries_bruteForce(N, size);
        nFibonacciSeries_windowSliding(N, size);
    }
}
