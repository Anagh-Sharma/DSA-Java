package Array_Problems;

import java.util.Arrays;
/*
 * 2D space problem.
 * 
 * - Solved using prefix max and suffix max arrays.
 * - In a 2D space, with respect to the discrete units of first dimension D1, the total maximum 
 *   discrete units of information that can be filled along the second dimension D2, in a discrete 
 *   unit A of dimension D1, is constrained by the information filled in the discrete unit with 
 *   minimum information out of discrete units with maximum information preceding and succeeding A.
 * - Capacity at discrete unit = Min(Max capacity on left, Max capacity on right) – capacity 
 *   occupied at that unit
 * - Total capacity += capacity at all discrete units
 */

public class P26_TrapWaterProblem {
    static void rainWaterTrap(int[] height)
    {
        int n = height.length;
        int capacity = 0;
        int prefix_max[] = new int[n];
        int prefix = Integer.MIN_VALUE;
        int suffix_max[] = new int[n];
        int suffix = Integer.MIN_VALUE;
        for(int i=0; i<n; i++)
        {
            if(height[i]>prefix)
            {
                prefix_max[i] = height[i];
                prefix = height[i];
            }
            else
            {
                prefix_max[i] = prefix;
            }
        }
        for(int j=n-1; j>=0; j--)
        {
            if(height[j]>suffix)
            {
                suffix_max[j] = height[j];
                suffix = height[j];
            }
            else
            {
                suffix_max[j] = suffix;
            } 
        }

        for(int k=0; k<n; k++)
        {
            if(prefix_max[k]<suffix_max[k])
            {
                capacity += prefix_max[k] - height[k];
            }
            else
            {
                capacity += suffix_max[k] - height[k];
            }
        }

        System.out.println(Arrays.toString(prefix_max));
        System.out.println(Arrays.toString(suffix_max));
        System.out.println(capacity);
    }
    public static void main(String[] args) {
        int arr[] = {0,1,0,2,1,0,1,3,2,1,2,1};
        rainWaterTrap(arr);
    }
}
