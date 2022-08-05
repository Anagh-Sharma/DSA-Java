package DSA_Recursion;

public class P75_ReverseStringRecursion {
    /*
     * Recursive algorithm to reverse an string
     * - Strings in Java are immutable, therefore the string object is cast as a character array.
     * - Swap characters at the left most position and right most position.
     * - Increment left pointer and decrement the right pointer.
     * - Recursively call the method to with the changed pointers.
     */
    private static void swap(char[] c, int i, int j)
    {
        char temp = c[i];
        c[i] = c[j];
        c[j] = temp;
    }
     public static void stringReverseRecursion(char[] c, int l, int h)
    {
        if (l<h)
        {
            swap(c, l, h);
            stringReverseRecursion(c, l + 1, h - 1);
        }
    }
    public static void main(String[] args)
    {
        String str = "Recursion Assignment!";
        char[] c = str.toCharArray();
        stringReverseRecursion(c, 0, c.length - 1);
        str = new String(c);// The new keyword creates a new object in the String pool
        System.out.println(str);
    }
}
