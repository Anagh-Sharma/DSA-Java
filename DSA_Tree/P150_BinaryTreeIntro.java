package DSA_Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

class BinaryTreeNode<T>
{
    T data;
    BinaryTreeNode<T> left;
    BinaryTreeNode<T> right;
    BinaryTreeNode(T data)
    {
        this.data = data;
        left = right = null;
    }
}

public class P150_BinaryTreeIntro {
    String message = "root node";
    Scanner scanner = new Scanner(System.in);
    /*
     * Recursive method to construct a tree:
     * - 1. Create a root node object.
     *      The root node created with data entered by user.
     *      The reference of this node is returned by this 
     *      function with the exception of the base case, 
     *      where null is returned. The base case is when
     *      -1 is scanned.
     * - 2. First, recursively create a root node object whose 
     *      returned reference is stored in the left child member 
     *      of the node constructed in this method call.
     * - 3. Second, recursively create a root node object whose 
     *      returned reference is stored in the right child member 
     *      of the node constructed in this method call.
     * - Thus, the left child nodes are created first recursively, 
     *   then at return time the right child nodes are created 
     *   recursively.
     */
    BinaryTreeNode<Integer> insert() 
    {
        System.out.println("Enter data for " + message + " or enter -1 to exit.");
        int data = scanner.nextInt();
        /*
         * Base case: When the scanned integer is -1.
         * No recursive call is made and the function 
         * returns null to the last function call or 
         * the function call of this unconstructed 
         * node's root node.
         */
        if(data == -1) 
        {
            return null;
        }
        /*A node object is constructed with the entered data. */
        BinaryTreeNode<Integer> node = new BinaryTreeNode<Integer>(data);
        /*
         * message is a non-static variable:
         * 
         * - For the first method call on the call stack the 
         *   message variable is initialized to "root" to 
         *   denote the root of the entire tree.
         * - Thereafter, the message is altered to either "left" 
         *   or "right" before a recursive call to denote the 
         *   left child node or the right child node of the node 
         *   that is constructed in this method.
         */

        /*
         * Make a recursive call and save the returned 
         * node's reference to this node's left child 
         * member.
         */
        message = "left node";
        node.left = insert();
        /*
         * Make a recursive call and save the returned 
         * node's reference to this node's left child 
         * member.
         */
        message = "right node";
        node.right = insert();

        message = "root node";
        return node;
    }

    /*Breadth First Order */

    /*
     * Order:
     * Root node -> Child nodes of Level 1 -> Child nodes of Level 2...
     * - Queue the root node
     * - Queue the left and right child nodes of each node already in the 
     *   queue by first removing and printing the node, then queuing its 
     *   left and right child nodes.
     */
    void levelOrder(BinaryTreeNode<Integer> root) 
    {
        LinkedList<BinaryTreeNode<Integer>> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) 
        {
            BinaryTreeNode<Integer> node = queue.removeFirst();
            System.out.println(node.data);
            if(node.left != null) 
            {
                queue.addLast(node.left);
            }
            if(node.right != null) 
            {
                queue.addLast(node.right);
            }
        }
    }

    /*Depth First Order */

    /*
     * Order:
     * Root node -> Left node (and its subtree) -> Right node (and its subtree)
     * Recursively print root nodes from first the left subtree, then the right 
     * subtree.
     */
    public void preOrder(BinaryTreeNode<Integer> root) {
        if(root == null) 
        {
            return;
        }
        System.out.print(root.data+", ");
        preOrder(root.left);
        preOrder(root.right);
    }

    /*
     * Order:
     * Left node (and its subtree) -> Root node -> Right node (and its subtree)
     * Print root nodes at return time after recursive call to the left subtree, 
     * then make a recursive call to the right subtree.
     */
    public void inOrder(BinaryTreeNode<Integer> root) 
    {
        if(root == null) 
        {
            return;
        }
        
        inOrder(root.left);
        System.out.print(root.data+", ");
        inOrder(root.right);
    }

    /*
     * Order:
     * Left node (and its subtree) -> Right node (and its subtree) -> Root node
     * Print root nodes at return time after recursive calls to first the left subtree, 
     * then the right subtree.
     */
    public void postOrder(BinaryTreeNode<Integer> root) {
        if(root == null) {
            return;
        }
        
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.data+", ");
    }

    /*
     * Order:
     * Root node -> Left node (and its subtree) -> Right node (and its subtree)
     * Print the left nodes until the left becomes null, then print the right 
     * subtree of the last left node, then the second last, in the similar 
     * manner.
     * - Print the root node by initializing "current" variable as root.
     * - Push the right child node onto stack.
     * - Update "current" with the reference of the left child node.
     * - In the next iteration stack is popped if the "current" variable 
     *   holds null, i.e., when the last iteration's node did not have a 
     *   left child.
     */
    void preOrderIterative(BinaryTreeNode<Integer> root) 
    {
        if(root == null) {
            return;
        }
        Stack<BinaryTreeNode<Integer>> st = new Stack<>();
        /*Initialize a node object with the reference of the root node. */
        BinaryTreeNode<Integer> current = root;
        while(!st.isEmpty() || current != null) 
        {
            /*
             * - Current is null when the left child of the last 
             *   iteration's current node did not have a left child.
             * - The last right member reference pushed onto the 
             *   stack is popped.
             */
            if(!st.isEmpty() && current == null) 
            {
                current = st.pop();
            }
            /*Print the data of the current node. */
            System.out.print(current.data+", ");
            /*If the right child is not null push it onto stack. */
            if(current.right != null) 
            {
                st.push(current.right);
            }
            /*Update current with the reference of the left child. */
            current = current.left;
        }
    }

    void preOrderIterativeSimpler(BinaryTreeNode<Integer> root) 
    {
        if(root == null) {
            return;
        }
        Stack<BinaryTreeNode<Integer>> st = new Stack<>();
        /*Initialize stack the with the reference of the root node. */
        st.push(root);
        BinaryTreeNode<Integer> current = null;
        while(!st.isEmpty()) 
        {
            /*The first element is root for the first iteration, then left, then right nodes. */
            current = st.pop();
            System.out.print(current.data+", ");

            /*The right child is push pushed first, so its popped later. */
            if(current.right != null)
                st.push(current.right);

            /*The left child is push pushed second, so its popped first. */            
            if(current.left != null)
                st.push(current.left);
        }
    }

    /*
     * Order:
     * Left node (and its subtree) -> Root node -> Right node (and its subtree)
     * Push the root on stack, then push each left subtree on stack until the left 
     * subtree becomes null. Then pop the last subtree from stack and print the root 
     * node. Then, iterate to the right subtree of the root node and so on.
     */
    void inOrderIterative(BinaryTreeNode<Integer> root) 
    {
        if(root == null) {
            return;
        }
        Stack<BinaryTreeNode<Integer>> st = new Stack<>();
        /*Initialize current with the reference of the root node. */
        BinaryTreeNode<Integer> current = root;
        while(!st.isEmpty() || current != null) 
        {
            /*
             * The current object holds a null reference, when 
             * the last iteration's node's left child was null.
             * Otherwise:
             * - Push the current node on stack to print the current 
             *   node and its right subtree later.
             * - Update current node to its left child denoting either 
             *   a left left node or a subtree. Thus the left child is 
             *   proccessed first and then, the node and the right subtree.
             */
            if(current != null)
            {
                st.push(current);// 1 2 | 2 4 | 4 null | null else | null else | 5 null | 
                                 // null else | null else | 3 null | null else
                current = current.left;
            }
            /*
             * The current object holds a null reference, when 
             * the last iteration's node's left child was null.
             * This means that now:
             * - The last iteration's node will be processed
             * - Its right subtree will be processed. 
             */
            else// 4 2 5 1 3
            {
                current = st.pop();
                System.out.print(current.data+", ");
                current = current.right;
            }
        }
    }

    /*
     * Iterate over the nodes:
     * - Store the node in a stack: mainStack
     * - Store the right child of the node on a stack: rightChildStack
     */
    void postOrderIterativeSimpler(BinaryTreeNode<Integer> root) 
    {
        if(root == null) 
        {
            return;
        }
        Stack<BinaryTreeNode<Integer>> nodesStack = new Stack<>();
        Stack<BinaryTreeNode<Integer>> nodesRightChildStack = new Stack<>();

        /*Initialize current with the reference of the root node. */
        BinaryTreeNode<Integer> current = root;
        /*Iterate until stack is empty or current node is not null. */
        while(!nodesStack.isEmpty() || current != null) 
        {
            /*
             * The if condition is to reach the left most 
             * subtree from the current node.
             */
            /*
             * If the current variable does not hold a null reference:
             * - The current variable of the last iteration did 
             *   not have a left subtree.
             */
            if(current != null)
            {
                /*Store the root node of the right subtree of the current node. */
                if(current.right != null)
                    nodesRightChildStack.push(current.right);

                /*Store the current node. */
                nodesStack.push(current);

                /*Increment the current node to the next left subtree's root node. */
                /*
                 * current stores null when no left subtree is present and the next 
                 * iteration goes to the else block.
                 */
                current = current.left;
            }

            /*
             * Else when:
             * - The last iteration's current node had no left subtree
             * - current was set to null in the last iteration for backtracking.
             * - Node references were still present in the Nodes stack.
             * 
             * The last iteration's current node (which is stored on top 
             * of the nodesStack) can:
             * - Either have only a right subtree and no left subtree (Or 
             *   current was set to null in the last iteration for backtracking).
             *   In this case the right subtree has to be printed first 
             *   and hence current is updated to the root of this subtree.
             *   To check this is the case:
             *   Compare the right subtree of the the last iteration's current 
             *   node with the stored right subtree of nodes in the separate 
             *   rightNodesStack. If they are same then this is the case.
             * - Or if the condition checked for the right subtre is false then 
             *   no right subtree needs to be printed and only the node needs 
             *   to be printed.
             */
            else
            {
                /*The last node has a right subtree which nees to be printed before the current node.  */
                if(!nodesRightChildStack.isEmpty() && nodesStack.peek().right == nodesRightChildStack.peek())
                {
                    current = nodesRightChildStack.pop();
                }
                /*
                 * The last node has no right subtree, apart from not having a 
                 * left subtree (hence the else condition is executed), therefore 
                 * only the root needs to be printed.
                 */
                else
                {
                    current = nodesStack.pop();
                    System.out.print(current.data+", ");
                    /*
                     * Current is set to null as again the else condition 
                     * needs to be executed to backtrack to the last node 
                     * stored in the nodesStack or its right subtree (if 
                     * it exists).
                     */
                    current = null;
                }
            }
        }
    }

    /*
     * - If current node has a right subtree:
     *   - First, push the root node of the right subtree onto stack.
     *   - Then, push the current node itself on stack
     * - If the current node has a left child change current to store 
     *   the reference of its left child and, repeat the proccess.
     * - Reach the left most node.
     * 
     * - If a node doesn't have a right subtree:
     *   - Push the node to stack.
     *   - Change the current reference to its left subtree.
     * 
     * - If the current node (moved to in the last step) is null:
     *   - If its right child does not exist:
     *     - Pop and print the last node from stack.
     *     - Set the current node to null (As, the node needs to backtrack
     *       to do that the last stored node of the stack needs to be popped.)
     *   - If its right child exists:
     *     - Pop and print the last node from stack.
     *     - Compare the top node on the stack and the popped node's right child.
     *       (The top node on the stack now, has to be to be right child of the 
     *        of the popped stack if the right child exists.)
     *       - If they are same: 
     *         - The right child's subtree needs to be proccessed first before printing 
     *           the popped node.
     *         - Pop the top node from the stack.
     *           (This needs to be proccessed first).
     *         - Push the last popped node back onto the stack.
     *           (This needs to be proccessed second).
     *         - Update current to the right child's reference.
     *       - If they are not the same:
     *         - Then only the popped node needs to be printed.
     */
    ArrayList<Integer> postOrderIterative(BinaryTreeNode<Integer> root) 
    {
        Stack<BinaryTreeNode<Integer>> st = new Stack<>();
        /*ArrayList to store the data of the nodes traversed. */
        ArrayList<Integer> list = new ArrayList<>();
        if(root == null) {
            return list;
        }

        /*Initialize the stack with the root node. */
        st.push(root);
        /*prev: Node object to store the "current" reference of the previous iteration. */
        BinaryTreeNode<Integer> prev = null;
        /*Iterate until the stack is empty. */
        while(!st.isEmpty()) 
        {
            /*
             * current: Node object to store the reference of the top node on the stack.
             * - For the first iteration this is the root node.
             */
            BinaryTreeNode<Integer> current = st.peek();
            /*
             * - If previous is null, i.e., during the first iteration.
             * - Or If the left child of the previous current stores the 
             *   reference of the current.
             * - Or If the right child of the previous current stores the 
             *   reference of the current.
             * Then:
             * - If current has a left child:
             *   Push its reference onto stack.
             * - If current has a right child:
             *   Push its reference onto stack.
             * - Else:
             *   - Pop the top element of the stack, i.e., the current 
             *     element.
             *   - Append the data of the current element to the ArrayList.
             */
            if(prev == null || prev.left == current || prev.right == current) 
            {
                if(current.left != null) 
                {
                    st.push(current.left);
                }
                else if(current.right != null) 
                {
                    st.push(current.right);
                }
                else 
                {
                    st.pop();
                    list.add(current.data);
                }
            }

            /*
             * Else if the left child of current holds the 
             * reference of the previous:
             * - If the right child of current is not null:
             *   Push the right child of current onto stack.
             * - Else:
             *   - Pop the top element of the stack, i.e., the current 
             *     element.
             *   - Append the data of the current element to the ArrayList.
             */
            else if(current.left == prev) 
            {
                if(current.right != null) 
                {
                    st.push(current.right);
                }
                else 
                {
                    st.pop();
                    list.add(current.data);
                }
            }

            /*
             * If the right child node of the current node holds the 
             * reference for the the previous current node, then: 
             * - Pop the top element of the stack, i.e., the current 
             *   element.
             * - Append the data of the current element to the ArrayList.
             */
            else if(current.right == prev) 
            {
                st.pop();
                list.add(current.data);
            }
            /*Update previous to store the current reference. */
            prev = current;
        }
        return list;
    }

    /*
     * Recursive solution:
     * Recur on the left and right subtrees pf the 
     * root and print the node whose left and right 
     * nodes are null, i.e., the root node.
     */
    static void treeLeavesRecursive(BinaryTreeNode<Integer> root)
    {
        /*Base case: If a null node is passed. */
        if(root == null)
        {
            return;
        }
        /*Base case: If a leaf node is passed. */
        else if(root.left == null && root.right == null)
        {
            System.out.print(root.data+", ");    
            return;
        }
        /*
         * Recursive case: If a subtree is passed, recur on
         * its left and right subtree.
         */
        treeLeavesRecursive(root.left);
        treeLeavesRecursive(root.right);
    }

    /*Same as the last recursive solution, but returns an ArrayList of the solution. */
    static ArrayList<Integer> treeLeavesRecursive(BinaryTreeNode<Integer> root, ArrayList<Integer> leaves)
    {
        if(root == null)
        {
            return new ArrayList<>();
        }
        else if(root.left == null && root.right == null)
        {
            ArrayList<Integer> nodeData = new ArrayList<>();
            nodeData.add(root.data);
            return nodeData;
        }

        ArrayList<Integer> leftTreeLeaves = treeLeavesRecursive(root.left, leaves);
        ArrayList<Integer> rightTreeLeaves = treeLeavesRecursive(root.right, leaves);

        leftTreeLeaves.addAll(rightTreeLeaves);
        return leftTreeLeaves;
    }

    /*
     * - Parse a tree in level order and store a 
     *   node if it is a leaf node.
     */
    static ArrayList<Integer> treeLeavesIterative(BinaryTreeNode<Integer> root) 
    {
        LinkedList<BinaryTreeNode<Integer>> queue = new LinkedList<>();
        ArrayList<Integer> leaves = new ArrayList<>();

        queue.add(root);
        while(!queue.isEmpty()) 
        {
            BinaryTreeNode<Integer> node = queue.removeFirst();
            if(node.left == null && node.right == null)
                leaves.add(node.data);
            
            if(node.left != null) 
            {
                queue.addLast(node.left);
            }
            if(node.right != null) 
            {
                queue.addLast(node.right);
            }
        }
        
        return leaves;
    }

    /*
     * Recursively print the root nodes of all the left 
     * subtrees and then the right subtrees.
     */
    void print(BinaryTreeNode<Integer> root) {
        if(root == null) {
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

    public static void main(String[] args) {
        P150_BinaryTreeIntro tree = new P150_BinaryTreeIntro();
        BinaryTreeNode<Integer> root = tree.insert();
        tree.print(root);
        System.out.println();
        System.out.println();

        tree.preOrder(root);
        System.out.println();
        System.out.println();

        tree.inOrder(root);
        System.out.println();
        System.out.println();

        tree.postOrder(root);
        System.out.println();
        System.out.println();

        tree.preOrderIterative(root);
        System.out.println();
        System.out.println();

        tree.preOrderIterativeSimpler(root);
        System.out.println();
        System.out.println();
        
        tree.inOrderIterative(root);
        System.out.println();
        System.out.println();

        tree.postOrderIterativeSimpler(root);
        System.out.println();
        System.out.println();

        System.out.print(tree.postOrderIterative(root));
        System.out.println();
        System.out.println();

        System.out.println("Leaves of the tree (Recursive solution): ");
        treeLeavesRecursive(root);

        System.out.println();
        System.out.println("Leaves of the tree (Recursive solution): ");
        ArrayList<Integer> leaves = treeLeavesRecursive(root, new ArrayList<Integer>());
        System.out.println(leaves);

        System.out.println("Leaves of the tree (Iterative solution): ");
        System.out.println(treeLeavesIterative(root));
    }    
}
