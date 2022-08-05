package DSA_LinkedList;

/*
 * - Traverse till the middle of the linked list.
 * - Save the reference for the next member of the 
 *   middle node: secondHalfStart.
 * - Make first half: Store the reference of start 
 *   in the next member of middle.
 * - Make second half:
 * - Iterate from secondHalfStart to the node whose 
 *   next member is start.
 * - Store the reference of secondHalfStart in this 
 *   node’s next member.
 */

class NodeCircle
{
    /*To store the data of a fixed type. Linked lists store data of homogenous type. */
    int data;

    /*Object of Node, used to store the reference of the next Node object. */
    NodeCircle next;

    public NodeCircle(int data)
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

public class P121_SplitCircularLL 
{
    private NodeCircle start;

    int get_count() 
    {
        /*NodeCircle object stores reference of start. */
        NodeCircle temp = start;
        if(start == null)
            return 0;
        /*Initialized at 1 to count the start node itself */
        int counter = 1;
        /*
         * The NodeCircle objects liked to start are traversed 
         * and counter is incremented untill the next link 
         * is the one pointing at null.
         */
        while(temp.next != start) 
        {
            temp = temp.next;
            counter++;
        }
        return counter;
    }

    void insertAtEnd(NodeCircle node) {
        /*
         * If the start points at null, then the NodeCircle 
         * object being inserted is the first, therefore 
         * its reference is stored in start and function 
         * returns.
         */
        if(start == null) 
        {
            start = node;
            return;
        }
        /*
         * If the next points at null, then the NodeCircle 
         * object being inserted is the second, therefore 
         * its reference is stored in next of start and 
         * function returns.
         */
        if(start.next == null) {
            node.next = start;
            start.next = node;
            return;
        }
        
        /*
         * Else: Search for the last NodeCircle 
         * object.
         * Traverse the entire linked list by 
         * traversing the NodeCircle objects 
         * stored in the next object members 
         * to search for the NodeCircle object 
         * with next member storing start.
         */
        NodeCircle current = start;
        while(current.next != start) 
        {
            current = current.next;
        }
        /*
         * Store the reference of the current 
         * NodeCircle object in the next member 
         * of the NodeCircle object whose next 
         * has start reference stored in it 
         * searched in the last step.
         */
        current.next = node;
        node.next = start;
    }

    static void splitCircularLL(P121_SplitCircularLL original, P121_SplitCircularLL firstHalf, P121_SplitCircularLL secondHalf)
    {
        /*Find the middle node */
        NodeCircle slow = original.start;
        NodeCircle fast = original.start;
        do
        {
            fast = fast.next.next;
            slow = slow.next;
        }while(fast != original.start && fast.next != original.start);

        NodeCircle middle = slow;

        /*Make the first half circular linked list. */
        NodeCircle temp = original.start;
        while(temp != middle.next)
        {
            firstHalf.insertAtEnd(new NodeCircle (temp.data));
            temp = temp.next;
        }

        /*Make the first half circular linked list. */
        temp = middle.next;
        while(temp != original.start)
        {
            secondHalf.insertAtEnd(new NodeCircle (temp.data));
            temp = temp.next;
        }
    }

    void display() 
    {
        NodeCircle temp = start;
        /*Print the start node. */
        System.out.print(temp.data+", ");
        /*Print the rest of the nodes. */
        while(temp.next != start) 
        {
            System.out.print(temp.next.data+", ");
            temp = temp.next;
        }
    }

    public static void main(String[] args) {
        P121_SplitCircularLL original = new P121_SplitCircularLL();
        P121_SplitCircularLL firstHalf = new P121_SplitCircularLL();
        P121_SplitCircularLL secondHalf = new P121_SplitCircularLL();

        original.insertAtEnd(new NodeCircle(1));
        original.insertAtEnd(new NodeCircle(2));
        original.insertAtEnd(new NodeCircle(3));
        original.insertAtEnd(new NodeCircle(4));
        original.insertAtEnd(new NodeCircle(5));
        original.insertAtEnd(new NodeCircle(6));
        original.insertAtEnd(new NodeCircle(7));

        System.out.println("The circular linked list:");
        original.display();
        System.out.println();

        splitCircularLL(original, firstHalf, secondHalf);

        System.out.println("The first circular linked list after split:");
        firstHalf.display();
        System.out.println();

        System.out.println("The first circular linked list after split:");
        secondHalf.display();
        System.out.println();

    }
}
