package DSA_Backtracking;

public class P100_RatInAMaze {
    /*The given board. */
    static int board[][] = {
        {1, 0, 0, 0},
        {1, 1, 0, 1},
        {0, 1, 0, 0},
        {1, 1, 1, 1}
    };

    /*isValid: Boolean value altered while recursivly searching for a solution. */
    static boolean isValid = false;

    /*
     * Method searchWord: Iterate over all cells as possible starting indices for the word. 
     * This allows for backtracking as when an index fails as a first index, the
     * next is tried.
     */
    static boolean searchPath() 
    {
        for(int i = 0; i < board.length; i++) 
        {
            for(int j = 0; j < board[0].length; j++) 
            {
                if(isPath(i, j) == true) 
                {
                    return true;
                }
            }
        }
        return false;
    }

    /*
     * Recursive function: isMatch
     * Recursively check if the cell has a 1.
     */
    static boolean isPath(int row, int col) {
        /*
         * Base case: 
         * Return true when the last cell has been reached.
         */
        if(row == board.length - 1 && col == board[0].length - 1) 
        {
            return true;
        }

        /*
         * Return false when the current cell cannot possibly
         * hold the current input string's first character:
         * 
         * - When search has exceeded the size of the board.
         * - When the current cell does not have 1.
         */
        if(row < 0 || col < 0 || row == board.length || col == board[0].length || board[row][col] != 1) 
        {
            return false;
        }
        
        /*
         * The current cell is marked with garbage value as its number
         * cannot also be used to validate any next steps in the 
         * subsequent recursive calls. This cell is reset to the current
         * word's first character at the end of this recursive call for 
         * backtracking.
         */
        board[row][col] = -1 ;

        /*Directions as increments and decrements to an index. */
        int directions[][] = 
        {
            {0,1},  // right
            {1,0}   // down
        };

        /*
         * Recursive case:
         * Recursively check if any cell adjacent to the current cell 
         * at any direction can be equal to 1, by:
         * - Iterating over all directions:
         *   - Recursive calls with:
         *     Altered cell indices using direction increments and decrements.
         *   - If any recursive call returns true, then the next cell with 1
         *     was found from the current index and their subsequent recursive 
         *     calls also found the subsequent 1s as true can only be 
         *     returned from the base case:
         *     - Break from the loop as a solution was found.
         *     - This true boolean value is returned 
         *     [When the recursive call at the bottom of the stack is returned
         *     true, then it means that the solution was found.]
         */
        for(int direction = 0; direction < directions.length; direction++) 
        {
            int rowDirection = directions[direction][0];
            int colDirection = directions[direction][1];
            isValid = isPath(row + rowDirection, col + colDirection);
            if(isValid == true) 
            {
                break;
            }
        }
        /*
         * If the next 1 is not found in the any adjacent cell
         * from the current cell then this cell is discarded as a part
         * of solution at this stage and the value needs to be unmarked 
         * and reset to its original value.
         */
        board[row][col] = 1;
        /*
         * When true is returned it means a solution was found.
         * Else, backtracking occurs:
         * This method returns false to the method below it in the
         * call stack and this method is popped.
         * Now, the current method on top (which was the popped
         * method's calling function) attempts to find its next 
         * 1 in a different direction, and so on.
         * If the bottom most method call is returned false then,
         * the searchWord is returned false and a next cell is 
         * checked as a possible starting index.
         */
        return isValid;
    }

    public static void main(String[] args) {
        System.out.println(searchPath());
    }
}
