package Array_Problems;

import java.util.ArrayList;
import java.util.Arrays;

public class P54_PatternMatch 
{
    static void patternMatch_Window_Sliding(String s1, String pattern) 
    {
        /*
         * - The i iterator defines the first element of the window.
         * - It iterates through every possible first element of the
         *   string.
         * - The last possible element of the string that can be the 
         *   first element of the substring is at:
         *   string.length - substring.length
         */
        for(int i = 0; i < s1.length() - pattern.length(); i++) 
        {
            /*
             * j count helps identify if the entire pattern was 
             * traversed and no unequal elements were found.
             */
            int j;
            /*
             * Iterate throught the pattern to validate
             * if each element from the current first element
             * of the string is equal to the elements of pattern.
             * 
             * Break from the loop when they are not equal and 
             * check the nest possible first element.
             */
            for(j = 0; j < pattern.length(); j++) 
            {
                if(pattern.charAt(j) != s1.charAt(i+j)) 
                {
                    break;
                }
            }
            /*
             * When the number of same elements and the
             * length of the patter are same, then the
             * entire pattern and the string's substring
             * are equal wrt the current first element.
             */
            if(j == pattern.length()) 
            {
                System.out.println("Pattern Found at : " + i);
            }
        }
        System.out.println("No Pattern Found...");
    }   
    
    static void patternMatch_Rabin_Karp(String str, String pattern)
    {
        /*
         * 1. Compare each possible substring's hash
         *    with the pattern's hash.
         * 2. Iterate over all elements of the given
         *    string that are possible first elements
         *    of substrings with length same as that of
         *    the pattern.
         * 3. Build hashes of each substring using 
         *    rolling hash.
         * 4. If hashes are same then do character-wise
         *    comparision
         * 5. Store the first elements of substrings
         *    that match with given pattern.
         */
        /*
         * List to record all the first elements of substrings
         * that match with given pattern.
         */
        ArrayList<Integer> list = new ArrayList<>();
        int N = str.length();
        /*
         * M    :   Length of the pattern, used to calculate hash values
         * base :   Base is used to calculate order sensitive hash values
         *          by raising it to the character's value and then multiplying
         *          this to the character's integer representation such as ASCII.
         * mod  :   Reduce the size of the hashes as large M and base values can
         *          produce very large hashes. 
         */
        int M = pattern.length();
        int base = pattern.length();
        int mod = 13;
        /*
         * stringHash   :   Hash value of a substring of the given string of size N.
         * patternHash  :   hash value of the given pattern.
         * 
         * This program will iteratively try to match patternHash with stringHash and
         * when they match then the characters of both pattern and substring will be
         * compared to dicide if they equal.
         */
        int stringHash = 0;
        int patternHash = 0;
        /*
         * Calculating:
         * Rolling hash metric:
         * b^(M-1)
         */
        int base_M_minus_1 = 1;
        for(int i = 0; i < M-1; i++) 
        {
            base_M_minus_1 = (base_M_minus_1 * base) % mod;
        }
        System.out.println("X is : " + base_M_minus_1);

        /*
         * Calculating: Linear time: O(M)
         * - patternHash
         * - stringHash of the first possible substring of the given string.
         *   Hash of every subsequent substring will be calculated using rolling
         *   hash in constant time: O(1).
         */
        for(int i = 0; i < M; i++) 
        {
            patternHash = (patternHash * base + (pattern.charAt(i))) % mod;
            stringHash = (stringHash * base + str.charAt(i)) % mod;
        }
        System.out.println(patternHash + "," + stringHash);

        /*
         * Iterate through all elements of the given string
         * that are potential first elements of a substring
         * of size N.
         */
        for(int i = 0; i <= N - M; i++) 
        {
            /*
             * When stringHash is equal to patternHash:
             * Character-wise comparision of pattern and
             * substring will take place.
             * 
             * For the first iteration of i the stringHash
             * of the first substring will be compared.
             * 
             * In every iteration of stringHash will be
             * recomputed for the next possible substring.
             * This stringHash will be compared in the next
             * iteration.
             */
            if(patternHash == stringHash) 
            {
                int j;
                for(j = 0; j < M; j++) 
                {
                    if(pattern.charAt(j) != str.charAt(i + j)) 
                    {
                        break;
                    }
                }
                if(j == M) 
                {
                    System.out.println("Pattern Match : " + i);
                    list.add(i+1);
                }
            }

            /*
             * Recomputing stringHash: 
             * [Does not occur when i = N-M as such iteration of i 
             * cannot be the first character of a substring of size.]
             * 
             * - stringHash computed for i+1 iteration.
             * - Removing the first element of the last window.
             *   (str.charAt(i) * base_N_minus_1
             * - Multiplying base as the place values of each
             *   character int the string will increment by 1 
             *   when the first character of the substring is 
             *   removed and another character is added at the end.
             * - The next element is added with multiplying base
             *   as the place value of the first character is 0
             *   and base^0 is 1.
             * - Then finally, the modulus of this hash will be 
             *   taken.
             * - Negative values are remove by adding mod to them.
             */
            if(i < N - M) 
            {
                stringHash = stringHash - (str.charAt(i) * base_M_minus_1);
                stringHash = ((stringHash * base + str.charAt(i + M))) % mod;
                if(stringHash < 0) 
                {
                    stringHash = stringHash + mod;
                }
            }
        }
    }
    static void patternMatch_KMP(String str, String pattern)
    {
        /*
         * Building the LPS array:
         * TC : O(M), where M is the size of the pattern
         * - Initialize an array LPS of length of the pattern and 
         *   initialize values to -1.
         * - The LPS array stores the indices of consecutive repeating 
         *   elements(s) in an array from the prefix of the array.
         * - Iterate over the size of the pattern/LPS array from index 1:
         * - If the element preceding an element in LPS is -1, then:
         *   - Compare the element of pattern at the index same as the
         *     current index of LPS with the first element, or 0 index 
         *     element, of the pattern.
         *   - If they are same then store the index of the first element
         *     of pattern, i.e., 0 at the current index of LPS.
         * - Else if the element preceding an element in LPS is greater than -1, then:
         *   - x: preceding element greater than -1 in the LPS
         *   - Compare the element of pattern at the index same as the
         *     current index of LPS with the element at x+1 index of 
         *     the pattern.
         *   - If they are same then store the x+1 index of the pattern 
         *     at the current index of LPS.
         */
        int LPS[] = new int[pattern.length()];
        Arrays.fill(LPS, -1);
        /* Iterate over the size of the pattern/LPS array from index 1. */
        for(int j=1; j<pattern.length(); j++)
        {
            /*
             * If a preceding index of LPS has a value larger than -1
             * then it means there exists a subsection in the pattern 
             * equal to the prefix of the pattern before the 
             * corresponding index in the pattern.
             * So, the next character is also checked if it is repeated
             * consecutively after the last repeated character from the
             * prefix.
             */
            if(LPS[j-1]>-1)
            {
                /*
                 * - Comparing the the character in the pattern at the 
                 *   index whose corresponding index in the LPS has a
                 *   term greater than -1 just preceding it with the
                 *   element at the index of the pattern just after
                 *   the index stored in the preceding non -1 index of
                 *   LPS, i.e., the next element after the last repeating
                 *   element.
                 * - If they are same, then current index j of LPS stores
                 *   the index of the repeating term of the prefix which is
                 *   just the value stored in the preceding index of the LPS
                 *   +1 as the characters occur consecutively.
                 */
                if(pattern.charAt(j) == pattern.charAt(LPS[j-1]+1))
                {
                    LPS[j] = LPS[j-1]+1;
                }
            }
            /*
             * If the element at index of LPS preceding the current
             * index is not greater than -1, then it means there was no
             * repeating element in the index preceding this index in the
             * corresponding index of the pattern.
             */
            else if(pattern.charAt(j) == pattern.charAt(0))
            {
                LPS[j] = 0;
            }
        }
        System.out.println("LPS of the Pattern: "+Arrays.toString(LPS));

        /*
         * KMP algorithm:
         * Variables:
         * - Two pointers:
         *  - Pointer i: Iterate over the characters of the string, from 0 to size-1.
         *  - Pointer j: Keep track of the position in LPS array, initialized to 0.
         * - LPSBackTrack: Store integer index to back-track in the pattern.
         * 
         * Procedure:
         * - Loop: Iterating over the characters of the string using Pointer 1:
         * - If characters at i and pattern value corresponding to j are equal:
         *   - If j is pointing at the end of pattern string, then:
         *      - Pattern found, break!
         *   - Else:
         *      - Increment both i and j.
         *      - Increment Length.
         * - Else if j = 0, i.e., j points to the first index of pattern therefore,
         *   no backtracking is possible as the LPS keeps track of backtracking:
         *   - Increment i to check the next character of the string as a possible
         *     first character.
         * - Else store LPS value at j-1 in LPSBackTrack.
         *   [This indicates that a prefix of the pattern already exists just before
         *    the current unequal string character.]
         *   - If the LPS value is -1 then, set j to 0.
         *   - Else set j to LPSBackTrack+1
         *     [The next character to be compared is after the current known existing
         *      prefix.]
         */
        int i = 0, j=0;
        int LPSBackTrack = 0;
        String totalPrefixBacktracking = "";
        while(i<str.length()&&j<pattern.length())
        {       
            if(str.charAt(i) == pattern.charAt(j))
            {
                totalPrefixBacktracking += str.charAt(i);
                if(j==pattern.length()-1)
                {
                    System.out.println("Found! Last index: " + i);
                    break;
                }
                else
                {
                    i++;
                    j++;
                    continue;
                }
            }
            else if(j==0)
            {
                i++;
                continue;
            }
            else
            {
                LPSBackTrack = LPS[j-1];
                if(LPSBackTrack == -1)
                {
                    j = 0;
                    continue;
                }
                else
                {
                    j = LPSBackTrack+1;
                    continue;
                }
            }
        }
        System.out.println("Total backtracking: "+totalPrefixBacktracking);
    }

    public static void main(String[] args) 
    {
        // String s1 = "ABABABCD";
        // String pattern = "ABAB";
        // String s2 = "batmanandrobinarebat";
        // String pattern2 = "bat";
        // patternMatch_Window_Sliding(s1, pattern);
        // patternMatch_Rabin_Karp(s2, pattern2);
        String s3 = "ababcabcabababd";
        String pattern3 = "ababd";
        patternMatch_KMP(s3, pattern3);
    }
}