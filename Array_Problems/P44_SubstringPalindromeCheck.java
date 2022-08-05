package Array_Problems;

public class P44_SubstringPalindromeCheck 
{
    static boolean isPalindrome(String str) 
    {
        int i = 0;
        int j = str.length() - 1;
        while(i <= j) {
            char c1 = str.charAt(i);
            char c2 = str.charAt(j);
            if(c1 != c2) {
                return false;
            }
            else {
                i++;
                j--;
            }
        }
        return true;
    }

    static void solution(String str) 
    {
        for(int i = 0; i < str.length(); i++) {
            for(int j = i + 1; j <= str.length(); j++) {
                String ss = str.substring(i, j);
                if(isPalindrome(ss)) {
                    System.out.println(ss);
                }
            }
        }
    }
    /*
     * - Iterate through each index of the string.
     * - All substrings associated with an element at an index can be checked by 
     *   moving equal spaces left and right of the index incrementally until the either 
     *   end of the string is reached.
     * 
     * - Each index can have to different sets of substrings associated with it:
     * - First: Odd length substring: Treating the index i, as the middle element and 
     *   checking its first associated substring by using i-1 and i+1 as the start and 
     *   end indices.
     * - Second: Even length substring: Treating the index i, as the left element and 
     *   checking its first associated substring by using i and i+1 as the start and end indices.
     * - Pass the string and the start-end pairs of indices for both odd and even length substrings.
     * - Compare characters on the left and right ends. 
     * - If both of them are equal, print or record the palindrome substring. 
     *   Expand one character to the left and right until left is greater than or 
     *   equal to 0 and right is lesser than or equal to the length of the string.
     * - If a palindrome doesn’t exist for both odd and even length substring, move 
     *   to the next letter.
     */

    public static int findPalindromesInSubString(String input, int j, int k) 
    {
        int count = 0;
        for (; j >= 0 && k < input.length(); --j, ++k) {
          if (input.charAt(j) != input.charAt(k)) {      
            break;
          } 
          System.out.println(input.substring(j, k+1));
          count++;
        }
        return count;
    }
    
    public static int findAllPalindromeSubstrings(String input) 
    {
        int count = 0;
        for(int i = 0 ; i < input.length() ; ++i) {
          count+= findPalindromesInSubString(input, i-1, i+1);
          count+= findPalindromesInSubString(input, i, i+1);
        }
    
        return count;
    }

    public static void main(String[] args) {
        String str = "abccbc";
        solution(str);
        String str1 = "aabbbaa";
        int count = findAllPalindromeSubstrings(str1);
        System.out.println("Total palindrome substrings: " + count);
    }
}
