package Array_Problems;

public class P28_MajorityElement {
    // TC : O(N)
    static void  majorityElement_approach1(int arr[],int n)
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
            if(hash[k] > n/2)
                System.out.print("Majority element: "+k);
    }
    // Boyer Moore Algorithm
    /*
     * - Boyer-Moore Majority Voting Algorithm:
     * - The algorithm is divided into two parts. A first pass identifies an element as a majority, 
     *   and a second pass confirms that the element identified in the first pass is indeed a 
     *   majority.
     * - Intuition: The occurrence of the Majority Element is N/2 and this is greater than all 
     *   other elements’ occurrences combined. Therefore, if each occurrence of the Majority Element 
     *   denotes +1 and each occurrence of every non-Majority Elements denote -1 then their running 
     *   sum has to be greater than 0.
     * - Analogy: Picture indices of the array as people holding the values there. Now, if any two 
     *   people who hold dissimilar values are adjacent, then they sit down. After all such people 
     *   sit down the remaining standing people are the ones holding the Majority Element value.
     */
    static void majorityElement_approach2(int nums[]) 
    {
        int n = nums.length;
        /*Algorithm Part 1: Finding the Majority element */
        // - Initialize two variables: “num” and “counter”, both set to 0.
        int count = 0;
        int majorityElement = 0;
        // - Iterating over each element.
        for(int i = 0; i < n; i++) {
            /*
             * - If the “counter” is 0, and then the current element is assigned to “num”. 
             *   [Important: “num” is only changed when “counter” value is equal to zero.]
             */
            if(count == 0) {
                majorityElement = nums[i];
            }
            // - Then, if the current element is equal to “num”:
            // - “counter” is incremented
            if(majorityElement == nums[i]) {
                count++;
            }
            /*
             * - Else:
             * - “counter” is decremented
             */
            else {
                count--;
            }
        }

        /*Algorithm Part 2: Confirm that the element is indeed the Majority Element */
        count = 0;
        // - Find the frequency of num variable.
        for(int i = 0; i < n; i++) {
            if(nums[i] == majorityElement) {
                count++;
            }
        }
        /*
         * - If the occurrances of num is equal to N/2:
         * - Num is the Majority Element
         * - Else:
         * - There is no Majority Element
         */
        String msg = count > n/2 ? "Majority Element" : "Not Majority Element";
        System.out.println(msg);
    }
    public static void main(String[] args) {
        int arr[] = {1, 2, 3, 2, 2, 3, 4, 5, 2, 2, 2};
        int n = arr.length;
        majorityElement_approach1(arr, n);
        majorityElement_approach2(arr);
    }
}
