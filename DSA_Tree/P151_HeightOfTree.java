package DSA_Tree;

class BinaryTreeHeight<T>
{
    T data;
    BinaryTreeHeight<T> left;
    BinaryTreeHeight<T> right;
    BinaryTreeHeight(T data)
    {
        this.data = data;
        left = right = null;
    }
}

public class P151_HeightOfTree {
    /*
     * Recursivly return the maximum height of either the left or the
     * right subtree and add 1 to it. So, for every root node of each 
     * subtree, 1 is added.
     * Base case:
     * - If the node reference passed is null:
     *   return: zero.
     * Recursive case:
     * - Recur on the left subtree and store the returned value: left
     * - Recur on the right subtree and store the returned value: right
     * - return: 1 + Maximum of left and right
     */
    static int height(BinaryTreeHeight<Integer> root) 
    {
        if(root == null) 
        {
            return 0;
        }
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        return 1 + Math.max(leftHeight, rightHeight);
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
        BinaryTreeHeight<Integer> root = new BinaryTreeHeight<Integer>(10);
        root.left = new BinaryTreeHeight<Integer>(20);
        root.right = new BinaryTreeHeight<Integer>(30);
        root.left.left = new BinaryTreeHeight<Integer>(40);
        root.left.right = new BinaryTreeHeight<Integer>(50);
        root.right.left = new BinaryTreeHeight<Integer>(60);
        root.right.right = new BinaryTreeHeight<Integer>(70);
        root.left.left.left = new BinaryTreeHeight<Integer>(80);
        int h = height(root);
        System.out.println(h);
    }
}
