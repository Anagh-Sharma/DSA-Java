package DSA_Tree;

class BinaryTreeCS<T>
{
    T data;
    BinaryTreeCS<T> left;
    BinaryTreeCS<T> right;
    BinaryTreeCS(T data)
    {
        this.data = data;
        left = right = null;
    }
}

public class P162_ChildrenSumProperty {

    static boolean checkChildrenSum(BinaryTreeCS<Integer> root)
    {
        /*
         * Base case:
         * - If the passed node is null.
         * - Or if the node is a leaf node.
         */
        if(root == null || (root.left == null && root.left == null))
            return true;

        /*
         * Recursive case:
         * - Return true if:
         * - The current root's data is equal to the
         *   sum of its children.
         * - The left and right subtrees
         */

        /*The left and right nodes's data are added if nodes exist. */
        int sum = 0;
        if(root.left != null)
            sum += root.left.data;
        
        if(root.right != null)
            sum += root.right.data;
 
        return (root.data == sum) && checkChildrenSum(root.left) && checkChildrenSum(root.right);
    }

    /*
    *               20
    *            /      \  
    *          8         12
    *        /    \     /   \
    *       3      5   7     5
    */
    public static void main(String[] args) 
    {
        BinaryTreeCS<Integer> root = new BinaryTreeCS<Integer>(20);
        root.left = new BinaryTreeCS<Integer>(8);
        root.right = new BinaryTreeCS<Integer>(12);
        root.left.left = new BinaryTreeCS<Integer>(3);
        root.left.right = new BinaryTreeCS<Integer>(5);
        root.right.left = new BinaryTreeCS<Integer>(7);
        root.right.right = new BinaryTreeCS<Integer>(5);

        System.out.println("Does the binary tree follow children sum property?  ");
        System.out.print(checkChildrenSum(root));
    }
}
