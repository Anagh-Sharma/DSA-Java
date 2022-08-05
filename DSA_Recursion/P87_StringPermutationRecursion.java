package DSA_Recursion;

import java.util.ArrayList;

public class P87_StringPermutationRecursion 
{
    /*
     * Permutations of a string:
     * Every recursive call will insert a character of the string
     * at different indices of the computed subsequences by casting each
     * subsequence as a StringBuffer object.
     */
    static ArrayList<String> permutations(String str) 
    {
        /*
         * Base case:
         * - When the entire string has been processed, an empty
         *   string is returned and no other computations take
         *   place.
         */        
        if(str.length() == 0) 
        {
            ArrayList<String> blank = new ArrayList<>();
            blank.add("");
            return blank;
        }
        /*currentChar: The first character of the passed string. */
        char currentChar = str.charAt(0);   // a
        /*remainingString: The substring with the first stored character of the string, removed. */
        String remainingString = str.substring(1);   // bc
        /*
         * Recursive case:
         * - All method calls on the stack hold the first
         *   elements of the iteratively smaller substrings
         *   from left to right.
         * - The last call gets an empty string as its returned
         *   value from the base case.
         * - The remaining calls get set of strings from the return
         *   statement at the end of the code.
         */
        ArrayList<String> temp = permutations(remainingString);
        /*
         * The strings added to the result will be:
         * - The current character inserted at different locations
         *   of each sub-sequence returned by the last recursive call.
         */
        ArrayList<String> result = new ArrayList<>();
        for(String s : temp) 
        {
            for(int i = 0; i <= s.length(); i++) 
            {
                StringBuffer sb = new StringBuffer(s);
                sb.insert(i, currentChar);
                result.add(sb.toString());
            }
        }
        return result;
    }

    public static void main(String[] args) 
    {
        String str = "abc";
        System.out.println(permutations(str));
    }
}
