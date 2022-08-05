package Array_Problems;

public class P6_PrintBarChart {
    public static void main(String[] args) {
        int arr[] = {3,1,0,7,5};
        int n = arr.length;
        int max = arr[0];
        /* Search for the max element (Sets the maximum frequency that needs to be represented as 
        the maximum number of rows). */
        for(int ele : arr) 
        {
            if(ele > max)
            {
                max = ele;
            }
        }
        /*
        -	There will be two nested loops.

        	First (outer) loop [Variable: i]: Will start from the max element, and then decrement 
            by step size 1.
        -   This loop iterates over the rows of the matrix (bar chart).

            Second (inner) loop [Variable: j]: Will iterate over the array (column’ total star count).
        -	This loop represents the columns in the matrix and an index in the array.
        -	The bar chart stars (or any character) are being printed top to bottom.
        -	The elements in the array represent how many stars are to be printed in its column.
        -	A column can only have a star in a certain row, if and only if, the row value is either 
            equal to or less than the value of that column (or array value at that index j).
        */
        // TC : O(n^2)
        for(int i = max; i >= 1; i--) {
            for(int j = 0; j < n; j++) {
                if(i <= arr[j]) {
                    System.out.print("*\t");
                }
                else {
                    System.out.print("\t");
                }
            }
            System.out.println();
        }
    }   
}
