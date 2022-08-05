package Array_Problems;
/*
 * int net_cost = 0;
 * int starting_point = -1;
 * Iterate i through gas array
 * starting_point = i;
 * net_cost = 0;
 * Iterate j through gas array starting from i value
 * if(gas[j]-cost[j]>=0)
 * then net_cost +=  gas[j] - cost[j] + gas[j+1]
 *      j++;
 * else
 *      break;
 * 
 * Net expediture must be smaller than net balance at an index
 */
public class P29_GasStation {
    // Simulating travel
    // Brute Force: O(N^2)
    static void gasStation_bruteForce(int[] gas,int[] cost)
    {
        int n = gas.length;
        // Variable Expenditure at an index is value in
        // the cost array at the same index
        int expediture = 0;
        /*
         * Variable Balance at an index is the cumulative 
         * Gas[index] - Expenditure + Gas[Index+1] 
         * Until that index.
         */
        int balance = 0;
        /*
         * Varaibles current and next indices
         * help calculate balance at an
         * index.
         */
        int currIndex = 0;
        int nextIndex = 0;
        int starting_point = -1;
        boolean flag = true;
        /*
         * Outer loop:
         * Iterates through the elements of the 
         * Gas array to check each element as a
         * possible starting point.
         */
        for(int i=0; i<n; i++)
        {
            /*
             * The current index is taken as the
             * starting point and the code below
             * tests if the array can be traversed
             * in circular manner and this starting
             * index can be reached again.
             */
            starting_point = i;
            /*
             * Balance at starting point is just
             * the Gas array value at that index
             * and it is iteratively altered due
             * to expemnditure and gas array values
             * of subsequent indices of the circular 
             * circuit.
             */
            balance = gas[i];
            /*
             * If the flag remains true throughout the
             * traversal of the array in a circular manner
             * starting from the current starting point then, 
             * this starting point index is the required unique
             * solution.
             */
            flag = true;
            /*
             * Inner Loop:
             * Iterates through each index of the arrays
             * until balance becomes negative at which point
             * the loop is stopped.
             */
            for(int j=0; j<n; j++)
            {
                /*
                 * Starting from i we have to move in circle
                 * using iterator j. Modulus helps in getting
                 * to the beginning of the array after reaching
                 * the end. 
                 */
                /*
                 * Note: Can iterate through an array as 
                 * if it were a circle by using modulus: 
                 * index % size
                 */
                currIndex = (i+j)%n;
                nextIndex = (currIndex+1)%n;
                /* 
                 * Expenditure at an index is
                 * determined from the cost
                 * array
                 */
                expediture = cost[currIndex];
                /*
                 * Balance = total cumulative
                 * balance until the preceding 
                 * index + Gas value at current
                 * index - expenditure
                 * 
                 * Note: Gas value of the current
                 * index is added to the balance
                 * in the if condition of the last
                 * iteration.
                 */
                balance = balance - expediture;
                /*
                 * When balance is either 0 or
                 * greater than zero after reaching
                 * the next index then, reaching the
                 * next index is possible.
                 */
                if(balance>=0)
                {
                    balance += gas[nextIndex];
                }
                /*
                 * Flag becomes false if balance becomes
                 * negative due the expenditure and the 
                 * next index cannot be reached and we 
                 * break from the for loop.
                 */
                else
                {
                    flag = false;
                    break;
                }
            }
            /*
             * Flag is checked outside the inner loop
             * If flag is true than the entire array
             * was traversed and the starting point
             * is the required unique solution.
             */
            if(flag)
            {
                System.out.println(starting_point);
            }
        }
    }
    // Optimized Greedy Solution
    /*
     * Intuition:
     * - Check first index as a potential starting point by adding the associated 
     * gas and subtracting the cost to and from balance respectively and repeat the process
     * until at an index the balance becomes negative. Then repeat the process for the next
     * index (index after the index where balance became negative and not the index after 
     * the current starting point) after resetting the balance to zero.
     * - The solution is the index of the starting point whose balance does not become negative.
     * - The balances of the tested and failed starting points can be accumulated. If the
     *   accumulated balance added to the balance of the solution is greater than or equal to
     *   zero then solution is valid. Otherwise, no solution exits. Therefore:
     *   Sum(Gas) >= Sum(Cost) or Sum(Gas) - Sum(Cost) >= 0
     */
    static void gasStation_optimized(int[] gas,int[] cost)
    {
        /*
         * Starting point set to zero because since this is 
         * a Greedy solution, the first possible starting 
         * point will be tested and then the next.
         */
        int starting_point = 0;
        /*
         * Variable accumulatedBalance stores the cumulative 
         * balance of tested and failed starting points 
         * acculmulated to the variable when their 
         * associated balance becomes negative. 
         */
        int accumulatedBalance = 0;
        /*
         * Variable balance stores the cumulative balance
         * accumalted since the current starting point being 
         * tested.
         */
        int balance = 0;
        for (int i = 0; i < gas.length; i++)
        {
            /*
             * Balance 
             * = total cumulative balance 
             *   from current starting point
             *   until the preceding index 
             * + Gas value at current index 
             * - expenditure at the current index 
             */
            balance += gas[i] - cost[i];
            /*
             * When the balance dips below zero:
             * - The balance is accumulated to
             *   accumulatedBalance.
             * - Balance is set to zero for testing
             *   the next starting point.
             * - the current starting point is 
             *   discarded and the next index (i+1)
             *   is assumed to be the "Greedy"
             *   solution until proven otherwise.
             */
            if (balance < 0) 
            {
                accumulatedBalance += balance;
                balance = 0;
                starting_point = i + 1;
            }   
        }
        /*
         * If the current balance + accumulatedBalance >= 0 
         * then a solution exists and it is the index of the 
         * starting point whose balance did not become negative 
         * and was therefore not added to the accumulatedBalance.
         * If:
         *   Sum(Gas) >= Sum(Cost)
         * = Sum(Gas) - Sum(Cost) >= 0
         * = balance + accumulatedBalance >= 0
         */
        if (balance + accumulatedBalance >= 0) 
        {
            System.out.println(starting_point);
        } else 
        {
            System.out.println(-1);          
        }
    }
    public static void main(String[] args) {
        int[] gas = {1,2,3,4,5};
        int[] cost = {3,4,5,1,2};
        // gasStation_bruteForce(gas, cost);
        gasStation_optimized(gas, cost);
    }
}
