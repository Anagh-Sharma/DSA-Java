package DSA_Tree;

import java.util.concurrent.atomic.AtomicBoolean;

class BinaryTreeCBT<T>
{
    T data;
    BinaryTreeCBT<T> left;
    BinaryTreeCBT<T> right;
    BinaryTreeCBT(T data)
    {
        this.data = data;
        left = right = null;
    }
}

public class P165_CheckBalancedTree {

    static int height(BinaryTreeCBT<Integer> root) 
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
     * - Pre-order traversal:
     * - First check if the current node satisfies the 
     *   condition by:
     *   - Recursively calculate the heights of the left 
     *     and right subtrees.
     *   - Calculate the absolute difference between the 
     *     heights.
     *   - If the difference does not exceed 1, then the 
     *     node satisfies the condition.
     * - Second, make recursive calls on left and right 
     *   subtrees to check if their root nodes also satisfy 
     *   the condition.
     * - Recursively the process is repeated until leaf nodes 
     *   are reached. This base case returns true as all leaves 
     *   are considered to satisfy the condition.
     * - This is pre-order traversal as it first validates the 
     *   root nodes then, the left and right subtrees.
     */
    /*
     * Time complexity:
     * O(N^2)
     * - The calculation of height takes O(N) since all N nodes are
     *   parsed.
     * - The recursive call on subtrees re-calculates the heights for 
     *   the subtrees.
     */
    static boolean checkBalancedTree(BinaryTreeCBT<Integer> root)
    {
        if(root == null)
            return true;

        int difference = height(root.left) - height(root.right);
        
        return (Math.abs(difference) <= 1) && checkBalancedTree(root.left) && checkBalancedTree(root.right);
    }

    /*
     * - Post-order traversal to calculate the height of 
     *   the tree and simultaneously update a boolean flag 
     *   when a node does not satisfy the condition.
     *   - First, recursively call the function on the 
     *     left subtree.
     *   - Second, recursively call the function on the 
     *     right subtree.
     *   - If the difference of heights at a node exceeds 1, 
     *     update flag to false.
     * - This is post-order traversal as it first validates the 
     *   the left and right subtrees and then the root node.
     * 
     * - The pre-order traversal first validated the root 
     *   node for w.r.t. the condition by calculating 
     *   the heights of left and right subtrees.
     * - Then, validated the left and right subtrees and thus 
     *   recalculating the heights again.
     * - This post order traversal optimizes this process by 
     *   only calculating the height once and simultaneously 
     *   checking the condition.
     */
    /*
     * Time complexity:
     * - O(N)
     * - The N nodes are parsed only once.
     */
    static int checkBalancedTreeOptimized(BinaryTreeCBT<Integer> root, AtomicBoolean isBalanced)
    {
        /*
         * Base case:
         * - If the root is null:
         *   return 0
         * - Or if the boolean value has been changed to 
         *   false in a previous recursive call then, no 
         *   further recursive calls are needed:
         *   return 0
         */
        if(root == null || isBalanced.get() == false) 
        {
            return 0;
        }

        /*
         * Recursive case:
         * - Recur on the left subtree
         * - Then, recur on the right subtree only 
         *   when the left subtree did not update 
         *   the booloean value to false.
         */
        int leftHeight = checkBalancedTreeOptimized(root.left, isBalanced);
        int rightHeight = 0;
        if(isBalanced.get() == true)
            rightHeight = checkBalancedTreeOptimized(root.right, isBalanced);
        
        /*
         * If the heights of the left and right 
         * subtrees exceed 1, update the boolean 
         * value to false.
         * This indicates that the subtree with the 
         * current root node is not balanced and hence 
         * the tree is not balanced.  
         */
        if(Math.abs(leftHeight - rightHeight) > 1)
            isBalanced.set(false);

        /*Return the height of the tree. */
        return 1 + Math.max(leftHeight, rightHeight);
    }

    /*
     * Post order traversal approach 2:
     * - If a node does not satisfy the condition, i.e.,
     *   the difference of the heights of its left and 
     *   right subtrees exceeds 1, then:
     *   - Return -1.
     * - If a recursive call to left or right subtree 
     *   returns -1 then the subtree is not balanced and 
     *   as result the entire tree is not balanced, then:
     *   - Return -1.
     * - Else when the difference of the subtrees' heights
     *   does not exceed 1 then the height is returned. The 
     *   returned height value in thos case cannot be -1 as 
     *   absolute value is returned. 
     */

    static int checkBalancedTreeOptimized2(BinaryTreeCBT<Integer> root)
    {
        if(root == null) 
        {
            return 0;
        }

        int leftHeight = checkBalancedTreeOptimized2(root.left);
        if(leftHeight == -1)
            return leftHeight;

        int rightHeight = checkBalancedTreeOptimized2(root.right);
        if(rightHeight == -1)
            return rightHeight;
        
        if(Math.abs(leftHeight - rightHeight) > 1)
            return -1;

        else
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
        BinaryTreeCBT<Integer> root = new BinaryTreeCBT<Integer>(10);
        root.left = new BinaryTreeCBT<Integer>(20);
        root.right = new BinaryTreeCBT<Integer>(30);
        root.left.left = new BinaryTreeCBT<Integer>(40);
        root.left.right = new BinaryTreeCBT<Integer>(50);
        root.right.left = new BinaryTreeCBT<Integer>(60);
        root.right.right = new BinaryTreeCBT<Integer>(70);
        root.left.left.left = new BinaryTreeCBT<Integer>(80);

        System.out.println("Is the tree balanced? ");

        System.out.println("Recursive approach:");
        System.out.println(checkBalancedTree(root));

        System.out.println("Optimized recursive approach 1:");
        AtomicBoolean isBalanced = new AtomicBoolean(true);
        checkBalancedTreeOptimized(root, isBalanced);
        System.out.println(isBalanced.get());

        System.out.println("Optimized recursive approach 2:");
        System.out.println(checkBalancedTreeOptimized2(root) == -1 ? false : true);


    }
}
