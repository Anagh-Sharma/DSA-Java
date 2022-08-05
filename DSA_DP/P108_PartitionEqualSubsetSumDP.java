package DSA_DP;

import java.util.Arrays;

public class P108_PartitionEqualSubsetSumDP {
    /*
     * Recursive solution:
     * Include and exclude each element of the array in the running sum, and 
     * If the running sum equals the sum of the array/2 then, such a subset exists.
     */
    static boolean partitionEqualSum(int index, int target, int[] nums)
    {
        /*Base case: When target becomes zero */
        if(target == 0)
            return true;
        /*Base case: When index exceeds the length of the array. */
        else if(index >= nums.length)
            return false;
        /*
         * Recursive case:
         * - If the element at current index is less that or equal to target:
         *   Include the element: Recursive call with: index: incremented, target-num at this index
         * - Else
         *   Exclude the element: Recursive call with: index: incremented, target
         */
        else if(nums[index] <= target)
        {
            return partitionEqualSum(index+1, target - nums[index], nums);
        }
        return partitionEqualSum(index+1, target, nums);
    }
    /*
     * DP (Memoization) optimization
     * - Applicable as the problem has overlapping subproblems.
     * - Cacheing the solutions to subproblems with different targets and indices.
     */
    static boolean partitionEqualSumCache(int index, int target, int[] nums, Boolean[][] cache)
    {
        /*Base case: When target becomes zero */
        if(target == 0)
            return true;
        /*Base case: When index exceeds the length of the array. */
        else if(index >= nums.length)
            return false;
        
        if(cache[index][target] != null)
            return cache[index][target];
        /*
         * Recursive case:
         * - If the element at current index is less that or equal to target:
         *   Include the element: Recursive call with: index: incremented, target-num at this index
         * - Else
         *   Exclude the element: Recursive call with: index: incremented, target
         */
        else if(nums[index] <= target)
        {
            cache[index][target] = partitionEqualSum(index+1, target - nums[index], nums);
            return cache[index][target];
        }
        cache[index][target] = partitionEqualSum(index+1, target, nums);
        return cache[index][target];
    }
    /*
     * DP (Tabulation) optimization
     * - Applicable as the problem has optimal substructure.
     * - Caching the solutions to subproblems with different targets and indices.
     * - A cell when true, indicates that there exists a subset of the array within 
     *   the subset of the array ending at that index whose values add up to that 
     *   target, or remaining sum.
     */
    static boolean partitionEqualSumTable(int[] nums)
    {
        int target = Arrays.stream(nums).sum()/2;
        boolean[][] cache = new boolean[nums.length][target+1];
        /*Iterate through all elements as last indices of subsets. */
        for(int i=0; i<nums.length; i++)
        {
            /*Iterate through all possible values of remaining sum or target */
            for(int j=0; j<=target; j++)
            {
                /* 
                 * When number is of index 0 and target is 0:
                 * Then 0 can be reached by simply not adding the term.
                 */
                if(i==0 && j==0)
                    cache[i][j] = true;
                /*
                 * When number is of index 0: 
                 * The target can only be reached when it is equal to the 
                 * number at index 0 of the numbers array.
                 */
                else if(i==0)
                    cache[i][j] = (j == nums[0] ? true : false);
                
                /*When target is 0: It can be reached by not adding any term */
                else if(j==0)
                    cache[i][j] = true;

                /*
                 * When the current remaining sum, or target, or column value is reached
                 * in the last row, or array index, i.e., a subset of itself, then:
                 * - The current row for the current column is also set true, as a subset 
                 *   of the subset up to the current index can produce the column value, or 
                 *   target.
                 * THIS CORRESPONDS TO NOT INCLUDING THE ELEMENT IN THE CHECKED SUBSET.
                 */
                else if(cache[i-1][j] == true)
                    cache[i][j] = true;
                /*
                 * When the the current element does not exceed the current column value, 
                 * or current target:
                 * Set true if:
                 * The subset of this index can produce the reduced target sum, reduced as
                 * as a result of adding this element.
                 * THIS CORRESPONDS TO INCLUDING THE ELEMENT IN THE CHECKED SUBSET.
                 */
                else if(nums[i] <= j)
                    cache[i][j] = cache[i-1][j - nums[i]];
            }
        }
        /*
         * Return the cache value corresponding to:
         * Element index: Last index, indicating that all elements were tested
         * Target index: Required target
         */
        return cache[nums.length-1][target];
    }

    public static void main(String[] args) {
        int[] nums = {1, 5, 11, 5};
        int target = Arrays.stream(nums).sum()/2;
        Boolean[][] cache = new Boolean[nums.length][target+1];
        System.out.println(partitionEqualSum(0, target, nums));
        System.out.println(partitionEqualSumCache(0, target, nums, cache));
        System.out.println(partitionEqualSumTable(nums));
    }
}
