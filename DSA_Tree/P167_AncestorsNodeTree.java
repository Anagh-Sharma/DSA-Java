package DSA_Tree;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

class BinaryTreeAT<T>
{
    T data;
    BinaryTreeAT<T> left;
    BinaryTreeAT<T> right;
    BinaryTreeAT(T data)
    {
        this.data = data;
        left = right = null;
    }
}

public class P167_AncestorsNodeTree {
    /*
     * Brute Force: -
     * Traverse the tree recursively.
     * - Append the current node's data
     * - If the current appended data 
     *   equals to the target, copy the 
     *   path to a global ArrayList and 
     *   no further recursive calls are made.
     */
    static ArrayList<Integer> foundPath = new ArrayList<>();
    static void ancestorsNode(BinaryTreeAT<Integer> root, int target, AtomicBoolean found, ArrayList<Integer> path)
    {
        /*
         * Base case:
         * - If the passed node is null, or 
         *   if the target has been found: 
         *   - No further recursive calls are 
         *     made and function returns.
         */
        if(root == null || found.get())
            return;
        
        /*
         * Create a new ArrayList to store the 
         * upto and from the current node.
         */
        ArrayList<Integer> nodePath = new ArrayList<>();

        /*Copy all the elements in the path to this element */
        nodePath.addAll(path);

        /*Append the element to the path */
        nodePath.add(root.data);

        /*
         * If the appended path is equal to target: 
         * - Store the path
         * - Update found so no recursive calls on the 
         *   right subtree are made.
         * - Return from the function and no recursive 
         *   calls are made.
         */
        if(root.data == target)
        {
            foundPath.addAll(nodePath);
            found.set(true);
            return;
        }

        /*Recursive call on the left subtree. */
        ancestorsNode(root.left, target, found, nodePath);

        /*
         * Recursive call on the left subtree if 
         * element not found in left subtree.
         */
        if(!found.get())
            ancestorsNode(root.right, target, found, nodePath);
    }

    /*
     * - Traverse the tree and return true:
     *   - If the node's data is equal to the 
     *     target.
     *   - At return time, append the node data.
     */
    static ArrayList<Integer> foundPathOptimize = new ArrayList<>();
    static boolean ancestorsNode(BinaryTreeAT<Integer> root, int target)
    {
        if(root == null)
            return false;
        
        if(root.data == target)
            return true;

        if(ancestorsNode(root.left, target) || ancestorsNode(root.right, target))
        {
            foundPathOptimize.add(root.data);
            return true;
        }
        return false;
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
        BinaryTreeAT<Integer> root = new BinaryTreeAT<Integer>(10);
        root.left = new BinaryTreeAT<Integer>(20);
        root.right = new BinaryTreeAT<Integer>(30);
        root.left.left = new BinaryTreeAT<Integer>(40);
        root.left.right = new BinaryTreeAT<Integer>(50);
        root.right.left = new BinaryTreeAT<Integer>(60);
        root.right.right = new BinaryTreeAT<Integer>(70);
        root.left.left.left = new BinaryTreeAT<Integer>(80);

        int target = 50;
        ancestorsNode(root, target, new AtomicBoolean(false), new ArrayList<Integer>());
        System.out.print(foundPath.isEmpty() ? "Not found!" : foundPath);

        if(ancestorsNode(root, target))
            foundPathOptimize.add(0, target);
        System.out.print(foundPathOptimize.isEmpty() ? "Not found!" : foundPathOptimize);

    }
}
