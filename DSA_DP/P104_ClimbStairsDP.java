package DSA_DP;

public class P104_ClimbStairsDP {
    /*
     * Tree recursion:
     * Intuitively:
     * - If the first step is of size 1, then the rest of the steps are
     *   climbed from stair 1, and then n-1 stairs need to be climbed.
     * - If the first step is of size 2, then the rest of the steps are
     *   climbed from stair 2, and then n-2 stairs need to be climbed.
     * climbStairsRecursion(0, n) = climbStairsRecursion(1, n) + climbStairsRecursion(2, n)
     */
    /*
     * DP (Memoization) Optimization:
     * - Top-down approach
     * Storing the number of ways stairs can be obtained from a certain stair
     * to be reused in other recursive calls in the space state tree.
     */
    static int climbStairsRecursionDP(int currStair, int stairs, int[] cache)
    {
        /*
         * Negative Base case:
         * When the current stair goes beyond the total number of stairs: return 0.
         */
        if(currStair > stairs)
            return 0;
        /*
         * Positive Base case:
         * When the current stair equals the total number of stairs: return 1.
         * This signifies that a unique way to climb all the stairs has been found.
         */
        else if(currStair == stairs)
            return 1;
        
        else if(cache[currStair] != 0)
            return cache[currStair];
        /*
         * Recursive case:
         * - Two recursive paths are taken:
         * - First when the step size is 1.
         * - Second when the step size is 2.
         */
        cache[currStair] = climbStairsRecursionDP(currStair+1, stairs, cache) + climbStairsRecursionDP(currStair+2, stairs, cache);

        return cache[currStair]; 
    }

    /*
     * Iterative solution with tabulation optimization.
     * - Bottom-up approach
     * - Problem: Count of the ways total sum be obtained
     *   from 0 by adding dice numbers 1 to 6 repeatedly.
     * - Sub-problem: Count of the ways total sum can be
     *   obtained from 6 numbers: 0 + 1 to 6 by adding
     *   dice numbers 1 to 6 repeatedly.
     * - This problem has optimal substructure, i.e.,
     *   if the solution to the subproblems above is
     *   known, then the solution to the problem can be
     *   computed.
     * - So, to count of the ways total sum can
     *   be obtained from 0, the counts of the ways total sum 
     *   can be obtained from 1 to 7 is added, thus by adding
     *   the associated counts of the next 6 values.
     * - If the value becomes so close to the the total sum
     *   that only a few numbers can be added to it from
     *   1 to 6 before the total sum is obtained, then only
     *   the associated counts of those few numbers needs to 
     *   be added to obtain this value' associated counts.
     * - Since it is known how many times total sum can be obtained
     *   from total sum (1, by adding nothing) and total sum - 1 
     *   (1 times as only 1 dice value could be added), its preceding 
     *   number, num - 2, is calculated by adding the associated 
     *   counts of its remaining suceeding values.
     * - Thus, a hash map recording the associated counts of
     *   all values from 0 to total sum is initialized:
     *   - The last two values of the array are known, thus the 
     *     preceding values are calculated by adding the 
     *     successive values, which can atmost be next 6 values
     *     as only 1 to 6, 6 numbers can be added and the next 6
     *     numbers obtained.[Constrained suffix sum.]
     */
    static int tabulationCountStairs(int stair, int stairs)
    {
        /*Size of cache: stairs+1 to include end itself as a key in the hash map. */
        int cache[] = new int[stairs + 1];
        /* 
         * The cache value for stairs is set for 1 as it is known and is used
         * to calculate the hash values of the preceding key.
         */
        cache[stairs] = 1;
        /*
         * Iterate throught cache values backwards,
         * from stairs - 1 to 0 in cache to calculate 
         * all preceding keys' values by calculating the
         * values of their suceeding keys first.
         */
        for(int i = stairs - 1; i >= 0; i--) 
        {

            if(i == stairs - 1)
            {
                cache[i] += cache[i + 1];
            }
            else
            {
                cache[i] += cache[i + 1];
                cache[i] += cache[i + 2];
            }
        }
        return cache[stair];
    }

    public static void main(String[] args) {
        int stairs = 5;
        int currStair = 0;
        int[] cache = new int[stairs+1];

        System.out.println(climbStairsRecursionDP(currStair, stairs, cache));
        System.out.println(tabulationCountStairs(currStair, stairs));
    }
}
