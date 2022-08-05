package Array_Problems;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class P47_2_ReverseStringWordWise {
   /*
    * - Word length variable: to calculate the length of a word
    * - Iterate through the string, increment the length variable, and if when space character is 
    *   found:
    * - The start index of word substring is calculated as: iterator- (length-1)
    * - The end index of word substring is calculated as: iterator [For last word as: iterator+1 as 
    *   it terminates not by space character but when the string reaches its end.]
    * - Use a method to reverse a word sub-string by casting the substring as a String Builder object 
    *   and using the reverse function.
    */
    static void reverseString(String str, int i, int j)
    {
        StringBuilder subString = new StringBuilder(str.substring(i, j));
        System.out.print(subString.reverse()+ " ");
    }
    static void reverseStringWordWise(String str)
    {
        int length = 0;
        for(int i=0; i<str.length(); i++)
        {
            length++;
            if(str.charAt(i) == ' ' || i==str.length()-1)
            {
                if(i==str.length()-1)
                    reverseString(str, i-(length-1), i+1);
                else
                    reverseString(str, i-(length-1), i);
                length = 0;
            }
        }
    }

    static String reverse(String wordStr) {
        int i = 0;
        int j = wordStr.length() - 1;
        char wordArr[] = wordStr.toCharArray();
        char temp;
        while(i < j) {
            temp = wordArr[i];
            wordArr[i] = wordArr[j];
            wordArr[j] = temp;
            i++;
            j--;
        }
        return String.valueOf(wordArr);
    }

    static String approach_1(String str) {
        String rev = reverse(str);
        // System.out.println(rev);    // whole string reverse
        String words[] = rev.split(" ");
        String sentence = "";
        for(String word : words) {
            if(word.equals("")) {
                continue;
            }
            sentence += reverse(word) + " ";
        }
        sentence = sentence.trim();
        // System.out.println(sentence);
        return sentence;
    }

    static String approach_2(String str) {
        String wordArr[] = str.split(" ");
        String sentence = "";
        int n = wordArr.length;
        for(int i = n-1; i >= 0; i--) {
            if(wordArr[i].equals("")) {
                continue;
            }
            sentence += wordArr[i] + " ";
        }
        // System.out.println(sentence);
        return sentence;
    }

    static String approach_3(String str) {
        List<String> wordsList = Arrays.asList(str.split(" "));
        Collections.reverse(wordsList);
        return String.join(" ", wordsList);
    }

    public static void main(String[] args) {
        String str = "hello how are you";
        reverseStringWordWise(str);
    }
}
