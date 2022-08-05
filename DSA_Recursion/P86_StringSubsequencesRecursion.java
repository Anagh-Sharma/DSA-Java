package DSA_Recursion;

import java.util.ArrayList;
/*
 * Construct power set of a string using recursion
 * [When the power set needs to be stored in some local object.]
 * - Base case:
 *   When the string index reaches the size of the string.
 * 
 * - Computation:
 * - pass the the string, string index, and ArrayList with all the subsequences.
 * - Add the element at the current string index to the ArrayList
 * - Iterate over all the strings in the ArrayList(except for the last one added):
 *   Concatenate the element at the present index to the string of an iteration
 *   Add this resultant string to the ArrayList.
 * 
 * - Recursive case:
 *   Recursive call with parameters: string, string index+1, ArrayList with all the subsequences.
 */

/*
 * Tree recursion:
 * - Include and exclude the element at every subsequent index of the string in the sequence 
 *   until the end of the string is reached, producing a subsequence. This way the entire power 
 *   set is produced.
 * - Start with the string, an empty subsequence, and the pointer at the beginning of the string.
 * - Then make two recursive calls: 
 * 
 * - First, with subsequence without the element at the current index, added to it.
 * - Second, with subsequence with the element at the current index, added to it.
 *   In both the recursive method calls, the index is incremented. 
 *   For both the method calls, when the incremented index reaches the size of the string 
 *   (or moves beyond the indices of the string) the subsequence is printed.
 * 
 * - Base case:
 * - When the index has reached the end of the string: 
 * - Print the subsequence.
 * - Return: Exits this branch of the tree and moves to the next branch.
 * 
 * - Recursive case:
 * - When the index has not reached the end the string:
 * - One call to exclude the next element in the sequence and one to include.
 * 
 * - Thus, when at an index the next index is both selected and discarded, producing two branches.
 */
public class P86_StringSubsequencesRecursion {
    static void stringSubsequenceRecursion(String subsequence, String str)
    {
        if(str.length() == 0)
        {
            System.out.print(subsequence+", ");
            return;
        }
        stringSubsequenceRecursion(subsequence, str.substring(1));
        stringSubsequenceRecursion(subsequence += str.charAt(0), str.substring(1));
    }
    /*More understandable since index is separately passed. */
    static void stringSubsequenceRecursion(int index, String subsequence, String str)
    {
        if(index == str.length())
        {
            System.out.print(subsequence+", ");
            return;
        }
        stringSubsequenceRecursion(index+1, subsequence, str);
        stringSubsequenceRecursion(index+1, subsequence += str.charAt(index), str);
    }
    /*Storing the subsequences. */
    static ArrayList<String> subSeq(String str) 
    {
        /*
         * Base case:
         * - When the entire string has been processed, an empty
         *   string is returned and no other computations take
         *   place.
         */
        if(str.length() == 0) 
        {
            ArrayList<String> emptyString = new ArrayList<>();
            emptyString.add("");
            return emptyString;
        }
        /*The first character of the passed string */
        char currentChar = str.charAt(0);
        /*The substring with the first stored character of the string, removed. */
        String remainingString = str.substring(1);
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
        ArrayList<String> temp = subSeq(remainingString);
        /*
         * The strings added to the result will be:
         * - The string already in temp (Excluding current character)
         * - String obtained as: string of temp + current character (Including current character)
         */
        ArrayList<String> result = new ArrayList<>();
        for(String s : temp) 
        {
            result.add(s);
            result.add(currentChar + s);
        }
        return result;
    }
    public static void main(String[] args) {
        String str = "abc";
        String subsequence = "";
        int index = 0;
        stringSubsequenceRecursion(index, subsequence, str);
        System.out.println(subSeq(str));
        
    }
}
