package Array_Problems;

public class P15_OddNumberOccurrance {

    // TC : O(N)
    static void oddNumberOccurrance(int arr[])
    {
        int hash[] = new int[100];

        for(int i = 0; i < arr.length; i++) 
        {
            hash[arr[i]] = ++hash[arr[i]];
        }

        for(int i = 0; i < hash.length; i++) 
        {
            if(hash[i] == 1) 
            {
                System.out.println("The element with single (odd) occurance = " + i);
                break;
            }
        }
    }

    // Approach_1 - XOR
    /*
     * - The basic idea is that if one initialises a variable as 0 and applies the XOR operator 
     *   to the variable with each element of an array of numbers, at the end the variable will 
     *   store the number which has been repeated odd number of times.
     * - This works because XOR is commutative, meaning we can change the order in which we 
     *   apply XOR. (a ^ a) == 0, and (by extension) that (a ^ b ^ a) == b.
     * - Therefore, any value that occurs an even number of times will "cancel" out to zero in 
     *   the XOR "accumulation", leaving just the odd one out.
     */
    static void approach_2(int arr[]) {
        int n=arr.length;
        int odd = 0;
        for(int i = 0; i < n; i++) {
            odd = odd ^ arr[i];
        }
        System.out.println("Elements is : " + odd);
    }

    public static void main(String[] args) 
    {
        int arr[] = {2, 1, 3, 5, 6, 1, 3, 5, 2};
        oddNumberOccurrance(arr);
        approach_2(arr);
    }
}
