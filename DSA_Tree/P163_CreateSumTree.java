package DSA_Tree;

class BinaryTreeST<T>
{
    T data;
    BinaryTreeST<T> left;
    BinaryTreeST<T> right;
    BinaryTreeST(T data)
    {
        this.data = data;
        left = right = null;
    }
}

public class P163_CreateSumTree {
 
    /*
     * Recursively print the root nodes of all the left 
     * subtrees and then the right subtrees.
     */
    static void print(BinaryTreeST<Integer> root) {
        if(root == null) {
            return;
        }
        String output = "";
        System.out.println("Root : " + root.data);
        if(root.left != null) {
            output += "L : " + root.left.data;
        }
        if(root.right != null) {
            output += " R : " + root.right.data;
        }
        System.out.println(output);
        print(root.left);
        print(root.right);
    }

    /*
     * - Recursively traverse the tree.
     * - Store the data of the current node.
     * - Add the returned values from left and 
     *   right subtrees and update the data of 
     *   the current node with this sum.
     * - Return the sum of the stored and updated 
     *   data.
     */
    static int createSumTree(BinaryTreeST<Integer> root)
    {
        /*
         * Base case:
         * If node is null:
         * return 0.
         */
        if(root == null)
            return 0;

        /*Store data of the node. */
        int rootData = root.data;
        /*
         * Recursive case:
         * - Recursive calls on left and right subtrees.
         */

        /*
         * Update node data with the sum of values 
         * returned by the recursive calls.
         */
        root.data = createSumTree(root.left) + createSumTree(root.right);

        /*Return the sum of original and updated node data. */
        return root.data + rootData;
    }
    /*Given output */
    /*
    *               10
    *            /      \  
    *          -1         3
    *        /    \     /
    *       4      5  -2
    */
    
    /*Required output */
    /*
    *                9
    *            /      \  
    *           9        -2
    *        /    \     /
    *       0      0   0
    */
    public static void main(String[] args) 
    {
        BinaryTreeST<Integer> root = new BinaryTreeST<Integer>(10);
        root.left = new BinaryTreeST<Integer>(-1);
        root.right = new BinaryTreeST<Integer>(3);
        root.left.left = new BinaryTreeST<Integer>(4);
        root.left.right = new BinaryTreeST<Integer>(5);
        root.right.left = new BinaryTreeST<Integer>(-2);

        createSumTree(root);

        print(root);
    }
}
