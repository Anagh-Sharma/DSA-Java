package DSA_Recursion;

import java.util.ArrayList;

public class P89_DiceGameRecursion {
    /*
     * Tree Recursion:
     * Intuitively: If the required total sum is n, then, the first subpart of the problem is:
     * - If the first number is 1, then n-1 has to be added.
     * - If the first number is 2, then n-2 has to be added, and so on till first
     *   number is 6.
     */
    static int diceGame(int currSum, int sum)
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

        /*
         * Recursive case:
         * Recursively try all possible additions by adding all values
         * from 1 to 6 and then adding their returned values.
         */
        return diceGame(currSum+1, sum) + diceGame(currSum+2, sum) +
        diceGame(currSum+3, sum) + diceGame(currSum+4, sum) +
        diceGame(currSum+5, sum) + diceGame(currSum+6, sum);
    }

    /*
     * This approach stores all the possible combinations of numbers
     * from 1 to 6 which produce the required number upon addition.
     */
     /*
     * Tree Recursion:
     * - Iterate through all numbers from  1 to 6 as possible first numbers in a sequence
     *   that adds up to the required number.
     * - A first number can be the first number of multiple such sequences.
     * - Iterate through all numbers from  1 to 6 as possible second numbers in a sequence
     *   that adds up to the required number.
     * - A second number can be the second number of multiple such sequences.
     * - And so on, from 1 to 6.
     * - A subsequent number in the sequence is searched until the base case is not found.
     * - When the positive base case, i.e., required number is found, the sequence is 
     *   complete and the sequence is appended at return time.
     * - The numbers of such sequences are appended backwards, i.e., the last number in 
     *   such sequence is appended first during unwinding then, the second is appended 
     *   before it, and so on.
     * - Since a prefix of a sequence can have mutiple suffixes, or a node can have multiple
     *   branches, a prefix could be appended before multiple suffixes that all lead up to
     *   final sum that is the required number.

     * - So, for a number to be appended behind the sequences of a node's temp ArrayList
     *   of strings containing valid suffixes:
     * 
     * - The number has to produce the required number producing the recursive call with
     *   positive base case.
     *   - The recursive call that produces this recursive call will contribute the last
     *     number of a unique sequence.
     * - The number has to be a part of the sequence or prefix that leads to the base case.
     *   - All such single numbers are appended behind the sequences returned during unwinding.
     * 
     * Note: To better understand:
     * - Picture a tree with its root as an empty string.
     * - Then add all numbers from 1 to 6 to six different copies of this empty
     *   string with different copies being different branches.
     * - Keep adding a branch from 1 to 6 to each branch until the required sum is 
     *   obtained.
     * - Thus, there can be multiple branches.
     * - Record the numbers added from leaf to root, in reverse order.
     */
    static ArrayList<String> game(int currentValue, int endValue) 
    {
        /*
         * currentValue: Obtained by adding the a value from 1 to
         * 6 to the previous iteration's "currentValue" to which
         * values from 1 to 6 are added and tested to see if 
         * they exceed the required sum.
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
        /*
         * Stores all of a single loop's valid addition of numbers
         * from 1 to 6 that do not exceed the required sum.
         */
        ArrayList<String> temp = new ArrayList<>();
        /*
         * Iterate from 1 to 6 to check all possible combinations
         * of numbers by adding them to the currentValue.
         * 
         * - Every iteration of dice will add the number to the string
         *   if its addition to the currentValue does not exceed required number.
         * - This final string will then be returned to the recursive call from
         *   the last iteration with all valid suffixes.
         * 
         * In more detail: 
         * - The for loop iterates through all the numbers from 1 to 6.
         * - When the running sum becomes equal to the required sum:
         *   The number whose addition produced this final number is appended
         *   to an ArrayList of strings "temp" and returned to the last recursive call
         *   that produced the the current "currentValue" by adding a number
         *   from 1 to 6 to its own "currentValue".
         *   [Numbers after the successful number are also tried, but naturally nothing is 
         *   returned since the for loop iterates incrementally.]
         * - The returned ArrayList of strings with the successfully tried final number
         *   is appended to the number that was added to this recursive call's "currentValue".
         *-  All numbers to the "temp" string whose addition to the running sum total 
         *   or "currenValue" does not exceed the required sum, these numbers lead
         *   up to multiple possible unique sequences and return a valid suffix. These
         *   prefixes are appeneded to the number leading up to them from the current
         *   method call's loop.
         *   
         *   Therefore:
         * - This string of all numbers from 1 to 6 that can be added to this recursive
         *   call's currentSum without exceeding the required number is retuned to the 
         *   previous recursive call's for loop's recurisive call that produced the current 
         *   sum total or "currentValue" which was in that loop a specific iteration's "newValue".
         * - This previous iteration then adds the number it had added to its "currentValue" 
         *   to produce the next iteration's "currentValue" to all the strings, representing
         *   valid suffixes, in the returned ArrayList of strings.
         */
        for(int dice = 1; dice <= 6; dice++) 
        {
            int newValue = currentValue + dice;// 0: 1 | 1: 1 | 2: 1 2 3 4 5 6
            ArrayList<String> result = game(newValue, endValue);// 1 3 | 2 3 | 3 3 |
            for(String s : result) 
            {
                temp.add(dice + s);
            }
        }
        return temp;
    }
    public static void main(String[] args) {
        int sum = 3;
        int currSum = 0;
        System.out.println(diceGame(currSum, sum));
        System.out.println(game(currSum, sum));
    }
}
