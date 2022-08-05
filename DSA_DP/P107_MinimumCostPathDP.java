package DSA_DP;

public class P107_MinimumCostPathDP 

{
    /*
     * - DP(Memoization) optimization:
     * The problem has optimal substructure.
     * Recursively find the minimum element
     * succeeding an element starting from 
     * the source element and thus reaching 
     * the destination element as the base case.
     */
    static int minCostPath(int x, int y, int xDest, int yDest, int cost[][]) 
    {
        if(x > xDest || y > yDest) 
        {
            return Integer.MAX_VALUE;
        }
        else if(x == xDest && y == yDest) 
        {
            return cost[xDest][yDest];
        }
        else 
        {
            int xCost = minCostPath(x+1, y, xDest, yDest, cost);
            int yCost = minCostPath(x, y+1, xDest, yDest, cost);
            int diagCost = minCostPath(x+1, y+1, xDest, yDest, cost);

            int newCost = cost[x][y] + Math.min(Math.min(xCost, yCost), diagCost);

            return newCost;
        }
    }
    /*
     * - DP(Tabulation) optimization:
     * This problem also has overlapping sub-problems.
     * Bottom-up approach is used to avoid repeated 
     * computations.
     * Tabulate minimum cost of reaching a cell from 
     * either its top, left, or left diagonal cell.
     */

    static int minCostPathTable(int xDest, int yDest, int cost[][])
    {
        int i, j;
        int cache[][]=new int[xDest+1][yDest+1];
 
        for (i = 0; i <= xDest; i++)
        {
            for (j = 0; j <= yDest; j++)
            {
                /*Initializing the cost of reaching the source element */
                if(i==0 && j==0)
                    cache[i][j] = cost[0][0];
                /* 
                 * Initialzing the first row as prefix sum of first row of cost
                 * as reaching every next element from previous would require 
                 * all preceeding elements to be summed.
                 */
                else if(i==0)
                    cache[i][j] = cache[0][j-1] + cost[0][j];
                /* 
                 * Initialzing the first column as prefix sum of first column of cost
                 * as reaching every next element from previous would require 
                 * all preceeding elements to be summed.
                 */
                else if(j==0)
                    cache[i][j] = cache[i-1][0] + cost[i][0];
                /*
                 * Tabulate minimum cost of reaching each remaining cell from 
                 * either its top, left, or left diagonal cell + the cost of cell itself.
                 */
                else
                    cache[i][j] = Math.min(Math.min(cache[i-1][j-1], cache[i-1][j]), cache[i][j-1]) + cost[i][j];
            }
        }
        return cache[xDest][yDest];
    }

    public static void main(String[] args) {
        int cost[][] = {
            {1,3,3},
            {1,8,2},
            {1,3,3}
        };
        // int m = 0, n = 0;
        int res = minCostPath(0, 0, 2, 2, cost);
        System.out.println(res);
        System.out.println(minCostPathTable(2, 2, cost));
    }
}
