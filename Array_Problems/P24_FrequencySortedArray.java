package Array_Problems;

public class P24_FrequencySortedArray {

    // TC : O(N)
    static void frequencySortedArray_approach1(int arr[],int n)
    {
        // Find max
        int max = arr[0];
        for(int i=0; i<n; i++)
            if(arr[i]>max)
                max = arr[i];
        
        // Hash to store count of each element
        int hash[] = new int[max+1];
        for(int j=0; j<n; j++)
            hash[arr[j]]++;            

        for(int k=0; k<max+1; k++)
            if(hash[k] != 0)
                System.out.print(k+" "+hash[k]+", ");
        
    }
    static void frequencySortedArray_approach2(int arr[],int n)
    {
        int count = 1;
        for(int i=1; i<n; i++)
        {
            if(arr[i]==arr[i-1])
            {
                count++;
            }
            else
            {
                System.out.println("Frequency of "+ arr[i - 1] + " is: " + count);
                // System.out.println(count);
                count = 1;
            }
        }
        System.out.println("Frequency of "+ arr[n-1] + " is: " + count);
    }
    public static void main(String[] args) {
        int arr[] = {10, 10, 10, 25, 31, 31, 32, 32};
        int n = arr.length;
        frequencySortedArray_approach1(arr, n);
        frequencySortedArray_approach2(arr, n);
    }
}
