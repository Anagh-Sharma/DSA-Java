package DSA_Recursion;

import java.util.ArrayList;

public class P91_MazePathHorizVertiDiagonal {
    static ArrayList<String> getMazeDiag(int currentRow, int currentCol, int endRow, int endCol) 
    {
        /*
         * Positive Base case:
         * When the current position reaches the required position: 
         * return ArrayList with empty string. 
         * 
         * - This returned empty string will be used to convert the
         *   last "H" or "V" leading upto the successful reaching of 
         *   the goal to an ArrayList as a string.
         * - This ArrayList is then returned to the calling recursive
         *   function and the associated "H" or "V" is appended behind
         *   all returned strings in the ArrayList.
         */
        if(currentRow == endRow && currentCol == endCol) {
            ArrayList<String> temp = new ArrayList<>();
            temp.add("");
            return temp;
        }
        /*
         * Negative base case:
         * When the current position exceeds the required position: 
         * return ArrayList with no string.
         * 
         * - This means that the option upon whose selection the current
         *   position exceeded the required position will not be appended 
         *   to the string.
         */
        if(currentRow > endRow || currentCol > endCol) {
            ArrayList<String> temp = new ArrayList<>();
            return temp;
        }
        /*
         * Store all the suffixes whose addition leads to the required position.
         */
        ArrayList<String> result = new ArrayList<>();
        /*
         * Recursive case:
         * - Valid sequences produce the positive base case and thus, a non-empty
         *   string is returned which enables the storage of the option leading
         *   up to the positive base case at return time.
         * - Two recursive cases:
         * - Horizontal: First with the column incremented.
         * - Vertical: Second with the row incremented.
         * - Diagonal: Both column and row are incremented.
         */
        ArrayList<String> horizontalRes = getMazeDiag(currentRow, currentCol+1, endRow, endCol);
        for(String tempRes : horizontalRes) {
            result.add("H" + tempRes);
        }

        ArrayList<String> verticalRes = getMazeDiag(currentRow+1, currentCol, endRow, endCol);
        for(String tempRes : verticalRes) {
            result.add("V" + tempRes);
        }
        
        ArrayList<String> diagRes = getMazeDiag(currentRow+1, currentCol+1, endRow, endCol);
        for(String tempRes : diagRes) {
            result.add("D" + tempRes);
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(getMazeDiag(1, 1, 3, 3));
    }
}
