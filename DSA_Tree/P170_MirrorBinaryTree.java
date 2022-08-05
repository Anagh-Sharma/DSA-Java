package DSA_Tree;

class BinaryTreeMirror<T>
{
    T data;
    BinaryTreeMirror<T> left;
    BinaryTreeMirror<T> right;
    BinaryTreeMirror(T data)
    {
        this.data = data;
        left = right = null;
    }
}

public class P170_MirrorBinaryTree {

    /*
     * Post order traversal of the tree with 
     * swapping of left and right child nodes 
     * at return time.
     */
    static void mirrorTree(BinaryTreeMirror<Integer> root)
    {
        /*
         * Base case:
         * - If the root is null:
         *   return from the function
         */
        if(root == null)
        {
            return;
        }

        /*
         * Recursive call:
         * - Recursive calls on left and
         *   right subtrees
         */
        mirrorTree(root.left);
        mirrorTree(root.right);

        /*
         * Return time computation:
         * - Starting from the leaf nodes, or from 
         *   the root node of the function on the 
         *   top of the call stack swap the node's 
         *   left and right child nodes.
         * - Recursively swap the left and child 
         *   nodes of the parent nodes.
         */
        BinaryTreeMirror<Integer> temp = root.left;
        root.left = root.right;
        root.right = temp;
    }
    
    /*
     * Recursively print the root nodes of all the left 
     * subtrees and then the right subtrees.
     */
    static void print(BinaryTreeMirror<Integer> root) {
        if(root == null || (root.left == null && root.right == null)) 
        {
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
    *                10
    *            /        \  
    *          20          30
    *        /    \       /   \
    *      40      50   60     70      
    */
    public static void main(String[] args) 
    {
        BinaryTreeMirror<Integer> root = new BinaryTreeMirror<Integer>(10);
        root.left = new BinaryTreeMirror<Integer>(20);
        root.right = new BinaryTreeMirror<Integer>(30);
        root.left.left = new BinaryTreeMirror<Integer>(40);
        root.left.right = new BinaryTreeMirror<Integer>(50);
        root.right.left = new BinaryTreeMirror<Integer>(60);
        root.right.right = new BinaryTreeMirror<Integer>(70);

        System.out.println("Original tree:");
        print(root);
        mirrorTree(root);

        System.out.println();

        System.out.println("Mirror of the tree:");
        print(root);

    }
}
