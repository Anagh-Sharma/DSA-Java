package DSA_Recursion;

public class P80_PatternTraingle1Recursion {
    /*
     * The number of stars printed depends on the number of the row.
     * 
     * Base case:
     * The maximum number of rows has been reached: return from the method call.
     * Computation: Print the number of stars according to the row number.
     * Recursive case:
     * Recursive call to the function by incrementing the row number.
     */
    static void patternTriangle1(int row, int rows)
    {
        if(row == rows)
            return;
        for(int i=0; i<=row; i++)
            System.out.print("*");
        System.out.println();

        patternTriangle1(row+1, rows);
    }
    /*
     * The number of stars printed depends on the number of the row.
     * 
     * Base case:
     * The maximum number of rows has been reached: return from the method call.
     * Computation: 
     * Print the number of spaces: total rows - (row + 1).
     * [If the row numbering starts from 0.]
     * Print the number of stars according to the row number.
     * Recursive case:
     * Recursive call to the function by incrementing the row number.
     */
    static void patternTriangle2(int row, int rows)
    {
        if(row == rows)
            return;
        for(int i=0; i<=rows-(row+1); i++)
            System.out.print(" ");
        for(int j=0; j<=row; j++)
            System.out.print("*");
        System.out.println();

        patternTriangle2(row+1, rows);
    }
    public static void main(String[] args) {
        int rows = 5;
        int row = 0;
        patternTriangle1(row, rows);
        patternTriangle2(row, rows);
    }
}
