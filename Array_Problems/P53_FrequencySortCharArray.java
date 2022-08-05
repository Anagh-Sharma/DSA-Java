package Array_Problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class P53_FrequencySortCharArray {

    // TC: O(N)
   /*
    * - Converting String to integer array
    * - Hash map from 0 to 255 to store ASCII value frequency.
    * - Find max in the hash array, store the index in array, replace value by zero.
    * - Print the array as characters.
    */
    static void freqSortString(String str)
    {
        // 1. Converting String to integer array
        int[] strASCII = new int[str.length()];
        for(int i=0; i<str.length(); i++)
        {
            strASCII[i] = str.charAt(i);
        }
        
        // 2. Hash to store count of each element
        int hash[] = new int[256];
        for(int j=0; j<strASCII.length; j++)
        {
            hash[strASCII[j]]++;
        }          

        // 3. Find max, store the index in array, replace value by zero.
        /*
         * - Create a new array to store the result
         * - Iterate over the size of the input array
         * - Find the maximum element in the hash map
         *   and store its index. The index denotes the
         *   character of the string with highest count.
         * - Store the maximum element in the result array
         *   as many times as its frquency in the original array.
         *   This is done by iterating over the maximum element's
         *   value, storing the maximum element and decrementing
         *   the hash value. So, the element's hash value becomes
         *   and the next max element can be found in the next iteration.
         */
        int max = 0;
        int lastMaxIndex = 0;
        int[] strASCII_Sort = new int[str.length()];
        int strCountIndex = 0;

        for(int k=0; k<str.length(); k++)
        {
            max = hash[0];
            for(int l=0; l<hash.length; l++)
            {
                if(hash[l]>max)
                {
                    max = hash[l];
                    lastMaxIndex = l;
                }
            }
            
            while(hash[lastMaxIndex]>0)
            {
                strASCII_Sort[strCountIndex] = lastMaxIndex;
                strCountIndex++;
                hash[lastMaxIndex]--;
            }

        }

        // 4. Print the result array as characters.
        for(int j=0; j<strASCII_Sort.length; j++)
        {
            System.out.print((char)strASCII_Sort[j]);
        }
    }

    static void freqSortString_HashMap(String str)
    {
        /*
         * First_Index: Hash map to store character as key a 
         * first occurrence index of each character as value. 
         */
        HashMap<Character, Integer> firstIndex = new HashMap<>();
        for(int i=0; i<str.length(); i++)
        {
            if(!firstIndex.containsKey(str.charAt(i)))
            {
                firstIndex.put(str.charAt(i), i);
            }
        }
        System.out.println(Arrays.asList(firstIndex));

        /*
         * charFreq: Hash map to store character as key a 
         * frequency as value.
         */
        HashMap<Character, Integer> charFreq = new HashMap<>();
        for(int i=0; i<str.length(); i++)
        {
            int count = charFreq.containsKey(str.charAt(i)) ? charFreq.get(str.charAt(i)) : 0;
            charFreq.put(str.charAt(i), count + 1);
        }
        System.out.println(Arrays.asList(charFreq));

        /*
         * Global_Freq: Hash map to store character frequency 
         * as keys and character List as values.
         */
        HashMap<Integer, List<Character>> freqCharRecord = new HashMap<>();
        for(Character key: charFreq.keySet())
        {
            int count = charFreq.get(key);

            if(freqCharRecord.containsKey(count)) 
            {
                freqCharRecord.get(count).add(key);
            }
            else 
            {
                List<Character> characters = new ArrayList<>();
                characters.add(key);
                freqCharRecord.put(count, characters);
            }
        }
        /*Retrive keys from freqCharRecord as array and sort the array 
         * The keys denote the length of the character subarray
         * A length can be associated with multiple characters.
         * The subarrays with larger lengths will be printed first 
         * and then smaller.
        */
        System.out.println(Arrays.asList(freqCharRecord));
        Integer lengths[] = freqCharRecord.keySet().toArray(new Integer[0]);
        System.out.println(Arrays.toString(lengths));
        Arrays.sort(lengths);
        System.out.println(Arrays.toString(lengths));
        
        StringBuilder res = new StringBuilder(str.length());

        /*Iterating over the frequency count of the characters. */
        for(int i=lengths.length-1; i>=0; i--)
        {
            /*Retrieve all characters of a count */
            Character characters[] = freqCharRecord.get(lengths[i]).toArray(new Character[0]);
            System.out.println(Arrays.toString(characters));
            for(int j=0; j<characters.length; j++)
            {
                /*Find character with lowest first index using
                 * firstIndex array to facilitate in-order
                 * sorting.
                 */
                char minIndexChar = characters[0];
                for(int l=0; l<characters.length; l++)
                {
                    if(firstIndex.get(characters[l])<firstIndex.get(minIndexChar))
                    {
                        minIndexChar = characters[l];
                    }
                }
                char letter = minIndexChar;
                /*Record the character with lowest index
                 * as many times as the length of the character
                 * subarray.
                 */
                for(int k=0; k<lengths[i]; k++)
                {
                    res.append(letter);
                }
                /*
                 * Set the first index of the current character
                 * to a high value so that he next character with
                 * minimum index is picked.
                 */
                firstIndex.put(minIndexChar, str.length()+1);
            }
        }
        System.out.println(res.toString());
    }
    
    public static void main(String[] args) {
        String str = "Aatreeeccbb";        
        freqSortString_HashMap(str);
    }
}
