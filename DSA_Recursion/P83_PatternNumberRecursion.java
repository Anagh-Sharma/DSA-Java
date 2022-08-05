package DSA_Recursion;

public class P83_PatternNumberRecursion {
    /*
     * The numbers printed depends upon a number parameter and row index parameter passed.
     * 
     * Base case:
     * The maximum number of rows has been reached: return from the method call.
     * Computation: The numbers printed depends upon a start value which is incremented 
     * iteratively and row index parameter passed.
     * Recursive case:
     * Recursive call to the function by incrementing the row number and start value.
     */
    static void pattern(int from, int row, int rows)
    {
        if (row == rows)
            return;
        for(int i=0; i<=row; i++)
        {
            System.out.print((from++)+ "  ");
        }
        System.out.println();
        pattern(from, row+1, rows);
    }    
    public static void main (String[] args)
    {
        int rows = 5;
        int row = 0;
        int from = 1;
        pattern(from, row, rows);
    }
}
