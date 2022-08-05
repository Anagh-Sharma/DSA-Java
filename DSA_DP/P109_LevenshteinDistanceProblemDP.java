package DSA_DP;

public class P109_LevenshteinDistanceProblemDP {
    /*
     * Recursive solution:
     * - Defining a sub-problem:
     * - For string a and b, up to their last characters:
     * - If the last characters of the strings are same: The edit 
     *   distance of the strings is equal to the edit distance of 
     *   the remaining substrings of the strings.
     * - If the last characters of the strings are not same: The 
     *   edit distance of the strings is equal to: the edit distance 
     *   of the remaining substrings of the strings + the minimum cost 
     *   of removing, replacing or inserting the last character.
     * - The minimum will be checked using recursion. All options will 
     *   be selected recursively and the minimum value will be chosen.
     * - 2D-array D to store solutions to subproblems. 
     *   Size: length of a X length of b
     * - Array D[i][j] represents edit distance of the strings a and b 
     *   up to indices i and j of the strings respectively.
     */
    static int editDistanceRecursive(int aIdx, int bIdx, String a, String b)
    {
        /*
         * Base cases:
         * If a or b index is 0: the result is the remaining letters in the other string, 
         * those letters only need to be added to build the first string of 0 letters.
         */
        if(bIdx == 0)
            return aIdx+1;// 1 is added as the strings are indexed from 0
        else if(aIdx == 0)
            return bIdx+1;
        
        /*
         * Recursive case:
         * - If the string's letters at the current indices match:
         *   No operation needs to be performed, so nothing is added to the cost.
         *   Recursive operation checks the previous letters of the strings.
         */
        else if(a.charAt(aIdx) == b.charAt(bIdx))
        {
            return editDistanceRecursive(aIdx-1, bIdx-1, a, b);
        }
        /*
         * Recursive case:
         * - Else:
         *   The operation returning minimum cost recursively is added to the cost.
         */
        else
        {
            /*Replacing moves the indices to the next comparison, as these two are now same */
            int replace = 1 + editDistanceRecursive(aIdx-1, bIdx-1, a, b); 
            /* 
             * Insertion moves the move b's index the next spot, 
             * as a's current spot will again be compared. 
             */
            int insert = 1 + editDistanceRecursive(aIdx, bIdx-1, a, b);           
            /*Deletion moves the current a's index to the next element. */
            int delete = 1 + editDistanceRecursive(aIdx-1, bIdx, a, b);

            return Math.min(Math.min(replace, insert), delete);
        }
    }
    /*
     * DP (Memoization) optimization
     * - Applicable as the problem has overlapping subproblems.
     * - Caching the solutions to subproblems with different indices of strings.
     */
    static int editDistanceRecursiveMemo(int aIdx, int bIdx, String a, String b, int[][] cache)
    {
        /*
         * Base cases:
         * If a or b index is -1: the result is the remaining letters in the other string, 
         * those letters only need to be added to build the first string of 0 letters.
         */
        if(bIdx == 0)
            return aIdx+1;// 1 is added as the strings are indexed from 0
        else if(aIdx == 0)
            return bIdx+1;
        
        else if(cache[aIdx][bIdx] != 0)
            return cache[aIdx][bIdx];
        /*
         * Recursive case:
         * - If the string's letters at the current indices match:
         *   No operation needs to be performed, so nothing is added to the cost.
         *   Recursive operation checks the previous letters of the strings.
         */
        else if(a.charAt(aIdx) == b.charAt(bIdx))
        {
            cache[aIdx][bIdx] = editDistanceRecursiveMemo(aIdx-1, bIdx-1, a, b, cache);
            return cache[aIdx][bIdx];
        }
        /*
         * Recursive case:
         * - Else:
         *   The operation returning minimum cost recursively is added to the cost.
         */
        else
        {
            /*Replacing moves the indices to the next comparison, as these two are now same */
            int replace = 1 + editDistanceRecursiveMemo(aIdx-1, bIdx-1, a, b, cache); 
            /* 
             * Insertion moves the move b's index the next spot, 
             * as a's current spot will again be compared. 
             */
            int insert = 1 + editDistanceRecursiveMemo(aIdx, bIdx-1, a, b, cache);           
            /*Deletion moves the current a's index to the next element. */
            int delete = 1 + editDistanceRecursiveMemo(aIdx-1, bIdx, a, b, cache);

            cache[aIdx][bIdx] = Math.min(Math.min(replace, insert), delete);
            return cache[aIdx][bIdx];
        }
    }
    /*
     * DP (Tabulation) optimization
     * - Applicable as the problem has optimal substructure.
     * - Caching the solutions to subproblems with different index pairs of the strings.
     * - A cell when true, indicates that there exists a subset of the array within 
     *   the subset of the array ending at that index whose values add up to that 
     *   target, or remaining sum.
     */
    static int editDistanceRecursiveTable(String a, String b)
    {
        int[][] cache = new int[a.length()+1][b.length()+1];		
        /*
         * Base cases:
         * If a or b index is -1: the result is the remaining letters in the other string, 
         * those letters only need to be added to build the first string of 0 letters.
         */
		for(int i = 0; i <= a.length(); i++)
            cache[i][0] = i;		
		for(int j = 0; j <= b.length(); j++)
            cache[0][j] = j;
		
		// Applying the algorithm:
		for(int i = 1; i <= a.length(); i++) 
        {
			for(int j = 1; j <= b.length(); j++) 
            {
                /*
                 * - If the string's letters at the current indices match:
                 *   No operation needs to be performed, so nothing is added to the cost.
                 */
				if(a.charAt(i - 1) == (b.charAt(j - 1)))
                    cache[i][j] = cache[i - 1][j - 1];
				else 
                {
                    int replace = 1 + cache[i - 1][j - 1];
					int insert = 1 + cache[i][j - 1];
					int delete = 1 + cache[i - 1][j];	

					cache[i][j] = Math.min(Math.min(replace, insert), delete);
				}
			}
		}

        return cache[a.length()][b.length()];
    }

    public static void main(String[] args) 
    {
        String s1 = "intention";
        String s2 = "execution";
        int[][] cache = new int[s1.length()][s2.length()];
        System.out.println(editDistanceRecursive(s1.length()-1, s2.length()-1, s1, s2));
        System.out.println(editDistanceRecursiveMemo(s1.length()-1, s2.length()-1, s1, s2, cache));
        System.out.println(editDistanceRecursiveTable(s1, s2));

    }
}
/*
static int editDist(char[] c1, char[] c2) 
    {

        int[] temp = new int[128];
        for (char x : c1) {
        temp[x]++;
        }
        for (char x : c2) {
        temp[x]--;
        }
        int count = 0;
        for (int x : temp) {
        if (x < 0) {
        count += x;
        }
        }
        return Math.abs(count);
    }
*/
