package DSA_Tree;

class BinaryTreeNodeCount<T>
{
    T data;
    BinaryTreeNodeCount<T> left;
    BinaryTreeNodeCount<T> right;
    BinaryTreeNodeCount(T data)
    {
        this.data = data;
        left = right = null;
    }
}

public class P152_CountNodesTree {
    /*
     * Recursivly return the count of nodes for both the left and the
     * right subtree and add 1 to it. So, for every root node of each 
     * subtree, 1 is added.
     * Base case:
     * - If the node reference passed is null:
     *   return: zero.
     * Recursive case:
     * - Recur on the left subtree and store the returned value: left
     * - Recur on the right subtree and store the returned value: right
     * - return: 1 + left + right
     */
    static int countNodes(BinaryTreeNodeCount<Integer> root) 
    {
        if(root == null) 
        {
            return 0;
        }
        int leftCount = countNodes(root.left);
        int rightCount = countNodes(root.right);
        return 1 + leftCount + rightCount;
    }

    /*
     * Recursivly return the count of nodes for both the left and the
     * right subtree and add 1 to it. So, for every root node of each 
     * subtree, 1 is added.
     * Base case:
     * - If the node reference passed is null or if the max level has
     *   been reached:
     *   return: zero.
     * Recursive case:
     * - Recur on the left subtree and store the returned value: left
     * - Recur on the right subtree and store the returned value: right
     * - return: 1 + left + right
     */
    static int countNodesLevel(BinaryTreeNodeCount<Integer> root, int maxLevels) 
    {
        if(root == null || maxLevels < 0) 
        {
            return 0;
        }
        int leftCount = countNodesLevel(root.left, maxLevels - 1);
        int rightCount = countNodesLevel(root.right, maxLevels - 1);
        return 1 + leftCount + rightCount;
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
        BinaryTreeNodeCount<Integer> root = new BinaryTreeNodeCount<Integer>(10);
        root.left = new BinaryTreeNodeCount<Integer>(20);
        root.right = new BinaryTreeNodeCount<Integer>(30);
        root.left.left = new BinaryTreeNodeCount<Integer>(40);
        root.left.right = new BinaryTreeNodeCount<Integer>(50);
        root.right.left = new BinaryTreeNodeCount<Integer>(60);
        root.right.right = new BinaryTreeNodeCount<Integer>(70);
        root.left.left.left = new BinaryTreeNodeCount<Integer>(80);
        int h = countNodes(root);
        System.out.println(h);
    }
}
