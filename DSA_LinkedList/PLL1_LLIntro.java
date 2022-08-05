package DSA_LinkedList;

class Node
{
    /*To store the data of a fixed type. Linked lists store data of homogenous type. */
    int data;

    /*Object of Node, used to store the reference of the next Node object. */
    Node next;

    public Node(int data)
    {
        /*
         * This keyword is a reference to the memebers 
         * of the object whose method or constructor is 
         * called.
         */
        this.data = data;
        /*The reference to the next Node is by default set to null. */
        this.next = null;
    }
}
/*
 * Generic class Node1
 * - T is a type parameter of generic class NodeGeneric.
 * - To reference the generic NodeGeneric class, 
 *   a generic type invocation is performed, which replaces 
 *   T with some concrete value, such as Integer.
 */
class NodeGeneric<T>
{
    /*To store the data of a fixed type. Linked lists store data of homogenous type. */
    T data;

    /*Object of Node, used to store the reference of the next Node object. */
    NodeGeneric<T> next;

    public NodeGeneric(T data)
    {
        /*
         * This keyword is a reference to the memebers 
         * of the object whose method or constructor is 
         * called.
         */
        this.data = data;
        /*The reference to the next Node is by default set to null. */
        this.next = null;
    }
}

public class PLL1_LLIntro 
{
    public static void main(String[] args) {
        /*Creating three Node objects. */
        Node n1 = new Node(10);
        Node n2 = new Node(20);
        Node n3 = new Node(30);
        /*Copying refrences from one node to the other, to create linked nodes, or linked lists. */
        n1.next = n2;
        n1.next = n2;
        n2.next = n3;
        /*Creating linked objects of Nodes directly without seperate creation and linking. */
        n3.next = new Node(40);
        n3.next.next = new Node(50);
        n3.next.next.next = new Node(70);
        /*Node object start, stores the reference to the first node. */
        Node start = n1;
        /*
         * When the start points at null, the printing stops.
         * The null address will come from the last node holding null in its next Node member.
         */
        while(start != null)
        {
            /*Print the data of the Node object referenced by start. */
            System.out.println(start.data);
            /*
             * Change the reference stored in start object to the 
             * reference stored in the next Node object member of
             * start.
             */
            start = start.next;
        }

        NodeGeneric<String> N1 = new NodeGeneric<>("hello");
        N1.next = new NodeGeneric<>("hey");

        NodeGeneric<String> startGeneric = N1;
        /*
         * When the start points at null, the printing stops.
         * The null address will come from the last node holding 
         * null in its next Node member.
         */
        while(startGeneric != null)
        {
            /*Print the data of the Node object referenced by start. */
            System.out.println(startGeneric.data);
            /*
             * Change the reference stored in start object to the 
             * reference stored in the next Node object member of
             * start.
             */
            startGeneric = startGeneric.next;
        }

    }  
}
