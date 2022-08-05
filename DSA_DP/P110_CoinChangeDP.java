package DSA_DP;

public class P110_CoinChangeDP {
    /*Recursive approach */
    static int coinChange(int currTotal, int total)
    {
        /*
         * Negative Base case:
         * When the current total goes beyond the required total: return 0.
         */
        if(currTotal > total)
            return 0;
        /*
         * Positive Base case:
         * When the current total equals the required total: return 1.
         * This signifies that a unique way add up to the required total has been found.
         */
        else if(currTotal == total)
            return 1;
        /*
         * Recursive case:
         * - Two recursive paths are taken:
         * - First when the step size is 1.
         * - Second when the step size is 2.
         */
        return coinChange(currTotal+2, total) + coinChange(currTotal+3, total)
        + coinChange(currTotal+5, total) + coinChange(currTotal+6, total); 
    }

    /*
     * DP (Memoization) optimization
     * - Applicable as the problem has overlapping subproblems.
     * - Caching the solutions to subproblems with different current total.
     */
    static int coinChangeDP(int currTotal, int total, int[] cache)
    {
        /*
         * Negative Base case:
         * When the current total goes beyond the required total: return 0.
         */
        if(currTotal > total)
            return 0;
        /*
         * Positive Base case:
         * When the current total equals the required total: return 1.
         * This signifies that a unique way add up to the required total has been found.
         */
        else if(currTotal == total)
            return 1;
        /*
         * Recursive case:
         * - Two recursive paths are taken:
         * - First when the step size is 1.
         * - Second when the step size is 2.
         */
        if(cache[currTotal] != 0)
            return cache[currTotal];

        cache[currTotal] = coinChangeDP(currTotal+2, total, cache) 
        + coinChangeDP(currTotal+3, total, cache)
        + coinChangeDP(currTotal+5, total, cache) 
        + coinChangeDP(currTotal+6, total, cache); 

        return cache[currTotal];
    }

    public static void main(String[] args) 
    {
        int total = 5;
        int currTotal = 0;
        int[] cache = new int[total+1];
        System.out.println(coinChange(currTotal, total));
        System.out.println(coinChangeDP(currTotal, total, cache));
    }
}
