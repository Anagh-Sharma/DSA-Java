package Array_Problems;

public class P46_CheckSubstringPresence {
    static void substringCheck(String str1, String str2)
    {
        int l1 = str1.length();
        int l2 = str2.length();
        int i=0, j=0;
        while (i<l2 && j<l1) {
            if(str1.charAt(i) == str2.charAt(j))
                j++;
            i++;
        }

        if(i == l2)
            System.out.println(true);
        else
            System.out.println(false);
    }

    public static void main(String[] args) {
        String str1 = "abcd";
        String str2 = "ab";
        substringCheck(str1, str2);
    }
}
