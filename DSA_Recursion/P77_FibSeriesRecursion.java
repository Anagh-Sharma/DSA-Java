package DSA_Recursion;

public class P77_FibSeriesRecursion {
    /*
     * Tree recursion:
     * - Computes the number at a specific index of the Fibonacci series.
     * - Creates the Fibonacci series for every paramater passed as an index in the series.
     * - Every recursive call recursively calls the method on its two preceding numbers and for 
     *   each number the Fibonacci series is created.
     */
    static int fibSeriesRecursion(int num)
    {
        /*
         * Base case:
         * When number is 1 or smaller than it, number itself is returned.
         */
        if (num <= 1)
            return num;
        /*
         * Recursive case:
         * Two recursive calls (tree recursion) with their return statements added
         * - Method called with input: number - 1
         * - Method called with input: number - 2
         */
        return fibSeriesRecursion(num-1) + fibSeriesRecursion(num-2);
    }
    public static void main(String[] args) {
        int num = 7;
        System.out.println(fibSeriesRecursion(num));  
    }
}