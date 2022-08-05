package Array_Problems;
/*
 * - A string of N elements will have N! permutations.
 * - All permutations can be divided into N!/N unique order
 *   in which all permutations follow a certain pattern.
 * - Each permutation of a certain order will be built by picking 
 *   itertively each element of the string as a starting element 
 *   and then printing the remaining elements in the order's pattern.
 * - Example: A string of 3 elements will have N! = 6 permutations and
 *   N!/N = 2 orders.
 * 
 * - The pattern of each permutation and thus its order 
 *   will be determined as follows:
 * 
 * - Iterate over the Total permutations N! from 0 to N! - 1.
 * - Store iteration in temp variable.
 * - Print one element at an index of the given string as a starting element.
 * - Delete it from a copy of the given string. [Thus, the string reduces by 1.]
 * - Repeat for N-2 times:
 * - Print an element at an index based upon N! iteration and indices of previous picked elements.
 *   [Here, iteration is one of N! iterations indexed from 0 to N! - 1.]
 * - Delete it from the copy of the given string. [Thus, the string reduces by 1.]
 * 
 * - Example: A string of 3 elements will have N! = 6 permutations and
 *   N!/N = 2 orders. The first order will pick its second element at
 *   and the second order at 1.
 */
public class P49_StringPermutations {
    static int factorial(int n) {
        int val = 1;
        for(int i = 2; i <= n; i++){
            val *= i;
        }
        return val;
    }    
    static void solution(String str) {
        // n: Length of the string
        int n = str.length();
        // f: Factorial of the length of the string
        int f = factorial(n);
        /*
         * Loop 1: from 0 to factorial-1
         * Iterate through the factorial count
         */
        for(int i = 0; i < f; i++)
        {
            // StringBuilder object: Mutable version of given string
            StringBuilder sb = new StringBuilder(str);
            // temp: Reuse Loop 1 iterator
            int temp = i;
            /*
             * Loop 2: from n to 1, over the length of the string, backwards.
             * Iterate through the string index values.
             * Each iteration corresponds to printing a character of a permutation.
             */
            for(int j = n; j >= 1; j--) // 3 | 2 | 1
            {
                /*
                 * - r: temp % String index
                 * 
                 * - At first iteration while calculating the index of
                 *   the first element, values from 0 to N-1 are 
                 *   calculated and then deleted from a copy of the string.
                 * 
                 * - At the remaining iterations an element will
                 *   picked based upon the permutation iteration index
                 *   value and then deleted from the copy of the string.
                 */
                // abcd b acd, bc ad, bcda NIL
                int r = temp % j; // 3%3=0 | 1%2=1 | 0%1=0
                                  // 17%4=1 | 4%3=1 | 1%2=1 | 1%1=0
                System.out.print(sb.charAt(r));
                sb.deleteCharAt(r);
                /*
                 * - q: temp / String index
                 * - Alters the index of the element of
                 *   the next iteration by altering the temp
                 *   value.
                 */
                int q = temp / j; // 3/3=1  | 1/2=0 | 0/1=0
                                  // 17/4=4 | 4/3=1 | 1/1=1 | 0/1=0
                // Alter temp
                temp = q; // 1 | 0 | 0
                          // 4 | 1 | 1 | 0
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        String str = "abc";
        solution(str);
    }
}
