package DSA_DP;

public class P103_DiceGameDP {
    /*
     * Tree Recursion:
     * Intuitively: If the required total sum is n, then, the first subpart of the problem is:
     * - If the first number is 1, then n-1 has to be added.
     * - If the first number is 2, then n-2 has to be added, and so on till first
     *   number is 6.
     */
    /*
     * DP optimization:
     * - For a certain running sum, there is only a constant number
     *   of ways that numbers from a set of numbers can be added to
     *   obtain the required sum.
     * - This constant number can be cached and reused when the same
     *   running sum is obtained in a different branch of the space
     *   state tree.
     */
    static int diceGameDP(int currSum, int sum, int[] cache)
    {
        /*
         * Negative Base case:
         * When the current sum goes beyond the total required: return 0.
         */
        if(currSum > sum)
            return 0;
        /*
         * Base case:
         * When the current sum equals the total required: return 1.
         * This signifies that a unique way to find the sum has been found.
         */
        else if(currSum == sum)
            return 1;

        else if(cache[currSum] != 0)
            return cache[currSum];
        /*
         * Recursive case:
         * Recursively try all possible additions by adding all values
         * from 1 to 6 and then adding their returned values.
         */

        cache[currSum] = diceGameDP(currSum+1, sum, cache) + diceGameDP(currSum+2, sum, cache) 
        + diceGameDP(currSum+3, sum, cache) + diceGameDP(currSum+4, sum, cache) 
        + diceGameDP(currSum+5, sum, cache) + diceGameDP(currSum+6, sum, cache);

        return cache[currSum];
    }

    static int countWays(int currentValue, int endValue) {
        if(currentValue == endValue) {
            return 1;
        }

        // Negative Base Case
        if(currentValue > endValue) {
            return 0;
        }
        int count = 0;
        for(int dice = 1; dice <= 6; dice++) {
            int newValue = currentValue + dice;
            count = count + countWays(newValue, endValue);
        }
        return count;
    }

    /*
     * DP optimization:
     * - For a certain running sum, there is only a constant number
     *   of ways that numbers from a set of numbers can be added to
     *   obtain the required sum.
     * - This constant number can be cached and reused when the same
     *   running sum is obtained in a different branch of the space
     *   state tree.
     */
    static int countWaysDP(int currentValue, int endValue, int cache[]) {
        if(currentValue == endValue) {
            return 1;
        }

        // Negative Base Case
        if(currentValue > endValue) {
            return 0;
        }

        if(cache[currentValue] != 0) {
            return cache[currentValue];
        }

        int count = 0;
        for(int dice = 1; dice <= 6; dice++) {
            int newValue = currentValue + dice;
            count = count + countWaysDP(newValue, endValue, cache);
        }
        cache[currentValue] = count;
        return count;
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
    static int tabulation(int start, int end)
    {
        /*Size of cache: End+1 to include end itself as a key in the hash map. */
        int cache[] = new int[end + 1];
        /* 
         * The cache value for End is set for 1 as it is known and is used
         * to calculate the hash values of the preceding key.
         */
        cache[end] = 1;
        /*
         * Iterate throught cache values backwards,
         * from end - 1 to 0 in cache to calculate 
         * all preceding keys' values by calculating the
         * values of their suceeding keys first.
         */
        for(int i = end - 1; i >= 0; i--) 
        {
            /*
             * Iterate through the next indices, six at most, and add
             * there values to the current index.
             */
            int count = 0;
            for(int dice = 1; dice <= 6 && dice + i < cache.length; dice++) 
            {
                count += cache[dice + i];
            }
            cache[i] = count;
        }
        return cache[start];
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        int result = countWays(0, 30);
        int n = 30;
        System.out.println(result);
        long endTime = System.currentTimeMillis();
        System.out.println("Total time take : " + (endTime - startTime) + "ms");

        startTime = System.currentTimeMillis();
        result = countWaysDP(0, n, new int[n+1]);
        System.out.println(result);
        endTime = System.currentTimeMillis();
        System.out.println("Total time take : " + (endTime - startTime) + "ms");

        startTime = System.currentTimeMillis();
        result = tabulation(0, n);
        System.out.println(result);
        endTime = System.currentTimeMillis();
        System.out.println("Total time take : " + (endTime - startTime) + "ms");
    }
}
