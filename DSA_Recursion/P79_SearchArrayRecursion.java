package DSA_Recursion;

public class P79_SearchArrayRecursion {
    /*
     * Recursively check left most and rightmost elements by incrementing and decrementing the
     * indices for every subsequent recursive call.
     */
    static int searchArrayRecursion(int arr[], int l, int r, int num)
    {
        /*
         * Base cases:
         * If the entire array has been traversed: return -1.
         * If either left or right indices have the required element: return the index.
         */
        if (r < l)
            return -1;
        else if (arr[l] == num)
            return l;
        else if (arr[r] == num)
            return r;
        /*
         * Recursive case:
         * Check the elements after the left index and before the right index.
         */
        return searchArrayRecursion(arr, l+1, r-1, num);
    }
    public static void main(String[] args)
    {
        int arr[] = {45, 78, 88, 23, 23};
        int num = 78;
        int index = searchArrayRecursion(arr, 0, arr.length-1, num);
        if (index != -1)
            System.out.println("Found!");
        else
            System.out.println("Not found!");
        }
}
