package DSA_Tree;

import java.util.LinkedList;

class BinaryTreeLeftView<T>
{
    T data;
    BinaryTreeLeftView<T> left;
    BinaryTreeLeftView<T> right;
    BinaryTreeLeftView(T data)
    {
        this.data = data;
        left = right = null;
    }
}

public class P155_LeftViewTree {
    /*
     * Recursivly print the value of the first 
     * node of a level by recurring on the left 
     * and right subtrees.
     * Base case:
     * - If the root node has been reached:
     *   - return
     * - If the current node is the first of its level:
     *   - Print the current node.
     *   - Update level reached count.
     * Recursive case:
     * - Recur on the left subtree.
     * - Recur on the right subtree.
     */
    /*
     * levelReached: A static variable is 
     * initialized to 0. 
     * When nodes starting from the root node are 
     * parsed, levelReached is updated to the level 
     * when the current level becomes larger.
     * Since, the recursion occurs of the left subtree 
     * first, therefore, the left most node is encountered 
     * first and printed with the update.
     */
    static int levelReached = 0;
    static void leftViewTree(BinaryTreeLeftView<Integer> root, int currLevel) 
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
        
        leftViewTree(root.left, currLevel + 1);
        leftViewTree(root.right, currLevel + 1);
    }

    /*
     * - Traverse a tree in level order or Breadth-first order 
     *   and print only the first node of a level.
     * - Starting from the root node for level 0 in the queue,
     *   iterate over the length of the queue: 
     *   Dequeue the node, then queue the left child, and then 
     *   the right child.
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
     *     - Queue the node's left and right children.
     */
    static void leftViewTreeIterative(BinaryTreeLeftView<Integer> root) 
    {
        LinkedList<BinaryTreeLeftView<Integer>> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) 
        {
            int levelLength = queue.size();
            for(int i=0; i<levelLength; i++)
            {
                BinaryTreeLeftView<Integer> node = queue.removeFirst();
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
        BinaryTreeLeftView<Integer> root = new BinaryTreeLeftView<Integer>(10);
        root.left = new BinaryTreeLeftView<Integer>(20);
        root.right = new BinaryTreeLeftView<Integer>(30);
        root.left.left = new BinaryTreeLeftView<Integer>(40);
        root.left.right = new BinaryTreeLeftView<Integer>(50);
        root.right.left = new BinaryTreeLeftView<Integer>(60);
        root.right.right = new BinaryTreeLeftView<Integer>(70);
        root.left.left.left = new BinaryTreeLeftView<Integer>(80);
        System.out.println("Recursive solution: ");
        System.out.println("The left view of the tree: ");
        leftViewTree(root, 1);

        System.out.println();
        System.out.println();
        System.out.println("Iterative solution: ");
        leftViewTreeIterative(root);
    }  
}
