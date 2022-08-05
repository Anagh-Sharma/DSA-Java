package DSA_Tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

class BinaryTreeSO<T>
{
    T data;
    BinaryTreeSO<T> left;
    BinaryTreeSO<T> right;
    BinaryTreeSO(T data)
    {
        this.data = data;
        left = right = null;
    }
}

public class P164_SpiralOrderTree {
    
    /*
     * - Traverse the tree in level order
     * - Append the nodes of a level based on 
     *   the following condition:
     * - If the level is odd: 
     *   - Append the nodes in original order
     * - Else:
     *   - Append the nodes in reverse order
     */
    static ArrayList<Integer> spiralOrder(BinaryTreeSO<Integer> root)
    {
        LinkedList<BinaryTreeSO<Integer>> queue = new LinkedList<>();
        ArrayList<Integer> spiralOrder = new ArrayList<>();
        int level = 0;

        queue.addLast(root);
        while(!queue.isEmpty())
        {
            ArrayList<Integer> levelNodes = new ArrayList<>();
            int length = queue.size();
            for(int i=0; i<length; i++)
            {
                BinaryTreeSO<Integer> node = queue.removeFirst();
                levelNodes.add(node.data);

                if(node.left != null)
                    queue.addLast(node.left);
                
                if(node.right != null)
                    queue.addLast(node.right);
            }
            level++;
            if(level%2 == 0)
            {
                spiralOrder.addAll(levelNodes);
            }
            else
            {
                Collections.reverse(levelNodes);
                spiralOrder.addAll(levelNodes);
            }
        }

        return spiralOrder;
    }

    /*
    *               20
    *            /      \  
    *          8         12
    *        /    \     /   \
    *       3      5   7     5
    */
    public static void main(String[] args) 
    {
        BinaryTreeSO<Integer> root = new BinaryTreeSO<Integer>(20);
        root.left = new BinaryTreeSO<Integer>(8);
        root.right = new BinaryTreeSO<Integer>(12);
        root.left.left = new BinaryTreeSO<Integer>(3);
        root.left.right = new BinaryTreeSO<Integer>(5);
        root.right.left = new BinaryTreeSO<Integer>(7);
        root.right.right = new BinaryTreeSO<Integer>(5);

        System.out.println("Spiral order of the given tree:");
        System.out.println(spiralOrder(root));
    }
}
