package DSA_Backtracking;

public class P98_Sudoku {
    /*The assigned board to be solved. */
    static int board[][] = {
        {5,3,0,0,7,0,0,0,0},
        {6,0,0,1,9,5,0,0,0},
        {0,9,8,0,0,0,0,6,0},
        {8,0,0,0,6,0,0,0,3},
        {4,0,0,8,0,3,0,0,1},
        {7,0,0,0,2,0,0,0,6},
        {0,6,0,0,0,0,2,8,0},
        {0,0,0,4,1,9,0,0,5},
        {0,0,0,0,8,0,0,7,9},
    };

    /*The number of maximum rows and columns in the board. */
    static int MAX_SIZE = 9;

    /*Recursive method to solve the problem. */
    static boolean solver(int row, int col) 
	{
        /*If the entire row has been traversed, move to the next. */
        if(col == MAX_SIZE) 
        {
            row =  row + 1;
            col = 0;
        }
        /*
         * Base case:
         * When all rows have been traversed, print the board.
         */
        if(row == MAX_SIZE) 
        {
            display();
            return true;
        }
        /*
         * Recursive case:
         * If the present cell is not 0:
         * Recursive call to the next element of the row.
         */
        if(board[row][col] != 0) 
        {
            return solver(row, col+1);
        }
        /*
         * Recursive call:
         * - Iterate over all possible values from 1 to 9 
         *   to be put into the empty cell.
         * - If a number is valid for a cell:
         *   - Put the number into the empty cell
         *   - Recursively check if the input leads to a solution
         *     [In the recursive call's parameters the column is inremented]
         *   - Else, check for the next number.
         */
        for(int i = 1; i <= MAX_SIZE; i++) 
        {
            if(isSafe(row, col, i)) 
            {
                board[row][col] = i;
                boolean res = solver(row, col+1);
                if(res) 
                {
                    return true;
                }
                board[row][col] = 0;
            }
        }

        return false;
    }

    static boolean isPresentInRow(int row, int data) {
        for(int col = 0; col < board.length; col++) {
            if(board[row][col] == data) {
                return true;
            }
        }
        return false;
    }

    static boolean isPresentInCol(int col, int data) {
        for(int row = 0; row < board.length; row++) {
            if(board[row][col] == data) {
                return true;
            }
        }
        return false;
    }

    static boolean isPresentInGrid(int row, int col, int data) {
        int r = row - row % 3;
        int c = col - col % 3;
        for(int i = r; i < r + 3; i++) {
            for(int j = c; j < c + 3; j++) {
                if(board[i][j] == data) {
                    return true;
                }
            }
        }
        return false;
    }

    static boolean isSafe(int row, int col, int data) {
        return !isPresentInRow(row, data) && !isPresentInCol(col, data) && !isPresentInGrid(row, col, data);
    }

    static void display() {
        for(int i = 0; i < MAX_SIZE; i++) {
            for(int j = 0; j < MAX_SIZE; j++) {
                System.out.print(board[i][j] + ",");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        String res = solver(0, 0) ? "Solved" : "Not Solved..";
        System.out.println(res);
    }

}
