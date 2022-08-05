package DSA_LinkedList;

import java.util.HashMap;

/*
 * - Traverse the given linked list:
 * 
 * - Create a new node object with the given 
 *   list’s node object's data and arbitrary 
 *   reference.
 * - Link this new node object to the new 
 *   linked list.
 * - Map the references of the old linked 
 *   list's nodes to the new linked list's 
 *   nodes.
 * 
 * 
 * - Traverse the deep copied new linked 
 *   list:
 * 
 * - Copy the reference of a new node's 
 *   arbitrary reference and use the map 
 *   to find the reference of the 
 *   corresponding new node.
 * - Copy the reference of this new node.
 * - Update the arbitrary node reference 
 *   of the new node.
 */
class NodeDoublyAribtrary
{
    /*
     * To store the data of a fixed type. 
     * Linked lists store data of homogenous 
     * type.
     */
    int data;
    /*
     * Object of Node, used to store the 
     * reference of the next Node object.
     */
    NodeDoublyAribtrary next;
    /*
     * Object of Node, used to store the 
     * reference of the arbitrary Node object.
     */
    NodeDoublyAribtrary arb;

    public NodeDoublyAribtrary(int data, NodeDoublyAribtrary next, NodeDoublyAribtrary arb)
    {
        /*
         * This keyword is a reference to the 
         * memebers of the object whose method 
         * or constructor is called.
         */
        this.data = data;        
        /*
         * The reference to the next Node is 
         * by default set to null.
         */
        this.next = next;
        /*
         * The reference to the arbitrary Node 
         * is by default set to null.
         */
        this.arb = arb;
    }
}

public class P124_CloneDoublyLL {

    static NodeDoublyAribtrary cloneArbitraryDoublyLL(NodeDoublyAribtrary original)
    {
        NodeDoublyAribtrary originalHead = original;
        NodeDoublyAribtrary resultHead = null;
        NodeDoublyAribtrary resultTail = null;
        // resultHead = resultTail;

        /*HashMap to store arbitrary links of nodes */
        HashMap<NodeDoublyAribtrary, NodeDoublyAribtrary> oldNewNodeMap = new HashMap<>();

        while(originalHead != null)
        {
            /*
             * Create a new node objext storing 
             * the data of the same original node.
             */
            NodeDoublyAribtrary temp = new NodeDoublyAribtrary(originalHead.data, null, originalHead.arb);
            
            /*
             * Attach the node to the result linked 
             * list.
             */
            if(resultTail == null) 
            {
                resultHead = resultTail = temp;
            }
            else
            {
                resultTail.next = temp;
                resultTail = temp;
            }

            /*
             * Hash the old node with the corresponding 
             * new node.
             */
            oldNewNodeMap.put(originalHead, resultTail);
            originalHead = originalHead.next;
        }

        /*
         * During traversal of the result linked list 
         * with its arbitrary references, storing the 
         * references of the original linked list's 
         * nodes:
         * - Copy the reference of a new node's 
         *   arbitrary reference and use the map 
         *   to find the reference of the new node.
         * - Copy the reference of this new node
         * - Update the arbitrary node reference of 
         *   the new node.
         */
        NodeDoublyAribtrary traverseResult = resultHead;

        while(traverseResult != null)
        {
            NodeDoublyAribtrary arbRefOld = traverseResult.arb;
            NodeDoublyAribtrary arbRefNew = oldNewNodeMap.get(arbRefOld);
            traverseResult.arb = arbRefNew;
            traverseResult = traverseResult.next;
        }

        return resultHead;
    }

    /*
     * Display: Node data and the linked 
     * arbitrary node data.
     */
    static void listDataDisplay(NodeDoublyAribtrary head)
    {
        NodeDoublyAribtrary printData = head;
        System.out.println("Node --> Arbitrary Link Node");
        while(printData != null)
        {
            if(printData.arb == null)
                System.out.println(printData.data+"    --> null");
            else
                System.out.println(printData.data+"    --> "+printData.arb.data);
            printData = printData.next;
        }
    }

    /*
     * Display: Node object address and 
     * the linked arbitrary node object 
     * address.
     */
    static void listObjectAdrsDisplay(NodeDoublyAribtrary head)
    {
        NodeDoublyAribtrary printNodeAdrs = head;
        while(printNodeAdrs != null)
        {
            if(printNodeAdrs.arb == null)
                System.out.println(printNodeAdrs+" --> null");
            else
                System.out.println(printNodeAdrs+" --> "+printNodeAdrs.arb);
            printNodeAdrs = printNodeAdrs.next;
        }
    }

    public static void main(String[] args) 
    {
        NodeDoublyAribtrary node3 = new NodeDoublyAribtrary(4, null, null);
        NodeDoublyAribtrary node2 = new NodeDoublyAribtrary(3, node3, null);
        NodeDoublyAribtrary node1 = new NodeDoublyAribtrary(2, node2, node3);
        NodeDoublyAribtrary head = new NodeDoublyAribtrary(1, node1, node2);
        
        /*
         * Printing the data and addresses of 
         * the nodes of the original linked list.
         */

        System.out.println("The original list:");
        listDataDisplay(head);

        System.out.println("The original nodes' addresses, to prove new nodes were created for the clone:");
        listObjectAdrsDisplay(head);
        
        System.out.println();

        /*
         * Printing the data and addresses of 
         * the nodes of the cloned linked list.
         */
        NodeDoublyAribtrary cloneHead = cloneArbitraryDoublyLL(head);

        System.out.println("The cloned list:");
        listDataDisplay(cloneHead);

        System.out.println("The clone nodes' addresses, to prove new nodes were created for the clone:");
        listObjectAdrsDisplay(cloneHead);

    }
}
