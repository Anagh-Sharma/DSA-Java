package DSA_Tree;

class BinaryTreeMaxElement<T>
{
    T data;
    BinaryTreeMaxElement<T> left;
    BinaryTreeMaxElement<T> right;
    BinaryTreeMaxElement(T data)
    {
        this.data = data;
        left = right = null;
    }
}

public class P154_MaxELementBinaryTree 
{
    /*
     * Recursivly return the node with the maximum value out of:
     * - The root node
     * - The left subtree
     * - The right subtree
     * Base case:
     * - If the root node has been reached:
     *   - return: 0.
     * Recursive case:
     * - Recur on the left subtree.
     * - Recur on the right subtree.
     */
    static int countNodesLevel(BinaryTreeMaxElement<Integer> root) 
    {
        if(root == null) 
        {
            return 0;
        }

        /*Save node data (Calling time) */
        int nodeElement = root.data;

        int leftElement = countNodesLevel(root.left);
        int rightElement = countNodesLevel(root.right);

        /*Compare left and right subtree returned data (Return time) */
        int maxChild = leftElement >= rightElement ? leftElement : rightElement;

        /*
         * Compare and return the maximum of the node 
         * data and the returned data (Return time).
         */
        return maxChild >= nodeElement ? maxChild : nodeElement;
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
        BinaryTreeMaxElement<Integer> root = new BinaryTreeMaxElement<Integer>(10);
        root.left = new BinaryTreeMaxElement<Integer>(20);
        root.right = new BinaryTreeMaxElement<Integer>(30);
        root.left.left = new BinaryTreeMaxElement<Integer>(40);
        root.left.right = new BinaryTreeMaxElement<Integer>(50);
        root.right.left = new BinaryTreeMaxElement<Integer>(60);
        root.right.right = new BinaryTreeMaxElement<Integer>(70);
        root.left.left.left = new BinaryTreeMaxElement<Integer>(80);
        int maxElement = countNodesLevel(root);
        System.out.println("The maximum element in the tree is: "+maxElement);
    }  
}
