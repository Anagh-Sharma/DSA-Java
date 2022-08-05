package Array_Problems;

public class P35_SubArrayKSum {
    static void approach_1(int arr[], int k, int n) {
        for(int i = 0; i <= n - k; i++) {
            int sum = 0;
            for(int j = i; j < i + k; j++) {
                sum += arr[j];
            }
            System.out.print(sum + ", ");
        }
    }

    // Using Window Sliding
    static void approach_2(int arr[], int n, int k) {
        int slideSum = 0;
        for(int i = 0; i < k; i++) {
            slideSum = slideSum + arr[i];
        }
        System.out.print(slideSum + ", ");
        for(int i = k; i < n; i++) {
            slideSum = slideSum + arr[i] - arr[i - k];
            System.out.print(slideSum + ", ");
        }
    }
    static void approach_3(int arr[], int n, int k) {
        for(int i=n-1; i>0; i--) 
        {
            arr[i-1] = arr[i-1] + arr[i];
        }
        for(int i = 0; i < n-k; i++) {
            System.out.print(arr[i]-arr[i+k] +", ");
        }
        System.out.print(arr[n-k]);
    }

    public static void main(String[] args) {
        int arr[] = {1,2,3,4,5,6};
        int n = arr.length;
        int k = 3;
        approach_1(arr, k, n);
        System.out.println();
        approach_2(arr, n, k);
        System.out.println();
        approach_3(arr, n, k);
    }
}
