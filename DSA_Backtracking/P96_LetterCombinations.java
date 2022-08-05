package DSA_Backtracking;

import java.util.ArrayList;

public class P96_LetterCombinations {
    static void letterCombination(int index, String digits, String path)
    {
        char[][] digitHash = {{},{},{'a','b','c'},{'d','e','f'},{'g','h','i'},{'j','k','l'}, {'m','n','o'},{'p','q','r','s'},{'t','u','v'},{'w','x','y','z'}};
        if(path.length() == digits.length())
        {
            System.out.println(path);
            return;
        }
        int digitHashIndex = Character.getNumericValue(digits.charAt(index));

        for(int i=0; i<digitHash[digitHashIndex].length; i++)
        {
            System.out.println("State-space tree level: "+(index+1));
            letterCombination(index+1, digits, path + digitHash[digitHashIndex][i]);
            
        }
    }

    static ArrayList<String> letterCombinationStore(int index, String digits, String path)
    {
        /*digitHash: Hash map to store all characters corresponding to numbers, using indices as numbers. */
        char[][] digitHash = {{},{},{'a','b','c'},{'d','e','f'},{'g','h','i'},{'j','k','l'}, {'m','n','o'},{'p','q','r','s'},{'t','u','v'},{'w','x','y','z'}};
        /*
         * Base case:
         * - When the length of the path becomes equal to the length
         *   of the input digits string, then the space state tree
         *   has reached one of its leaves and a path has been traversed.
         * - Store this path in an ArrayList of String and return this
         *   ArrayList.
         */
        if(path.length() == digits.length())
        {
            ArrayList<String> fullPath = new ArrayList<>();
            fullPath.add(path);
            System.out.println(path);
            return fullPath;
        }
        /*
         * nodePaths: Store all paths from the current node.
         * 
         * - All the base cases will return ArrayLists of a single string
         *   containing a full path. 
         * - These ArrayLists will be stored in the previous recursive call's
         *   nodePaths ArrayList object and returned to parent recursive call.
         * - Thus, all base case ArrayLists will trickle upwards, getting 
         *   accumulated and at the end reaching the root node.
         */
        ArrayList<String> nodePaths = new ArrayList<>();

        int digitHashIndex = Character.getNumericValue(digits.charAt(index));

        /*
         * Recursive case:
         * Iterate through all of a digit's associated characters:
         * - Append the character to the path.
         * - Recursively call the method with this appended path and incremented
         *   index(to move to the next digit of digits).
         * - Copy the strings of the returned ArrayList to the current
         *   recursive call's nodePaths. This nodePaths object will be 
         *   returned after all of this digit's characters or child
         *   nodes have been iterated through.
         */
        for(int i=0; i<digitHash[digitHashIndex].length; i++)
        {
            nodePaths.addAll(letterCombinationStore(index+1, digits, path + digitHash[digitHashIndex][i]));
        }
        return nodePaths;
    }
    public static void main(String[] args) {
        String digits = "23";
        int index = 0;
        String path = "";
        letterCombination(index, digits, path);
        System.out.println(letterCombinationStore(index, digits, path));
    }
}
