package DSA_Tree;

class BinaryTreeDM<T>
{
    T data;
    BinaryTreeDM<T> left;
    BinaryTreeDM<T> right;
    BinaryTreeDM(T data)
    {
        this.data = data;
        left = right = null;
    }
}

public class P166_DiameterOfTree {

    /*
     * - The diameter of the tree is the longest
     *   path between any two nodes of the tree.
     * - The highest level node in the path can 
     *   be used to find such a path by calculating 
     *   the sum of the height of its left and right 
     *   subtrees as the height of a tree is the the 
     *   longest path to the leaf node.
     * - Thus, every node can be considered as the 
     *   highest level node of its path and thus the sum 
     *   of the heights of the left and right subtrees of 
     *   each is calculated and the algorithm maximizes this 
     *   sum value.
     */
    static int maxPathLength = Integer.MIN_VALUE;

    static int diameterTree(BinaryTreeDM<Integer> root)
    {
        if(root == null)
            return 0;
        
        /*Calculate the height  */
        int leftSubtreeHeight = diameterTree(root.left);
        int rightSubtreeHeight = diameterTree(root.right);

        if((leftSubtreeHeight + rightSubtreeHeight) > maxPathLength)
            maxPathLength = leftSubtreeHeight + rightSubtreeHeight;
        
        return 1 + Math.max(leftSubtreeHeight, rightSubtreeHeight);
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
        BinaryTreeDM<Integer> root = new BinaryTreeDM<Integer>(10);
        root.left = new BinaryTreeDM<Integer>(20);
        root.right = new BinaryTreeDM<Integer>(30);
        root.left.left = new BinaryTreeDM<Integer>(40);
        root.left.right = new BinaryTreeDM<Integer>(50);
        root.right.left = new BinaryTreeDM<Integer>(60);
        root.right.right = new BinaryTreeDM<Integer>(70);
        root.left.left.left = new BinaryTreeDM<Integer>(80);

        System.out.println("Diameter of the tree: ");
        diameterTree(root);
        System.out.print(maxPathLength);
    }
}
