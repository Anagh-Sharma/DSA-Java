package DSA_DP;

public class P105_LCS_DP {
    /*
     * Base case:
     * The lengths of the two strings are zero
     * The lengths become 0 as recursive calls pass
     * substrings after comparing the first characters
     * of the strings
     * 
     * Recursive case:
     * - If both the strings' first characters are same: 
     *   Increment count
     *   Recursive call with strings' first characters pruned.
     *   Call returns count that is added to this call's count.
     *   The returned count is the length of the suffix of the
     *   longest common sequence from the current index of both
     *   strings.
     * - Else If both the strings' first characters are not same: 
     *   Two recursive calls, each with a string's first character pruned.
     *   Calls return counts maximum of which is added to this call's count
     *   as the longest common subsequence is required and the two recursive
     *   calls return lengths of two different suffixes of two different
     *   sub-sequences, the longer of which is required.
     */
    static int lcs(String first, String second) {
        if(first.length() == 0 || second.length() == 0) {
            return 0;
        }
        int count = 0;
        if(first.charAt(0) == second.charAt(0)) {
            count = 1 + lcs(first.substring(1), second.substring(1));
        }

        else {
            int result1 = lcs(first.substring(1), second);
            int result2 = lcs(first, second.substring(1));
            count = Math.max(result1, result2);
        }
        return count;
    }
    /*
     * DP (Memoization) optimization:
     * - Top-down approach
     * - Recursively compare characters of the strings from end to start indices.
     * - Memorize the results of recursive calls made using different index pairs.
     * - The index pairs being compared can reoccur in the recursion tree and their 
     *   associated common longest subsequence' suffix's length can be cached and reused.
     * - Memorize the length of the maximum length common suffix (suffixes part
     *   of full common subsequence from a certain index pair) of each index pair 
     *   and reuse that length when the same index pair is encountered again.
     * - This applies the overlapping subproblem property of this problem
     *   as pairs of indices to be compared repeat over different branches of the
     *   recursion tree.
     */
    static int lcsMemo(String first, String second, int m, int n, int cache[][]) {
        // Base Case
        if(m == 0 || n == 0) {
            return 0;
        }

        // Memoization
        if(cache[m-1][n-1] != 0) {
            return cache[m-1][n-1];
        }

        if(first.charAt(m-1) == second.charAt(n-1)) 
        {
            cache[m-1][n-1] = 1 + lcsMemo(first, second, m-1, n-1, cache);
            return cache[m-1][n-1];
        }

        else 
        {
            cache[m-1][n-1] = Math.max(lcsMemo(first, second, m, n-1, cache), lcsMemo(first, second, m-1, n, cache));
        }
        return cache[m-1][n-1];
    }

    /*
     * DP (Tabulation) optimization:
     * - Bottom-up approach
     * - This is possible as the problem has optimal substructure
     *   and thus LCS from an index pair can be calculated from
     *   its preceding indices.
     */
    static int lcsTable(String first, String second, int m, int n) 
    {
        int matrix[][] = new int[m+1][n+1];
        for(int i = 0; i <= m; i++) 
        {
            for(int j = 0; j <= n; j++) 
            {
                if(i == 0 || j == 0) 
                {
                    matrix[i][j] = 0;
                }
                else 
                {
                    /*
                     * mat[i-1][j-1] holds the length of the subsequence
                     * until before indices i-1 and j-1 of the strings.
                     * If strings have same values until i-1 and j-1, then
                     * 1 is added to the value of mat[i-1][j-1] and stored
                     * at mat[i][j] so that if there was common prefix, its length
                     * is also added to the length of the subsequence until 
                     * i-1 and j-1.
                     * 
                     * Else if strings don't have same values at i-1 and j-1, then
                     * the length of the longest common subsequence prefix is copied 
                     * to mat[i][j] as this is the longest common subsequence until this
                     * point.
                     * 
                     * Now, question arises: What if in the first condition the preceeding
                     * terms being compared are same (So, 1 is being added), but what if the
                     * preceding term's preceding term is not same, then how the running
                     * sum until i-1 and j-1 will be computed (accounting for the length of
                     * the prefix before the compared i-1 and j-1). 
                     * This is not a problem as the when two numbers at i-1 and j-1 are not same 
                     * then, the length of their longest common prefix subsequence is stored:
                     * As the mat index pairs denote the length of common sequence in
                     * the string up to the respective indices, the larger value of
                     * the two values in the preceeding indices in both directions is stored.
                     */
                    if(first.charAt(i-1) == second.charAt(j-1)) 
                    {
                        matrix[i][j] = matrix[i-1][j-1] + 1;
                    }
                    else
                    {
                        matrix[i][j] = Math.max(matrix[i][j-1], matrix[i-1][j]);
                    }
                }
            }
        }
        return matrix[m][n];
    }

    public static void main(String[] args) {
        // String first = "abbg";
        // String second = "agbg";
        // int len = lcs(first, second);
        // System.out.println("Length is : " + len);

        String first1 = "qpqrr";
        String second2 = "pqprqrp";
        int m = first1.length();
        int n = second2.length();
        int cache[][] = new int[m+1][n+1];
        int res = lcsMemo(first1, second2, m, n, cache);
        System.out.println("Length is : "+ res);

        int res2 = lcsTable(first1, second2, m, n);
        System.out.println("Length is : "+ res2);
    }
}
