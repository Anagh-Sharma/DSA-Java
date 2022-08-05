package Array_Problems;

public class P43_StringPallindrome {
    // TC : O(n), SC : O(n)
    static boolean isPalindrome(String str) {
        StringBuilder sb = new StringBuilder(str);
        sb.reverse();
        return str.equals(sb.toString());
    }

    static boolean isPalindrome_2(String str) {
        int begin = 0;
        int end = str.length() - 1;
        while(begin < end) {
            if(str.charAt(begin) != str.charAt(end)){
                return false;
            }
            begin++;
            end--;
        }
        return true;
    }

    public static void main(String[] args) {
        String str = "ababa";
        System.out.println(isPalindrome_2(str));
    }
}
