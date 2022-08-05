package Array_Problems;

import java.util.Arrays;

public class P57_LongestSubstringNonRepeating {
    static void longestSubstring(String str, int n)
    {
        /*res: records length of the longest substring visited. */
        int res = 0;
        /*
         * visited: Hash map to store if a character has occurred before
         * - Initialized to store zero values, indicating that a character has not been read
         * - Store 1 at a character's index once it has been read
         */
        int[] visited = new int[256];
        /*
         * Iterate over all characters of the string as first characters of substrings 
         * and in the nested loop iterate to all the successive characters as last
         * characters. This builds all the substrings.
         */
        for(int i=0; i<n; i++)
        {
            /*Fill the array with zeroes for an iteration of first characters.
             * The hash map is common for all last characters as all preceding
             * substrings are a substring of all the succeeding substrings.
             */
            Arrays.fill(visited, 0);
            for(int j=i; j<n; j++)
            {
                /*
                 * If the current character was visited:
                 * break from the inner loop as the current 
                 * first character cannot be a first character.
                 */
                if (visited[str.charAt(j)] == 1)
                    break;
                /*
                 * Else:
                 * Set result as the maximum between:
                 * The length of the last longest substring
                 * The length of the current substring
                 */
                else
                {
                    res = Math.max(res, j-i+1);
                    visited[str.charAt(j)] = 1;
                }
            }
        }
        System.out.println(res);
    }
    public static void main(String[] args) {
        String str = "abcabcbb";
        int n = str.length();
		longestSubstring(str, n);
    }
}