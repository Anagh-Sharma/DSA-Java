package DSA_Tree;

import java.util.ArrayList;
import java.util.Collections;

class BinaryTreeBT<T>
{
    T data;
    BinaryTreeBT<T> left;
    BinaryTreeBT<T> right;
    BinaryTreeBT(T data)
    {
        this.data = data;
        left = right = null;
    }
}

/*
 * To traverse the boundary of a tree:
 * - Traverse the left boundary
 * - Traverse the leaves
 * - Traverse the right boundary
 */

public class P161_BoundaryViewTree {

    static void leftBoundaryView(BinaryTreeBT<Integer> root, ArrayList<Integer> leftBoundary)
    {
        /*
         * Base case:
         * - If root is null
         *   return null
         * - Unstated base case: leaf nodes
         */
        if(root == null)
        {
            return;
        }

        /*Add the node's data to passed ArrayList. */
        leftBoundary.add(root.data);

        /*
         * Recursive case:
         * - If the left child is not null, recur on it.
         */
        if(root.left != null)
            leftBoundaryView(root.left, leftBoundary);
        /*
         * Recursive case:
         * - Else if the right child is not null, recur on it.
         */
        else
            leftBoundaryView(root.right, leftBoundary);
    }
    
    static void treeLeavesRecursive(BinaryTreeBT<Integer> root, ArrayList<Integer> leaves)
    {
        if(root == null)
        {
            return;
        }
        else if(root.left == null && root.right == null)
        {
            leaves.add(root.data);
        }
        treeLeavesRecursive(root.left, leaves);
        treeLeavesRecursive(root.right, leaves);
    }

    static void rightBoundaryView(BinaryTreeBT<Integer> root, ArrayList<Integer> rightBoundary)
    {
        if(root == null || (root.right == null && root.left == null))
        {
            return;
        }

        rightBoundary.add(root.data);

        if(root.right != null)
            rightBoundaryView(root.right, rightBoundary);
        else
            rightBoundaryView(root.left, rightBoundary);
    }

    static ArrayList<Integer> clockwiseBoundaryView(BinaryTreeBT<Integer> root)
    {
        ArrayList<Integer> boundaryView = new ArrayList<>();
        ArrayList<Integer> leftBoundary = new ArrayList<>();
        ArrayList<Integer> rightBoundary = new ArrayList<>();
        ArrayList<Integer> leaves = new ArrayList<>();

        /*
         * Traverses tree from root to each subsequent 
         * left child node unless only right child is 
         * present and excludes the leaf node.
         */
        leftBoundaryView(root, leftBoundary);
        boundaryView.addAll(leftBoundary);

        /*Traverses all the leaves */
        treeLeavesRecursive(root, leaves);
        boundaryView.addAll(leaves);

        /*
         * Traverses tree from right child of the 
         * root to each subsequent left child node 
         * unless only right child is present and 
         * excludes the leaf node.
         */
        rightBoundaryView(root.right, rightBoundary);
        /*Reverse the right boundary for clockwise traversal. */
        Collections.reverse(rightBoundary);
        boundaryView.addAll(rightBoundary);

        return boundaryView;
    }

    static ArrayList<Integer> antiClockwiseBoundaryView(BinaryTreeBT<Integer> root)
    {
        ArrayList<Integer> boundaryView = new ArrayList<>();
        ArrayList<Integer> leftBoundary = new ArrayList<>();
        ArrayList<Integer> rightBoundary = new ArrayList<>();
        ArrayList<Integer> leaves = new ArrayList<>();

        // Traverse right boundary
        rightBoundaryView(root, rightBoundary);
        boundaryView.addAll(rightBoundary);

        // Traverse leaves
        treeLeavesRecursive(root, leaves);
        Collections.reverse(leaves);
        boundaryView.addAll(leaves);

        // Traverse left boundary
        leftBoundaryView(root.left, leftBoundary);
        Collections.reverse(leftBoundary);
        boundaryView.addAll(leftBoundary);

        return boundaryView;
    }

    /*
    *                10
    *            /        \  
    *          20          30
    *        /    \       /   \
    *      40      50   60     70
    *     /                      \
    *   80                        90
    */
    public static void main(String[] args) 
    {
        BinaryTreeBT<Integer> root = new BinaryTreeBT<Integer>(10);
        root.left = new BinaryTreeBT<Integer>(20);
        root.right = new BinaryTreeBT<Integer>(30);
        root.left.left = new BinaryTreeBT<Integer>(40);
        root.left.right = new BinaryTreeBT<Integer>(50);
        root.right.left = new BinaryTreeBT<Integer>(60);
        root.right.right = new BinaryTreeBT<Integer>(70);
        root.right.right.right = new BinaryTreeBT<Integer>(90);
        root.left.left.left = new BinaryTreeBT<Integer>(80);

        System.out.println("Clockwise boundary view of the tree : ");
        System.out.println(clockwiseBoundaryView(root));
        System.out.println("Anti-clockwise boundary view of the tree: ");
        System.out.println(antiClockwiseBoundaryView(root));
    }
}