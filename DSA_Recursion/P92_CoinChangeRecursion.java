package DSA_Recursion;

import java.util.ArrayList;

public class P92_CoinChangeRecursion {
    /*
     * Counting all the valid sequences:
     * 
     * Tree recursion:
     * Intuitively:
     * - If the first addition is of 2, then the rest of the steps are
     *   added from 2, and then n-2 stairs need to be climbed.
     * - Else if the number is any of the other options, then the 
     *   remaining sum is also similarly represented.
     *   coinChange(0, n) = coinChange(1, n) + coinChange(2, n) 
     *                    + coinChange(3, n) + coinChange(4, n)
     *                    + coinChange(5, n)
     */
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
     * Intuition same as the dice problem.
     * Storing all the valid sequences.
     */
    static ArrayList<String> coinSum(int currentValue, int endValue) 
    {
        /*
         * currentValue: Obtained by adding the value from the
         * valid coins to the previous iteration's "currentValue" 
         * to which values from valid coins are added and tested 
         * to see if they exceed the required sum.
         */
        /*
         * Positive Base case:
         * When the current sum reaches the required sum: return 
         * ArrayList with empty string. 
         * 
         * - This returned empty string will be used to convert the
         *   last number leading upto the successful addition to a string
         *   which will then be appended to the returning string.
         * 
         * - This string will then be appended to the similarly converted
         *   second last number and so on.
         */
        if(currentValue == endValue) 
        {
            ArrayList<String> t = new ArrayList<>();
            t.add("");
            return t;
        }
        /*
         * Negative base case:
         * When the current sum exceeds the required sum: return empty
         * ArrayList with no string.
         * 
         * - This means that the number upon whose addition the current
         *   sum exceeded the required sum will not be appended to the string.
         */
        if(currentValue > endValue) {
            ArrayList<String> t = new ArrayList<>();
            return t;
        }

        int coins[] = {2, 3, 5, 6};
        /*
         * Stores all of a single loop's valid addition of numbers
         * in the coins that do not exceed the required sum.
         */
        ArrayList<String> temp = new ArrayList<>();
        /*
         * Recursive case:
         * Check all valid coins by adding them to the current running sum with
         * the result producing multiple valid suffixes.
         */
        for(int coin: coins) 
        {
            int newValue = currentValue + coin;
            ArrayList<String> result = coinSum(newValue, endValue);
            for(String s : result) 
            {
                temp.add(coin + s);
            }
        }
        return temp;
    }
    public static void main(String[] args) {
        int total = 5;
        int currTotal = 0;
        System.out.println(coinChange(currTotal, total));
        System.out.println(coinSum(currTotal, total));
    }
}
