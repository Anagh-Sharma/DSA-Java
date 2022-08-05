package DSA_Recursion;

public class P82_PatternTraingle3Recursion {
    /*
     * The number of stars printed depends on the number of the row.
     * 
     * Base case:
     * The maximum number of rows has been reached: return from the method call.
     * Computation: Print the number of stars according to the row number.
     * Recursive case:
     * Recursive call to the function by incrementing the row number.
     */
    static void patternTriangle3(int row, int rows)
    {
        if(row == rows)
            return;
        for(int i=0; i<=rows-(row+1); i++)
            System.out.print(" ");
        for(int j=0; j<=row; j++)
            System.out.print("* ");
        System.out.println();

        patternTriangle3(row+1, rows);
    }
    public static void main(String[] args) {
        int rows = 5;
        int row = 0;
        patternTriangle3(row, rows);
    }
}
