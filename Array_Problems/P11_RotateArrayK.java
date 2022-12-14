package Array_Problems;

public class P11_RotateArrayK {
    static void rotate_approach_1(int arr[], int n, int k) {
        for(int j = 1; j <= k; j++) {
            int x = arr[n-1];
            for(int i = n - 1; i > 0; i--) {
                arr[i] = arr[i-1];
            }
            arr[0] = x;
        }

        System.out.println("Array After Rotation...");
        for(int i : arr) {
            System.out.print(i + ",");
        }
    }

    static void rotate_approach_2(int arr[], int n, int k) {
        int temp[] = new int[n];
        for(int i = 0; i < n; i++) {
            temp[(i + k) % n] = arr[i];
        }
        System.out.println("Array After Rotation...");
        for(int i : arr) {
            System.out.print(i + ",");
        }
    }

    static void rotate_approach_3_reverse(int arr[], int i, int j) {
        while(i < j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
    }
    public static void main(String[] args) {
        int arr[] = {1,2,3,4,5,6,7};
        int n = arr.length;
        int k = 3;
        rotate_approach_1(arr, n, k);

        // Approach 3: Optimized
        // range case handle
        k = k % n;
        // negative case handle
        if(k < 0)
        {
            k = n + k;
        }
        rotate_approach_3_reverse(arr, 0, n-k-1);
        rotate_approach_3_reverse(arr, n-k, n-1);
        rotate_approach_3_reverse(arr, 0, n-1);
    }
}
