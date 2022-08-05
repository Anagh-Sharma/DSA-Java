package DSA_Backtracking;

public class P99_WordSearch {
    /*The given board. */
    static char board[][] = {
        {'A','B','C','E'},
        {'S','F','C','S'},
        {'A','D','E','E'}
    };
    /*isValid: Boolean value altered while recursivly searching for a solution. */
    static boolean isValid = false;

    /*
     * Method searchWord: Iterate over all cells as possible starting indices for the word. 
     * This allows for backtracking as when an index fails as a first index, the
     * next is tried.
     */
    static boolean searchWord(String word) 
    {
        for(int i = 0; i < board.length; i++) 
        {
            for(int j = 0; j < board[0].length; j++) 
            {
                if(isMatch(i, j, word) == true) 
                {
                    return true;
                }
            }
        }
        return false;
    }

    /*
     * Recursive function: isMatch
     * Recursively check if the input string's first character
     * matches the character at the input string.
     */
    static boolean isMatch(int row, int col, String word) {
        /*
         * Base case: Return true as the entire word has been found.
         * 
         * When the length of the input word becomes 0.
         * The length of the word will reduce as recursive calls
         * will be given a substring of their input word when
         * their first character has been found.
         */
        if(word.length() == 0) 
        {
            return true;
        }

        /*
         * Return false when the current cell cannot possibly
         * hold the current input string's first character:
         * 
         * - When search has exceeded the size of the board.
         * - When the current cell's character is not equal
         *   to the first character of the input string.
         */
        if(row < 0 || col < 0 || row == board.length || col == board[0].length || board[row][col] != word.charAt(0)) 
        {
            return false;
        }
        
        /*
         * The current cell is marked with garbage value as its character
         * cannot also be used to validate any next character in the 
         * subsequent recursive calls. This cell is reset to the current
         * word's first character at the end of this recursive call for 
         * backtracking.
         */
        board[row][col] = '#';

        /*Directions as increments and decrements to an index. */
        int directions[][] = 
        {
            {0,1},  // right
            {1,0},  // down
            {0,-1}, // left
            {-1,0}  // top
        };

        /*
         * Recursive case:
         * Recursively check if any cell adjacent to the current cell 
         * at any direction can be equal to the next character, by:
         * - Iterating over all directions:
         *   - Recursive calls with:
         *     Altered cell indices using direction increments and decrements.
         *     Word substring removing the current word's first character.
         *   - If any recursive call returns true, then the next character 
         *     was found from the current index and their subsequent recursive 
         *     calls also found the subsequent characters as true can only be 
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
            isValid = isMatch(row + rowDirection, col + colDirection, word.substring(1));
            if(isValid == true) 
            {
                break;
            }
        }
        /*
         * If the next character is not found in the any adjacent cell
         * from the current cell then this cell is discarded as a part
         * of solution at this stage and the value needs to be unmarked 
         * and reset to its original value.
         */
        board[row][col] = word.charAt(0);
        /*
         * When true is returned it means a solution was found.
         * Else, backtracking occurs:
         * This method returns false to the method below it in the
         * call stack and this method is popped.
         * Now, the current method on top (which was the popped
         * method's calling function) attempts to find its next 
         * character in a different direction, and so on.
         * If the bottom most method call is returned false then,
         * the searchWord is returned false and a next cell is 
         * checked as a possible starting index.
         */
        return isValid;
    }

    public static void main(String[] args) {
        String str = "ABCCED";
        System.out.println(searchWord(str));
    }
}
