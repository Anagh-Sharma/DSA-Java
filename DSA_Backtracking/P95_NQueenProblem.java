package DSA_Backtracking;

public class P95_NQueenProblem {
    static int getCount(boolean[][] board) 
    {
        int countDown = 0;
        for(int row = 0; row < board.length; row++) 
        {
            for(int col = 0; col < board.length; col++) 
            {
                if(board[row][col]) 
                {
                    System.out.println(board[row][col] + "\t" + row + "," + col);
                    countDown++;
                }
            }
        }
        return countDown;
    }

    static int countWays(boolean [][]board, int currentRow) {
        /*
         * Initialize a count variable that is incremented in 
         * the base case when all the rows have been reached.
         */
        int count = 0;
        /*
         * Base case:
         * When all rows have been reached.
         * Call function on the board that prints all cells with
         * true values indicating there is a queen there and an
         * integer indicating how many such true values exist
         * indicating the number of queens placed.
         */
        if(currentRow == board.length) {
            System.out.println("Total Queen Placed : " + getCount(board));
            return 1;
        }
        /*
         * Recursive case:
         * - Iterate through all the possible columns or row indices
         *   for the current row.
         * - If the cell is not attacked:
         *   - Set the cell to: true
         *   - Recursively call with the next row and add the returned value to this 
         *     recursive call’s count variable.
         *     The returned value of the recursive is only 1 when:
         *     All rows have been traversed.
         *   - If the next row's recursive call is never able to 
         *     enter If statement with any iteration of the columns:
         *     - Set this cell as false and try another column's cell
         *     [Backtracking]
         *   - If the next row's recursive call is able to 
         *     enter If statement with an iteration of the columns
         *     or even finds the solution (Which will be printed
         *     int the Base case):
         *     - Still set this cell as false and try another column's cell
         *       to try other solutions.
         * - Else If:
         *     - Count zero is returned to the previous call indicating that 
         *       when the current column’s cell is set to true, then, a solution 
         *       is not found and the next column value is tried in that recursive 
         *       call’s iteration.
         */
        for(int col = 0; col < board[currentRow].length; col++) 
        {
            if(isSafeArea(board, currentRow, col)) 
            {
                board[currentRow][col] = true;
                count = count + countWays(board, currentRow + 1);
                /*
                 * Backtracking purpose:
                 * - Check next column as a possible solution component 
                 *   when the current fails.
                 * - Check next column as a next possible solution when
                 *   the current succeeds.
                 */
                board[currentRow][col] = false;
            }
        }
        /*
         * Count value returns:
         * Total number of times the Base case was reached
         * with a different cell of this recursive call's row
         * set to true.
         */
        return count;
    }

    static boolean isSafeArea(boolean[][] board, int row, int col) {
        // checking if the there is a queen in same column
        for(int i = row; i >= 0; i--) {
            if(board[i][col]) {
                return false;
            }
        }

        // check in upper left diagonal
        for(int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if(board[i][j]) {
                return false;
            }
        }

        // check in upper right diagonal
        for(int i = row, j = col; i >= 0 && j < board.length; i--, j++) {
            if(board[i][j]) {
                return false;
            }
        }
        
        return true;
    }

    public static void main(String[] args) {
        boolean[][] board = new boolean[4][4];
        System.out.println(countWays(board, 0));
    }
}
