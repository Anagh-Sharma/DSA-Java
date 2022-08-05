package DSA_Recursion;

public class P84_PatternAlphabet1Recursion {
   /*
     * The alphabet printed depends on the number of the row.
     * 
     * Base case:
     * The maximum number of rows has been reached: return from the method call.
     * Computation: Print the alphabets according to the row number and the start value.
     * Recursive case:
     * Recursive call to the function by incrementing the row number and start value.
     */
    static void pattern(int from, int row, int rows)
    {
        if (row == rows)
            return;
        for(int i=0; i<=row; i++)
        {
            System.out.print((char)from + "  ");
        }
        System.out.println();
        pattern(from+1, row+1, rows);
    }    
    public static void main (String[] args)
    {
        int rows = 5;
        int row = 0;
        int from = 65;
        pattern(from, row, rows);
    }
}
