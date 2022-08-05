package DSA_Backtracking;

/*
 * Base case:
 * When the sum becomes equal to the required sum.
 * Space state tree: 
 * 1st level: Every single number
 * 2nd level: Every single number with every single number of level 1.
 * and so on..
 * 
 * Recursive case:
 * Iterate over all the numbers:
 * Recursive call with: 
 * - Number added to the running sum
 * - Number added to the ArrayList collecting all the added numbers.
 */
public class P101_CombinationSum {

    /*
     * Recursive function: isMatch
     * Recursively check if the input string's first character
     * matches the character at the input string.
     */
    static void combinationSum(int sum, int reqSum, int[] nums, String path) 
    {
        /*
         * Base case: Print and return when the sum is equal to required sum.
         */
        if(sum == reqSum) 
        {
            System.out.println(path);
            return;
        }

        /*
         * Backtrack:
         * Return when sum exceeds required sum.
         */
        if(sum > reqSum) 
        {
            return;
        }

        /*
         * Recursive case:
         * 
         * Iterate over all the numbers:
         * Recursive call with: 
         * - Number added to the running sum
         * - Number added to the String collecting all the added numbers.
         */
        for(int i = 0; i<nums.length; i++) 
        {
            combinationSum(sum+nums[i], reqSum, nums, path + nums[i]);
        }
    }

    public static void main(String[] args) {
        int sum = 0;
        int reqSum = 7;
        int[] nums = {2,3,6,7};
        String path = "";
        combinationSum(sum, reqSum, nums, path);
    }
}
