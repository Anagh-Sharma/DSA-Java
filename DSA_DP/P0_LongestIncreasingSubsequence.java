package DSA_DP;
/*
 * A recursive approach for finding all increasing subsequences:
 * 
 * Base case:
 * - The end of the string has been reached.
 * - Return an ArrayList of path's length or paths themselves.
 * 
 * Recursive case:
 * Arguments passed:
 * - Incremented index of the string
 * - The path
 * - The string
 *   [If the current index element is larger that previous, then
 *   it is concatenated to the path. Else pass the pass unaltered
 *   to the next recursive call.]
 * 
 * - Iterate through all the indices of the string following the recursive' call's index:
 *   - If an element is larger:
 *     Append it to the path, increment the index and make a recursive call
 *   - Else:
 *     Make a recursive call with incremented the index but without appending the element 
 *     to the path.
 * - Return the combined ArrayLists returned to this recursive call by it recursive
 *   calls in the itearation above. The bottom-most recursive call on the stack will
 *   recieve the final ArrayList with all increasing subsequences or their lengths and
 *   the longest can be searched from these. 
 */
/*
 * 
 */
public class P0_LongestIncreasingSubsequence {
    public static void main(String[] args) {
        // int[] seq = {3, 1, 8, 2, 5};
        
    }
}
