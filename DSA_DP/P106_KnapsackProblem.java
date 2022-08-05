package DSA_DP;

public class P106_KnapsackProblem {
    /*Recursive solution. */
    static int knapsack(int []weights, int values[], int capacity, int index) 
    {
        /*
         * Base case:
         * - When all items have been picked
         * - When the capacity becomes zero
         */
        if(index == weights.length || capacity == 0) 
        {
            return 0;
        }

        /*
         * Not including items with weight exceeding the current capacity.
         */
        if(weights[index] > capacity) 
        {
            return knapsack(weights, values, capacity, index+1);
        }
        else 
        {
            /*
             * Two options:
             * - Include the item.
             * - Not include the item.
             * The maximum of the recursive result of the two is
             * returned as the solution for this capacity and index.
             */
            int option_1 = values[index] + knapsack(weights, values, capacity - weights[index], index+1);
            int option_2 = knapsack(weights, values, capacity, index+1);
            return Math.max(option_1, option_2);
        }
    }
    /*
     * DP (Memoization) optimization
     * - Caching the solutions to subproblems with different capacities and item indices.
     */
    static int knapsackCacheDP(int []weights, int values[], int capacity, int index, int[][] cache) 
    {
        /*
         * Base case:
         * - When all items have been picked
         * - When the capacity becomes zero
         */
        if(index == weights.length || capacity == 0) 
        {
            return 0;
        }
        /*If the solution to the recursive subproblem exists in cache, it is not re-calculated. */
        if(cache[index][capacity] != 0)
            return cache[index][capacity];
        /*
         * Not including items with weight exceeding the current capacity.
         */
        if(weights[index] > capacity) 
        {
            return knapsack(weights, values, capacity, index+1);
        }
        else 
        {
            /*
             * Two options:
             * - Include the item.
             * - Not include the item.
             * The maximum of the recursive result of the two is
             * returned as the solution for this capacity and index.
             */
            int option_1 = values[index] + knapsack(weights, values, capacity - weights[index], index+1);
            int option_2 = knapsack(weights, values, capacity, index+1);
            /*Caching the solution to the subproblem. */
            cache[index][capacity] = Math.max(option_1, option_2);
            return cache[index][capacity];
        }
    }
    /*
     * DP (Tabulation) optimization:
     * - Bottom-up approach
     * - Tabulate solutions to all possible combinations of 
     *   items and remaining capacity
     * - Return tabulated data for maximum capacity and items.
     * 
     * Tabulation logic:
     * - Each row represents an item and rows span from 0 items to n items.
     *   The index 1 of the table's row will correspond to the index 0 of items array.
     * - Each column represents a remaining capacity starting from 0 capacity to final capacity.
     */
    static int knapsackTabulation(int capacity, int[] weights, int[] values, int items) 
    {
        int i, w;
        int k[][] = new int[items+1][capacity+1];
        /*Iterating through all item indices. */
        for(i = 0; i <= items; i++)
        {
            /*Iterating through all possible values of remaining capacity. */
            for(w = 0; w <= capacity; w++)
            {
                /*When either items or capacity is 0, nothing is tabulated. */
                if(i == 0 || w == 0) 
                {
                    k[i][w] = 0;
                }
                /*
                 * When weight of current row's item is less than capacity, 
                 * then the maximum of the two is tabulated:
                 * For adding the item: values[i-1] + k[i-1][w - weights[i-1]]
                 * - Value of the row's item + tabulated value for:
                 *   - Item: Index of the previous item's row.
                 *   - Capacity: capacity at index of current remaining capacity - added item's capacity.
                 *     Indicating: Could the previous item also be added if the capacity is the current 
                 *     reduced capacity.
                 * For not adding the item: k[i-1][w]
                 * - Tabulated value for:
                 *   - Index: Previous item's row
                 *   - Capacity: Current iteration's remaining capacity.
                 */
                else if(weights[i-1] <= w) 
                {
                    k[i][w] = Math.max(values[i-1] + k[i-1][w - weights[i-1]], k[i-1][w]);
                }
                else 
                {
                    /*
                     * When, the weight exceeds a remaining capacity, no further
                     * item can be added and the previous accumulated value for 
                     * the same remaining capacity stored in the previous row and 
                     * the same column is tabulated.
                     */
                    k[i][w] = k[i-1][w];
                }
            }
        }
        /*
         * Returning tabulated value for:
         * - Item: Max items
         * - Capacity: Max capacity
         */
        return k[items][capacity];
    }

    public static void main(String[] args) {
        int []weights = {10,20,30};
        int values[] = {60,100,120};
        int capacity = 50;
        int n = values.length;
        /*max is incremented as this the max value itself is cached. */
        int[][] cache = new int[weights.length][capacity+1];
        // int result = knapsack(weights, values, max, 0);
        int result = knapsackTabulation(capacity, weights, values, n);
        System.out.println(result);

        int resultCache = knapsackCacheDP(weights, values, capacity, 0, cache);
        System.out.println(resultCache);
    }
}
