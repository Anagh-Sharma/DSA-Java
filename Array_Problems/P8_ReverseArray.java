package Array_Problems;

public class P8_ReverseArray {
    // 2 pointer
    // TC : O(N)
    static void reverse(int arr[], int n) {
        int i = 0;
        int j = n-1;
        int temp;
        while(i < j) {
            temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
        for(int k=0; k<n; k++)
        {
            System.out.print(arr[k]);
        }
    }
    public static void main(String[] args) {
        int arr[] = {3,1,0,7,5};
        int n = arr.length;
        reverse(arr, n);
    }
}
