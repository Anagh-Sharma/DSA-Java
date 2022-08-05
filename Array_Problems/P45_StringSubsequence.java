package Array_Problems;

import java.util.ArrayList;
/*
 * Power set:
 * Print all sub-sequences of a string which are strings built using all combinations 
 * of the source string’s characters, in the order of their occurrence in the string but
 * not necessarily contiguous.
 * 
 * New sub-sequences are built by appending each character of the string to already 
 * built sub-sequences iteratively.
 */
public class P45_StringSubsequence 
{
    static void stringSubsequence(String str) {
        ArrayList<String> result  = new ArrayList<>();
        // Two nested for loops:
        // Outer For loop: Iterate through each character of the string.
        for(int i = 0; i < str.length(); i++) 
        {
            // firstChar: Store the character of the present iteration. 
            char firstChar = str.charAt(i);
            /*
             * In the first iteration append an empty string and the first character to 
             * result using condition checking if result is empty, then move to the next 
             * iteration.
             */
            if(result.size() == 0) 
            {
                /*
                 * First add "" empty string.
                 */
                result.add("");
                /*
                 * firstChar is of character type
                 * and result is of String type.
                 * Concatenating firstChar to
                 * "" empty String converts firstChar
                 * to String before adding it to subSeq. 
                 */
                result.add("" + firstChar);
                continue;
            }
            /*
             * Store the size of the result in result-size for the current iteration.
             */
            int resultSize = result.size();
            /*
             * Inner for loop: Iterate through each string in result from index 0 to result-size.
             * 
             * New sub-sequences are built by appending each character of the string to already 
             * built sub-sequences iteratively.
             */
            for(int j = 0; j < resultSize; j++) 
            {
                /*
                 * Append the character of the current iteration of outer loop if it is 
                 * not already present to result.
                 */
                if(!result.contains("" + firstChar)) 
                {
                    result.add("" + firstChar);
                }
                /*
                 * Store string temp: string of result of the present iteration of inner loop 
                 * + firstChar
                 */
                String temp = result.get(j) + firstChar;
                /*
                 * Append temp if it is not already present to result.
                 */
                if(!result.contains(temp)) 
                {
                    result.add(temp);
                }
            }
        }
        System.out.println(result);
    }

    public static void main(String[] args) {
        String str = "abc";
        stringSubsequence(str);
    }
}
