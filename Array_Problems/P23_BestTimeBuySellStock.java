package Array_Problems;

import java.util.Arrays;
public class P23_BestTimeBuySellStock {
    static void buyStockBestProfit_approach1(int[] nums)
    {
        int n = nums.length, curr_profit = 0, best_profit=Integer.MIN_VALUE;
        for(int i=0; i<n; i++)
        {
            for(int j=i+1; j<n; j++)
            {
                curr_profit = nums[j] - nums[i];
                if(best_profit< curr_profit)
                {
                    best_profit = curr_profit;
                }
            }
        }
        System.out.println(best_profit);
    }
    /*
     * Two pointer approach:
     * [Store the maximum profit at the index itself]
     * Pointer 1, i: Iterates through each element
     * temp: Store the element at index i
     * Pointer 2, j: Iterates through each element succeeding i
     * Calculate the difference: arr[j] - temp
     * Check if it is greater than the last difference
     * If yes then store the difference at arr[i]
     * 
     * Find maximum profit by finding the maximum element in the transformed array
     * Store the maximum profit at the index itsef
     */
    static void buyStockBestProfit_approach2(int[] nums)
    {
        int n = nums.length;
        int curr_buy = 0, curr_profit = 0, best_profit = 0, flag=0;
        System.out.println(Arrays.toString(nums));
        // Pointer 1, i: Iterates through each element
        for(int i=0; i<n; i++)
        {
            // Storing the buying price
            curr_buy = nums[i];
            // Setting the profit to zero so that the first
            // calculated profit is set before greater ones 
            // are recorded.
            nums[i] = 0;
            for(int j=i+1; j<n; j++)
            {
                // Profit when selling price is greater
                if(nums[j]>curr_buy)
                {
                    // Calculate profit
                    curr_profit = nums[j] - curr_buy;
                    // If profit excedes the current stored profit
                    // then it is stored
                    // Note that nums[i] is instially set to zero
                    // in loop 1, as the first calculated profit
                    // is obviously smaller than the buying price.
                    if(curr_profit>nums[i])
                    {
                        nums[i] = curr_profit;
                    }
                    // Flag to denote that there was possible
                    // selling price larger than the buying price
                    // and thus a profit was calculated
                    flag=1;
                }
            }
            // If no profit was calculated set the value at the index as zero
            if(flag==0)
                nums[i]=0;
            // Reset flag as zero for next iteration of buying price
            flag=0;
        }
        // Search the maximum profit
        best_profit = nums[0];
        for(int k=0; k<n; k++)
        {
            if(nums[k]>best_profit)
                best_profit = nums[k];
        }
        System.out.println(best_profit);
        System.out.println(Arrays.toString(nums));
    }
    static void buyStockBestProfit_approach3(int arr[]) 
    {
        int n = arr.length;
        int profit = 0;
        int finalProfit = 0;
        int prefixMin[] = new int[n];
        prefixMin[0] = arr[0];
        for(int i = 1; i < n; i++) {
            prefixMin[i] = Math.min(prefixMin[i-1], arr[i]);
        }

        for(int i = 1; i < n; i++) {
            finalProfit = arr[i] - prefixMin[i];
            profit = Math.max(finalProfit, profit);
        }
    }

    static void buyStockBestProfit_approach4(int arr[]) {

        int buy_min = Integer.MAX_VALUE;
        // int curr_profit = 0;
        int max_profit = 0; 
        for(int i = 0; i < arr.length; i++) {
            /*
             * Iteratively calculating profit using buy_min and the element of the current 
             * iteration and only storing it if it is greater than the last calculated profit
             */
            // curr_profit = arr[i] - buy_min;
            if(max_profit<arr[i] - buy_min)
            {
                max_profit = arr[i] - buy_min;
            }
            // max_profit = Math.max(max_profit, arr[i] - buy_min);
            /* Prefix Min: Minimum encountered number
             * Then, changing the buy_min or buying price with the current element for next 
             * profit calculations if it is smaller than the current buy_min because profit 
             * is greater if buying price is as small as possible.
             */
            if(buy_min>arr[i])
            {
                buy_min = arr[i];
            }
            // buy_min = Math.min(buy_min, arr[i]);
        }
    }
    public static void main(String[] args) {
        int arr[] = {7,1,5,3,6,4};
        buyStockBestProfit_approach1(arr);
        buyStockBestProfit_approach2(arr);
        buyStockBestProfit_approach3(arr);
        buyStockBestProfit_approach4(arr);
    }
}
