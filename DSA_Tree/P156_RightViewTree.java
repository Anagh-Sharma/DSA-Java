package DSA_Tree;

import java.util.LinkedList;

class BinaryTreeRightView<T>
{
    T data;
    BinaryTreeRightView<T> left;
    BinaryTreeRightView<T> right;
    BinaryTreeRightView(T data)
    {
        this.data = data;
        left = right = null;
    }
}

public class P156_RightViewTree {
    /*
     * Recursivly print the value of the first 
     * node of a level by recurring on the right 
     * and left subtrees.
     * Base case:
     * - If the root node has been reached:
     *   - return: 0.
     * Recursive case:
     * - Recur on the right subtree.
     * - Recur on the left subtree.
     */
    /*
     * levelReached: A static variable is 
     * initialized to -1. 
     * When nodes starting from the root node are 
     * parsed, levelReached is updated to the level 
     * when the current level becomes larger.
     * Since, the recursion occurs of the right subtree 
     * first, therefore, the right most node is encountered 
     * first and printed with the update.
     */
    static int levelReached = -1;
    static void rightViewTree(BinaryTreeRightView<Integer> root, int currLevel) 
    {
        if(root == null) 
        {
            return;
        }

        /*Print node data (Calling time) */
        if(levelReached < currLevel)
        {
            System.out.print(root.data+", ");
            levelReached = currLevel;
        }
        
        rightViewTree(root.right, currLevel + 1);
        rightViewTree(root.left, currLevel + 1);
    }

    /*
     * - Traverse a tree in level order or Breadth-first order 
     *   and print only the first node of a level.
     * - Starting from the root node for level 0 in the queue,
     *   iterate over the length of the queue: 
     *   Dequeue the node, then queue the right child, and then 
     *   the left child.
     * - The first node of a level is the first node to be dequeued 
     *   and is printed.
     * - Then the child nodes of the rest of the nodes of the level 
     *   are queued until all the nodes of the level are dequeued.
     * - When all the nodes of a level have been dequeued, then only 
     *   all the nodes of the next level are present in the queue and 
     *   the process is repeated for the next level.
     * 
     * - Initialize a queue with the root node reference.
     * - Iterate untill the queue is empty.
     *   - Store the length of the queue.
     *   - Iterate over the length of the queue.
     *     - Dequeue a node.
     *     - Print the node if this is the first iteration.
     *     - Queue the node's right and left children.
     */
    static void rightViewTreeIterative(BinaryTreeRightView<Integer> root) 
    {
        LinkedList<BinaryTreeRightView<Integer>> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) 
        {
            int levelLength = queue.size();
            for(int i=0; i<levelLength; i++)
            {
                BinaryTreeRightView<Integer> node = queue.removeFirst();
                /*Print the node if its the first of its level. */
                if(i == 0)
                    System.out.print(node.data+", ");

                if(node.right != null) 
                {
                    queue.addLast(node.right);
                }  
                if(node.left != null) 
                {
                    queue.addLast(node.left);
                }
            }
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
    public static void main(String[] args) {
        BinaryTreeRightView<Integer> root = new BinaryTreeRightView<Integer>(10);
        root.left = new BinaryTreeRightView<Integer>(20);
        root.right = new BinaryTreeRightView<Integer>(30);
        root.left.left = new BinaryTreeRightView<Integer>(40);
        root.left.right = new BinaryTreeRightView<Integer>(50);
        root.right.left = new BinaryTreeRightView<Integer>(60);
        root.right.right = new BinaryTreeRightView<Integer>(70);
        root.left.left.left = new BinaryTreeRightView<Integer>(80);
        System.out.println("Recursive solution: ");
        System.out.println("The right view of the tree: ");
        rightViewTree(root, 0);
        
        System.out.println();
        System.out.println();
        System.out.println("Iterative solution: ");
        rightViewTreeIterative(root);
    }  
}
