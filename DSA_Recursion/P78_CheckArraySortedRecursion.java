package DSA_Recursion;

public class P78_CheckArraySortedRecursion {
    /*
     * Every consecutive pair is compared from end of the array to the beginning.
     * The recursive function is passed an index and the the computation compares
     * the index's two preceding indices and a boolean result is ANDed with a recursive
     * call to the array with a decremented index.
     */
    static boolean arrayAscendingCheck(int arr[], int n)
    {
        /*
         * Base case:
         * When array has reached the last indices, which have already been
         * checked so a default true is returned.
         * Here, 0 value index is checked for the case when an empty array
         * is passed, otherwise no recursive call will occur after the index has
         * reached 1. A default true value is returned for the index 0.
         */
        if(n==0 || n==1)
            return true;
        /*
         * Recursive case:
         * The preceding two terms to the current index 
         * are compared and the boolean result is ANDed 
         * with a recursive call to the previous index.
         * A false boolean value during unwinding will
         * result in the final returned value to be false.
         */
        return arr[n-2] <= arr[n-1] && arrayAscendingCheck(arr, n-1);
    }
    public static void main(String[] args)
    {
        int arr[] = {1, 2, 3, 4};
        int n = arr.length;
        if (arrayAscendingCheck(arr, n) == true)
            System.out.println("Sorted!");
        else
            System.out.println("Unsorted!");
    }
}
