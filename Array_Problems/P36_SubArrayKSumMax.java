package Array_Problems;

public class P36_SubArrayKSumMax {
    static void approach_1(int arr[], int k, int n) {
        int maxSlideSum = 0;
        for(int i = 0; i <= n - k; i++) {
            int sum = 0;
            for(int j = i; j < i + k; j++) {
                sum += arr[j];
            }
            if(sum>maxSlideSum)
                maxSlideSum = sum;
        }
        System.out.println(maxSlideSum);
    }

    // Using Window Sliding
    static void approach_2(int arr[], int n, int k) {
        int maxSlideSum = 0;
        for(int i = 0; i < k; i++) {
            maxSlideSum = maxSlideSum + arr[i];
        }
        for(int i = k; i < n; i++) {
            if(maxSlideSum < maxSlideSum + arr[i] - arr[i - k])
                maxSlideSum = maxSlideSum + arr[i] - arr[i - k];
        }
        System.out.println(maxSlideSum);
    }

    public static void main(String[] args) {
        int arr[] = {1,2,3,4,5,6};
        int n = arr.length;
        int k = 3;
        approach_1(arr, k, n);
        // System.out.println();
        approach_2(arr, n, k);
    }
}
