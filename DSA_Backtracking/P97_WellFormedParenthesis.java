package DSA_Backtracking;

import java.util.ArrayList;

public class P97_WellFormedParenthesis {
    /*
     * Recursive function parameters:
     * - Array of String parentheses: Current string
     * - int open: All open parentheses
     * - int close: All closed parenthesis
     * - int n: total number of parenthesis pairs required
     * 
     * - Base case:
     * - When close == n: print the string
     * 
     * - Recursive case:
     * - If open > close:
     * - Recursive call with: Append a closing bracket to the string and increment close count
     * - If open < n:
     * - Recursive call with: Append an opening bracket to the string and increment open count
     * - Note: The various valid permutations will be produced as recursive calls will enter 
     *   both the above If clauses, thus creating multiple branches in the space state tree at 
     *   a node.
     */
    static ArrayList<String> wellFormedParenthesis(String bracketSeq, int open, int close, int totalPairs)
    {
        ArrayList<String> nodeResult = new ArrayList<>();
        /*When all pairs have been made. */
        if(close == totalPairs)
        {
            ArrayList<String> uniqueBracketSeq = new ArrayList<>();
            uniqueBracketSeq.add(bracketSeq);
            return uniqueBracketSeq;
        }

        else
        {
            /*
             * When the closed brackets are less than opened ones:
             * Then the brackets need to be closed.
             * Make recursive call with adjusted parameters.
             */
            if(close<open)
            {
                nodeResult.addAll(wellFormedParenthesis(bracketSeq + " ) ", open, close+1, totalPairs));
            }
            /*
             * When the opened brackets are less than required pairs:
             * Then more brackets can be opened.
             * Make recursive call with adjusted parameters.
             */
            if(open<totalPairs)
            {
                nodeResult.addAll(wellFormedParenthesis(bracketSeq + " ( ", open+1, close, totalPairs));
            }
        }

        return nodeResult;
    }
    public static void main(String[] args) {
        int totalPairs = 2;
        int open = 0;
        int close = 0;
        String bracketSeq = "";
        System.out.println(wellFormedParenthesis(bracketSeq, open, close, totalPairs));
    }
}
