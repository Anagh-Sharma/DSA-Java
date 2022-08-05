package Array_Problems;

public class P12_RemoveDuplicatesSortedArray {
    /*
     * Two pointer approach:
     * 
     * First pointer i, starts at index 0
     * Second pointer j, searches for an element different than the element at i
     * If different character is found it is copied at i+1 and i and j are incremented
     * If different is not found then j is incremented
     */
    static void removeDuplicate(int []arr, int n) {
        int i=0, j=1;
        while(i<n && j<n)
        {
            if(arr[i] != arr[j])
            {
                arr[i+1] = arr[j];
                i++;
                j++;
            }
            else
                j++;
            
        }

        // Same logic but less clear:

        // int j = 0;
        // for(int i = 1; i < n; i++) {
        //     if(arr[i] != arr[j]) 
        //     {
        //         j++;
        //         arr[j] = arr[i];
        //     }
        // }
        // return j+1;
    }

    public static void main(String[] args) {
        int arr[] = {1,1,1,1,2,2,2,2,3,3,3,4,4,4,4,4,5};
        int n = arr.length;
        //int k = 
        removeDuplicate(arr, n);
        for(int i = 0; i < n; i++) {
            System.out.print(arr[i] + ",");
        }
    }
}
