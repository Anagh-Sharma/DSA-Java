package DSA_Tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

class BinaryTreeDV<T>
{
    T data;
    BinaryTreeDV<T> left;
    BinaryTreeDV<T> right;
    BinaryTreeDV(T data)
    {
        this.data = data;
        left = right = null;
    }
}

public class P160_DiagonalViewTree {

    /*
     * A node's right child belongs to the node's diagonal 
     * while the left child belongs to the next diagonal.
     * All left child nodes of the nodes of a diagonal belong 
     * to the next diagonal.
     */
    static void diagonalViewRecursive(BinaryTreeDV<Integer> root, int diagonal, 
                                        HashMap<Integer, ArrayList<Integer>> diagonalNodeMap)
    {
        /*Base case: When the node is null, return from the function call. */
        if(root == null)
        {
            return;
        }

        /*
         * If the diagonal value is absent as the key in 
         * the map, it is created.
         */
        diagonalNodeMap.putIfAbsent(diagonal, new ArrayList<Integer>());
        /*The node data is mapped to the node's diagonal. */
        diagonalNodeMap.get(diagonal).add(root.data);

        /*
         * Recursive calls are made:
         * - The left child belongs to the next diagonal
         * - The right child belongs to the same diagonal
         */
        diagonalViewRecursive(root.left, diagonal + 1, diagonalNodeMap);
        diagonalViewRecursive(root.right, diagonal, diagonalNodeMap);
    }

    /*
     * A node's right child belongs to the node's diagonal 
     * while the left child belongs to the next diagonal.
     * All left child nodes of the nodes of a diagonal belong 
     * to the next diagonal.
     * 
     * Initially with only the the root node in the queue:
     * - Dequeue node, print the node and the subsequent right child nodes.
     * - The left nodes of the nodes are queued as they belong to the next 
     *   diagonal and the process is repeated.
     */
    static void diagonalView(BinaryTreeDV<Integer> root) 
    {
        LinkedList<BinaryTreeDV<Integer>> queue = new LinkedList<>();
        /*Initialize the queue with the root node. */
        queue.addLast(root);
        /*Iterate until the queue is empty, i.e., there is no next diagonal. */
        while(!queue.isEmpty()) 
        {
            /*
             * Store the length of the diagonal's root 
             * nodes.
             * The nodes themselves and their right child
             * nodes belong to the diagonal.
             * The left chid nodes belong to the next diagonal.
             */
            int diagonalRootsLength = queue.size();
            /*
             * Store the nodes of the diagonal:
             * - The queued left child nodes of the last 
             *   diagonal and their each subsequent right 
             *   child node.
             */
            ArrayList<Integer> diagonal = new ArrayList<>();

            for(int i=0; i<diagonalRootsLength; i++)
            {
                /*
                 * Store a root node of a diagonal.
                 * - The first diagonal has only the root 
                 *   node of the tree as a root node.
                 * - The next diagonal has the left child 
                 *   nodes of the last diagonal's nodes as 
                 *   root nodes.
                 */
                BinaryTreeDV<Integer> node = queue.removeFirst();
                /*
                 * Iterate until the root node is null.
                 * - The root nodes are updated to their right 
                 *   child nodes and the data is stored for the 
                 *   current diagonal.
                 * - The left child nodes are queued for the 
                 *   next diagonal's root node.
                 */
                while(node != null) 
                {
                    diagonal.add(node.data);
                    if(node.left != null) 
                    {
                        queue.addLast(node.left);
                    }
                    node = node.right;
                }
            }
            System.out.println(diagonal);
        }
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
    public static void main(String[] args) 
    {
        BinaryTreeDV<Integer> root = new BinaryTreeDV<Integer>(10);
        root.left = new BinaryTreeDV<Integer>(20);
        root.right = new BinaryTreeDV<Integer>(30);
        root.left.left = new BinaryTreeDV<Integer>(40);
        root.left.right = new BinaryTreeDV<Integer>(50);
        root.right.left = new BinaryTreeDV<Integer>(60);
        root.right.right = new BinaryTreeDV<Integer>(70);
        root.left.left.left = new BinaryTreeDV<Integer>(80);

        /*
         * The hashmap is passed and is updated in recursive 
         * calls to map the node to its diagonal.
         */
        HashMap<Integer, ArrayList<Integer>> diagonalNodeMap = new HashMap<>();

        System.out.println("Bottom View of the tree (Iterative solution): ");
        diagonalViewRecursive(root, 1, diagonalNodeMap);

        for(int i=1; i<=diagonalNodeMap.size(); i++)
            System.out.println(diagonalNodeMap.get(i));

        diagonalView(root);
    }
}
