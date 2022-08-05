package Array_Problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class P52_StringArrayAllAnagrams {
    static void printAnagrams_Consecutive(String wordArr[]) 
    {
        String[] words = wordArr.clone();
        int index = 0;
        for(String word : words) {
            char arr[] = word.toCharArray();
            Arrays.sort(arr);
            words[index] = String.valueOf(arr);
            index++;
        }
        for(int i = 0; i < words.length; i++) {
            if(i < words.length-1 && words[i].equals(words[i+1])) {
                System.out.println(wordArr[i] + wordArr[i+1]);
            }
        }
    }   
    
    // Using Hashmap
    static void printAnagrams_Hashmap(String arr[]) 
    {
        /*Make a HashMap object with String as keys and List of Strings as values.*/
        HashMap<String, List<String>> map = new HashMap<>();
        /*Iterate through the strings of the array of strings. */
        for(int i = 0; i < arr.length; i++) 
        {
            String word = arr[i];
            /*Converting the string to character array. */
            char []letters = word.toCharArray();
            /*Sorting the character array. */
            Arrays.sort(letters);
            /*Store the sorted character array as a string */
            String newWord = new String(letters);
            /*
             * Use the sorted string as key
             * and the original string as
             * value.
             */

            /*
             * If the sorted string is already
             * present as a key, then add the 
             * current original sting as a value.
             */
            if(map.containsKey(newWord)) 
            {
                map.get(newWord).add(word);
            }
            /*
             * Else 
             * - construct a List of Strings,
             * - add the current original string,
             * - add the sorted string as key
             *   and the List as value to the map.
             */
            else 
            {
                List<String> words = new ArrayList<>();
                words.add(word);
                map.put(newWord, words);
            }
        }
        
        /*
         * - Iterate through the keys of the map.
         * - Retrive the value List associated with the key in the map.
         * - Print the List if its size is greater than 1.
         */
        for(String s : map.keySet()) 
        {
            List<String> values = map.get(s);
            if(values.size() > 1) 
            {
                System.out.println(values);
            }

        }
    }

    public static void main(String[] args)
    {
        String arr[] = { "cat", "dog", "tac", "god", "act" };
        // printAnagrams_Consecutive(arr);
        printAnagrams_Hashmap(arr);
    }
}
