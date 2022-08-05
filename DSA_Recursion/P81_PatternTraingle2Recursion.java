package DSA_Recursion;

public class P81_PatternTraingle2Recursion {
    /*
     * The number of stars printed depends on the number of the row.
     * 
     * Base case:
     * The number of rows has become 0: return from the method call.
     * Computation: 
     * Print the number of stars according to the row number.
     * Recursive case:
     * Recursive call to the function by decrementing the number of rows.
     */
    static void patternTriangle2(int rows)
    {
        if(rows == 0)
            return;
        for(int i=rows; i>0; i--)
            System.out.print("*");
        System.out.println();

        patternTriangle2(rows-1);
    }
    public static void main(String[] args) {
        int rows = 5;
        patternTriangle2(rows);
    }
}
