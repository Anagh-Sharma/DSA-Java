package Array_Problems;

import java.util.Arrays;

public class P5_SpanOfAnArray {

    // TC : O(n)
    static void  spanArray_approach_1(int arr[], int n)
    {
        int max = arr[0];
        for(int i = 1; i < n; i++) {
            if(arr[i] > max) {
                max = arr[i];
            }
        }

        int min = arr[0];
        for(int i = 1; i < n; i++) {
            if(arr[i] < min) {
                min = arr[i];
            }
        }
        int span = max - min;
        System.out.println("Span of the array is : " + span);
    }

    // TC : O(n)
    static void  spanArray_approach_2(int arr[], int n)
    {
        Arrays.sort(arr);
        int span = arr[n-1] - arr[0];
        System.out.println("Span of the array is : " + span);
    }
    public static void main(String[] args) {
        int arr[] = {20,30,40,90,70,6};
        int n = arr.length;
        spanArray_approach_1(arr, n);
        spanArray_approach_2(arr, n);
    }    
}
