package DSA_Tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

class BinaryTreeBV<T>
{
    T data;
    BinaryTreeBV<T> left;
    BinaryTreeBV<T> right;
    BinaryTreeBV(T data)
    {
        this.data = data;
        left = right = null;
    }
}

/*Pair a node with its relative distance from the root of the tree. */
class nodeDistancePairBV
{
    BinaryTreeBV<Integer> node = null;
    int distance = 0;
    nodeDistancePairBV(BinaryTreeBV<Integer> node, int distance) 
    {
        this.node = node;
        this.distance = distance;
    }
}

public class P158_BottomViewTree {

    /*
     * - Parse a tree in level order and map a node to 
     *   its relative distance from the tree’s root node.
     * - Bottom view is the set of nodes that are of the 
     *   lowest level of a relative distance from the 
     *   tree’s root node.
     * - Since, the parsing of the tree is done in level 
     *   order, therefore the last node to be mapped to 
     *   a relative distance is the node of the lowest 
     *   level mapped to that relative distance.
     */
    static ArrayList<Integer> bottomViewIterative(BinaryTreeBV<Integer> root) 
	{
        /*
         * Queue stores nodeDistancePairBV objects containing 
         * nodes paired with their relative distance from 
         * the root of the tree.
         */
        LinkedList<nodeDistancePairBV> queue = new LinkedList<>();

        /*Map the nodes to distance values measured relative to the root node of the tree. */
        HashMap<Integer, List<Integer>> verticalViewMap = new HashMap<>();

        /*Store the values from the key-value map after all the nodes have been parsed. */
        ArrayList<Integer> ans = new ArrayList<>();

        /*
         * The queue is initialized with the root node 
         * of the tree and its distance from itself is 
         * naturally 0.
         */
        queue.addLast(new nodeDistancePairBV(root, 0));

        /*Iterate until the queue is not empty. */
        while(!queue.isEmpty()) 
		{
            /*Store the length of the queue. */
            int size = queue.size();
            /*Iterate over the length of the queue */
            for(int i=0; i<size; i++) 
			{
                /*Store the first element from the queue. */
                nodeDistancePairBV currNodeDistPair = queue.removeFirst();
                BinaryTreeBV<Integer> currNode = currNodeDistPair.node;
                int distance = currNodeDistPair.distance;

                /*
                 * To map the current node with its relative distance make 
                 * the key for its relative distance if it is not already 
                 * present (and map it to an empty ArrayList).
                 */
                verticalViewMap.putIfAbsent(distance, new ArrayList<>());
                /*
                 * Now, put the data of the node to the node's distance 
                 * key's value which is an ArrayList.
                 */
                verticalViewMap.get(distance).add(currNode.data);

                /*
                 * Queue the left and right child nodes of the node 
                 * by making their nodeDistancePairBV objects.
                 * - The left node's object is made with the current 
                 *   node's distance decremented.
                 * - The right node's object is made with the current 
                 *   node's distance incremented.           
                 */

                /*Queue the left child of the current node. */
                if(currNode.left != null) 
                {
                    nodeDistancePairBV leftChild = new nodeDistancePairBV(currNode.left, distance - 1);
                    queue.addLast(leftChild);
                }
                /*Queue the right child of the current node. */
                if(currNode.right != null) {
                    nodeDistancePairBV rightChild = new nodeDistancePairBV(currNode.right, distance + 1);
                    queue.addLast(rightChild);
                }
            }
        }
        /*Store the last node of each relative distance. */
        for(int distance : verticalViewMap.keySet()) 
        {
            ans.add(verticalViewMap.get(distance).get(verticalViewMap.get(distance).size() - 1));
        }

        return ans;
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
        BinaryTreeBV<Integer> root = new BinaryTreeBV<Integer>(10);
        root.left = new BinaryTreeBV<Integer>(20);
        root.right = new BinaryTreeBV<Integer>(30);
        root.left.left = new BinaryTreeBV<Integer>(40);
        root.left.right = new BinaryTreeBV<Integer>(50);
        root.right.left = new BinaryTreeBV<Integer>(60);
        root.right.right = new BinaryTreeBV<Integer>(70);
        root.left.left.left = new BinaryTreeBV<Integer>(80);

        System.out.println("Bottom View of the tree (Iterative solution): ");
        System.out.println(bottomViewIterative(root));
    }
}
