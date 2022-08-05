package DSA_Tree;

import java.util.ArrayList;

class BinaryTreeLCA<T>
{
    T data;
    BinaryTreeLCA<T> left;
    BinaryTreeLCA<T> right;
    BinaryTreeLCA(T data)
    {
        this.data = data;
        left = right = null;
    }
}

public class P168_LCATree {

    /*
     * - Find the paths to both the elements in the tree.
     * - From the root, find the last common element.
     */
    static boolean ancestorsNode(BinaryTreeLCA<Integer> root, int target, ArrayList<Integer> foundPath)
    {
        if(root == null)
            return false;
        
        if(root.data == target)
        {
            foundPath.add(root.data);
            return true;
        }

        if(ancestorsNode(root.left, target, foundPath) || ancestorsNode(root.right, target, foundPath))
        {
            foundPath.add(root.data);
            return true;
        }
        return false;
    }

    static int lcaNodes(BinaryTreeLCA<Integer> root, int target1, int target2)
    {
        ArrayList<Integer> foundPath1 = new ArrayList<>();
        ancestorsNode(root, target1, foundPath1);

        ArrayList<Integer> foundPath2 = new ArrayList<>();
        ancestorsNode(root, target2, foundPath2);

        int lengthToCheck, toSkip;
        int commonAncestor = 0;

        if(foundPath1.size() >= foundPath2.size())
        {
            lengthToCheck = foundPath2.size();
            toSkip = foundPath1.size() - foundPath2.size();
            for(int i=0; i<lengthToCheck; i++)
            {
                if(foundPath1.get(i+toSkip) == foundPath2.get(i))
                {
                    commonAncestor = foundPath1.get(i);
                    break;
                }
            }
        }
        else
        {
            lengthToCheck = foundPath1.size();
            toSkip = foundPath2.size() - foundPath1.size();
            for(int i=0; i<lengthToCheck; i++)
            {
                if(foundPath1.get(i) == foundPath2.get(i+toSkip))
                {
                    commonAncestor = foundPath1.get(i);
                    break;
                }
            }
        }
        return commonAncestor;
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
        BinaryTreeLCA<Integer> root = new BinaryTreeLCA<Integer>(10);
        root.left = new BinaryTreeLCA<Integer>(20);
        root.right = new BinaryTreeLCA<Integer>(30);
        root.left.left = new BinaryTreeLCA<Integer>(40);
        root.left.right = new BinaryTreeLCA<Integer>(50);
        root.right.left = new BinaryTreeLCA<Integer>(60);
        root.right.right = new BinaryTreeLCA<Integer>(70);
        root.left.left.left = new BinaryTreeLCA<Integer>(80);

        int lca = lcaNodes(root, 50, 80);
        
        System.out.println(lca);

    }
}
