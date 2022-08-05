package Array_Problems;

import java.util.Arrays;

public class P50_StringAnagram {
    static final int CHAR = 256;

    static boolean isAnagramSort(String string1, String string2) 
    {
        if (string1.length() != string2.length()) {
            return false;
        }
        char[] a1 = string1.toCharArray();
        char[] a2 = string2.toCharArray();
        Arrays.sort(a1);
        Arrays.sort(a2);
        return Arrays.equals(a1, a2);
    }

    static boolean isAnagramHashmap(String s1, String s2) 
    {
        if(s1.length() != s2.length()) {
            return false;
        }
        int count[] = new int[CHAR];
        for(int i = 0; i < s1.length(); i++) {
            count[s1.charAt(i)]++;
            count[s2.charAt(i)]--;
        }
        for(int i = 0; i < CHAR; i++) {
            if(count[i] != 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s1 = "silent";
        String s2 = "listen";
        System.out.println(isAnagramSort(s1, s2));
        System.out.println(isAnagramHashmap(s1, s2));
    }
}
