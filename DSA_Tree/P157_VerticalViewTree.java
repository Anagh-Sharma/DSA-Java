package DSA_Tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/*
 * Using preorder traversal of tree:
 * - Map each node to a key or distance.
 * - Initialize by mapping root node to key 0.
 * - The key is the horizontal distance from 
 *   root node that is at distance 0.
 * - Thereafter, map:
 *   - The left child node to root key - 1.
 *   - The right child node to root key + 1.
 */
class BinaryTreeNodeVerticalView<T>
{
    T data;
    BinaryTreeNodeVerticalView<T> left;
    BinaryTreeNodeVerticalView<T> right;
    BinaryTreeNodeVerticalView(T data)
    {
        this.data = data;
        left = right = null;
    }
}

/*Pair a node with its relative distance from the root of the tree. */
class nodeDistancePair 
{
    BinaryTreeNodeVerticalView<Integer> node = null;
    int distance = 0;
    nodeDistancePair(BinaryTreeNodeVerticalView<Integer> node, int distance) 
    {
        this.node = node;
        this.distance = distance;
    }
}

public class P157_VerticalViewTree {
    static void vericalViewRecursive(BinaryTreeNodeVerticalView<Integer> root, int distance, HashMap<Integer, ArrayList<Integer>> verticalViewMap) 
    {
        /*
         * Base case:
         * When the root holds a null reference, 
         * no recursive call is left to be made.
         */
        if(root == null) 
        {
            return;
        }
        /*
         * If the map passed to the recursive call already 
         * holds a key or distance value equal to the distance 
         * of the current root node, then the current root node 
         * is added to the value list.
         */
        else if(verticalViewMap.containsKey(distance))
        {
            ArrayList<Integer> map = verticalViewMap.get(distance);
            map.add(root.data);
            verticalViewMap.put(distance, map);
        }
        /*
         * Else If the map passed to the recursive call does not 
         * hold a key or distance value equal to the distance 
         * of the current root node, then a new list is made,  
         * the current root node is added to the list, and the list 
         * with the current node's distance as key is added to the 
         * map.
         */
        else
        {
            ArrayList<Integer> map = new ArrayList<>();
            map.add(root.data);
            verticalViewMap.put(distance, map);
        }
        /*
         * Recursive calls are made:
         * - Recursive call on the left subtree with the distance decremented
         * - Recursive call on the right subtree with the distance incremented
         */
        vericalViewRecursive(root.left, distance - 1, verticalViewMap);
        vericalViewRecursive(root.right, distance + 1, verticalViewMap);
    }

    /*
     * - Parse a tree in level order and map a node to 
     *   its relative distance from the tree’s root node.
     * 
     * - Create a class (nodeDistancePair) whose objects 
     *   pair nodes with their relative distances from 
     *   the tree's root node.
     * - Create a map to map node objects with their relative 
     *   distance from the root of the tree.
     * - Initially with the queue holding only the nodeDistancePair
     *   object for the root node of distance 0, iterate over the 
     *   length of the queue: 
     *   - Dequeue the node
     *   - Add the current node data to the map w.r.t. its distance 
     *     as key.
     *   - Queue left and right child nodes' nodeDistancePair objects 
     *     by pairing the nodes with their distances equal to the 
     *     current node's distance decremented and incremented 
     *     respectively.
     */
    static List<List<Integer>> verticalOrderIterative(BinaryTreeNodeVerticalView<Integer> root) 
	{
        /*
         * Queue stores nodeDistancePair objects containing 
         * nodes paired with their relative distance from 
         * the root of the tree.
         */
        LinkedList<nodeDistancePair> queue = new LinkedList<>();

        /*Map the nodes to distance values measured relative to the root node of the tree. */
        HashMap<Integer, List<Integer>> verticalViewMap = new HashMap<>();

        // /*
        //  * Store maximum and minimum relative distances.
        //  * Used to iterate over the values of keys of 
        //  * the map, from the minimum key (or distance) 
        //  * to the maximum key (or distance).
        //  */
        // int minh1 = 0, maxh1 = 0;

        /*Store the values from the key-value map after all the nodes have been parsed. */
        List<List<Integer>> ans = new ArrayList<>();

        /*
         * The queue is initialized with the root node 
         * of the tree and its distance from itself is 
         * naturally 0.
         */
        queue.addLast(new nodeDistancePair(root, 0));

        /*Iterate until the queue is not empty. */
        while(!queue.isEmpty()) 
		{
            /*Store the length of the queue. */
            int size = queue.size();
            /*Iterate over the length of the queue */
            for(int i=0; i<size; i++) 
			{
                /*Store the first element from the queue. */
                nodeDistancePair currNodeDistPair = queue.removeFirst();
                BinaryTreeNodeVerticalView<Integer> currNode = currNodeDistPair.node;
                int distance = currNodeDistPair.distance;

                // /*Update maximum and minimum relative distances. */
                // maxh1 = Math.max(maxh1, vp.distance);
                // minh1 = Math.min(minh1, vp.distance);

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
                 * by making their nodeDistancePair objects.
                 * - The left node's object is made with the current 
                 *   node's distance decremented.
                 * - The right node's object is made with the current 
                 *   node's distance incremented.           
                 */

                /*Queue the left child of the current node. */
                if(currNode.left != null) 
                {
                    nodeDistancePair leftChild = new nodeDistancePair(currNode.left, distance - 1);
                    queue.addLast(leftChild);
                }
                /*Queue the right child of the current node. */
                if(currNode.right != null) {
                    nodeDistancePair rightChild = new nodeDistancePair(currNode.right, distance + 1);
                    queue.addLast(rightChild);
                }
            }
        }
        for(int distance : verticalViewMap.keySet()) 
        {
            ans.add(verticalViewMap.get(distance));
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
        BinaryTreeNodeVerticalView<Integer> root = new BinaryTreeNodeVerticalView<Integer>(10);
        root.left = new BinaryTreeNodeVerticalView<Integer>(20);
        root.right = new BinaryTreeNodeVerticalView<Integer>(30);
        root.left.left = new BinaryTreeNodeVerticalView<Integer>(40);
        root.left.right = new BinaryTreeNodeVerticalView<Integer>(50);
        root.right.left = new BinaryTreeNodeVerticalView<Integer>(60);
        root.right.right = new BinaryTreeNodeVerticalView<Integer>(70);
        root.left.left.left = new BinaryTreeNodeVerticalView<Integer>(80);

        HashMap<Integer, ArrayList<Integer>> verticalView = new HashMap<>();
        vericalViewRecursive(root, 0, verticalView);

        for(Map.Entry<Integer, ArrayList<Integer>> m : verticalView.entrySet()) 
        {
            System.out.println(m.getKey() + " : " + m.getValue());
        }
        System.out.println(verticalOrderIterative(root));
    }
}
