package DSA_Tree;

class BinaryTreeKDistance<T>
{
    T data;
    BinaryTreeKDistance<T> left;
    BinaryTreeKDistance<T> right;
    BinaryTreeKDistance(T data)
    {
        this.data = data;
        left = right = null;
    }
}

public class P153_PrintNodesKDistance {

    /*
     * Recursivly return the count of nodes for both the left and the
     * right subtree and add 1 to it. So, for every root node of each 
     * subtree, 1 is added.
     * Base case:
     * - If max level has been reached:
     *   - Print the node
     *   - return.
     * Recursive case:
     * - Recur on the left subtree.
     * - Recur on the right subtree.
     */
    static void printNodesLevel(BinaryTreeKDistance<Integer> root, int maxLevels) 
    {
        if(maxLevels == 0) 
        {
            System.out.print(root.data+", ");
            return;
        }            
        printNodesLevel(root.left, maxLevels - 1);
        printNodesLevel(root.right, maxLevels - 1);
    }
    
    /*
    *                10
    *            /        \  
    *          20          30
    *        /    \       /   \
    *      40      50   60     70
    *     /                      
    *   80                       
    */
    public static void main(String[] args) {
        BinaryTreeKDistance<Integer> root = new BinaryTreeKDistance<Integer>(10);
        root.left = new BinaryTreeKDistance<Integer>(20);
        root.right = new BinaryTreeKDistance<Integer>(30);
        root.left.left = new BinaryTreeKDistance<Integer>(40);
        root.left.right = new BinaryTreeKDistance<Integer>(50);
        root.right.left = new BinaryTreeKDistance<Integer>(60);
        root.right.right = new BinaryTreeKDistance<Integer>(70);
        root.left.left.left = new BinaryTreeKDistance<Integer>(80);
        int maxLevels = 2;
        System.out.println("The nodes of level are:");
        printNodesLevel(root, maxLevels);
    }  
}
