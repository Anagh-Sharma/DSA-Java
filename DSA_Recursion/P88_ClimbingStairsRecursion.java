package DSA_Recursion;

public class P88_ClimbingStairsRecursion {
    /*
     * Tree recursion:
     * Intuitively:
     * - If the first step is of size 1, then the rest of the steps are
     *   climbed from stair 1, and then n-1 stairs need to be climbed.
     * - If the first step is of size 2, then the rest of the steps are
     *   climbed from stair 2, and then n-2 stairs need to be climbed.
     * climbStairsRecursion(0, n) = climbStairsRecursion(1, n) + climbStairsRecursion(2, n)
     */
    static int climbStairsRecursion(int currStair, int stairs)
    {
        /*
         * Negative Base case:
         * When the current stair goes beyond the total number of stairs: return 0.
         */
        if(currStair > stairs)
            return 0;
        /*
         * Positive Base case:
         * When the current stair equals the total number of stairs: return 1.
         * This signifies that a unique way to climb all the stairs has been found.
         */
        else if(currStair == stairs)
            return 1;
        /*
         * Recursive case:
         * - Two recursive paths are taken:
         * - First when the step size is 1.
         * - Second when the step size is 2.
         */
        return climbStairsRecursion(currStair+1, stairs) + climbStairsRecursion(currStair+2, stairs); 
    }
    public static void main(String[] args) {
        int stairs = 5;
        int currStair = 0;
        System.out.println(climbStairsRecursion(currStair, stairs));
    }
}
