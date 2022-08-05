package DSA_DP;

public class P102_FibonacciSeriesDP {
    /*
     * Tree recursion:
     * - Computes the number at a specific index of the Fibonacci series.
     * - Creates the Fibonacci series for every paramater passed as an index in the series.
     * - Every recursive call recursively calls the method on its two preceding numbers and for 
     *   each number the Fibonacci series is created.
     */
    /*
     * DP (Memoization)
     * - The results of recursive calls are stored in an array and passed to
     *   subsequent recursive calls.
     */
    static int fibSeriesRecursionMemo(int num, int[] cache)
    {
        /*
         * Base case:
         * When number is 1 or smaller than it, number itself is returned.
         */
        if (num <= 1)
            return num;

        if(cache[num] != 0)
            return cache[num];
        /*
         * Recursive case:
         * Two recursive calls (tree recursion) with their return statements added
         * - Method called with input: number - 1
         * - Method called with input: number - 2
         */
        cache[num] = fibSeriesRecursionMemo(num-1, cache) + fibSeriesRecursionMemo(num-2, cache);
        return cache[num];
    }

    /*
     * - DP (Tabulation)
     * - Does not use recursive calls.
     * - A cache array maps indices of Fibonacci series to values of the series.
     * - Iteration is used wherein all subsequent index’s values are computed 
     *   using the value of two previous indices and the result is stored in the 
     *   cache array.
     */
    static int fibTabulation(int n, int cache[]) 
    {
        cache[0] = 0;
        cache[1] = 1;
        for(int i = 2; i <= n; i++) 
        {
            cache[i] = cache[i-1] + cache[i-2];
        }
        return cache[n];
    }
    public static void main(String[] args) {
        int num = 10;
        int[] cache = new int[num+1];
        System.out.println(fibSeriesRecursionMemo(num, cache));  
    }
}
