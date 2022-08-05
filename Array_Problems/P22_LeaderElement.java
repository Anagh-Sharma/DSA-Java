package Array_Problems;

public class P22_LeaderElement {

    // TC : O(N)
    static void leaderElement(int arr[], int n)
    {
        int flag = 0;
        int i = 0, j = 0;
        for(i=0; i<n; i++)
        {
            flag = 0;
            for(j=i; j<n; j++)
            {
                if(arr[j] > arr[i])
                    flag = 1;
                else
                    continue;
            }
            if(flag == 0)
                System.out.println("Leader element: "+arr[i]);
        }
    }
    public static void main(String[] args) {
        int arr[] = {20, 10, 50, 16, 19, 18, 7};
        leaderElement(arr, arr.length);
    }
}
