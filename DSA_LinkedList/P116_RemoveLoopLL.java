package DSA_LinkedList;

class NodeRemoveLoop<T>
{
    /*To store the data of a fixed type. Linked lists store data of homogenous type. */
    T data;

    /*Object of Node, used to store the reference of the next Node object. */
    NodeRemoveLoop<T> next;

    public NodeRemoveLoop(T data)
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

public class P116_RemoveLoopLL<T>
{
    private NodeRemoveLoop<T> start;

    void insertAtEnd(NodeRemoveLoop<T> node) {
        /*
         * If the start points at null, then the NodeCRUD 
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
         * If the next points at null, then the NodeCRUD 
         * object being inserted is the second, therefore 
         * its reference is stored in next of start and 
         * function returns.
         */
        if(start.next == null) {
            start.next = node;
            return;
        }
        
        /*
         * Else: Search for the last NodeCRUD 
         * object.
         * Traverse the entire linked list by 
         * traversing the NodeCRUD objects 
         * stored in the next object members 
         * to search for the NodeCRUD object 
         * with next member storing null.
         */
        NodeRemoveLoop<T> current = start;
        while(current.next != null) {
            current = current.next;
        }
        /*
         * Store the reference of the current 
         * NodeCRUD object in the next member 
         * of the NodeCRUD object whose next 
         * has null reference stored in it 
         * searched in the last step.
         */
        current.next = node;
    }

    void makeLoop(int position)
    {
        NodeRemoveLoop<T> loopEnd = start;
        int counter = 0;
        while(counter < position-1)
        {
            loopEnd = loopEnd.next;
            counter++;
        }

        NodeRemoveLoop<T> temp = loopEnd;
        while(temp.next != null)
        {
            temp = temp.next;
        }

        temp.next = loopEnd;
    }

    boolean detectLoopFloyd()
    {
        NodeRemoveLoop<T> slow = start;
        NodeRemoveLoop<T> fast = start;

        while(fast.next != null)
        {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast)
                return true;
        }
        // System.out.println(slow.data);
        // System.out.println(fast.data);
        return false;
    }

    void removeLoopFloyd()
    {
        NodeRemoveLoop<T> slow = start;
        NodeRemoveLoop<T> fast = start;

        while(fast.next != null)
        {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast)
                break;
        }

        /*
         * Measure the length of the loop.
         * Increment slow until it again
         * reaches fast after travelling 
         * a circular path.
         */
        int length = 0;
        while(slow.next != null)
        {
            slow = slow.next;
            length++;
            if(slow == fast)
            {
                System.out.println("Length of the loop: "+length);
                break;
            }
        }

        /*
         * Initialize the positions of 
         * the teo pointers as:
         * ptr1: start
         * ptr2: distance "length" from 
         *       start.
         */
        NodeRemoveLoop<T> ptr1 = start;
        NodeRemoveLoop<T> ptr2 = start;
        for(int i=0; i<length; i++)
        {
            ptr2 = ptr2.next;
        }

        /*
         * Increments the two pointers 
         * until they both point at the 
         * same node. This happens when: 
         * - ptr1 has reached the node 
         *   that is the first and last 
         *   node of the loop.
         * - ptr2 at distance from ptr1 
         *   equal to the length of the 
         *   loop points at same node.
         */
        while(ptr1.next != null)
        {
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
            /*
             * When the next member of both 
             * ptr1 and ptr2 point at the same 
             * node, set the next member of 
             * ptr2 to null.
             */
            if(ptr1.next == ptr2.next)
            {
                System.out.println("Start of the loop: "+ptr1.next.data);
                ptr2.next = null;
                break;
            }
        }
    }

    void display() 
    {
        NodeRemoveLoop<T> temp = start;
        while(temp != null) 
        {
            System.out.println(temp.data);
            temp = temp.next;
        }
    }

    public static void main(String[] args) {
        P116_RemoveLoopLL<Integer> operations = new P116_RemoveLoopLL<>();
        operations.insertAtEnd(new NodeRemoveLoop<Integer>(1));
        operations.insertAtEnd(new NodeRemoveLoop<Integer>(2));
        operations.insertAtEnd(new NodeRemoveLoop<Integer>(3));
        operations.insertAtEnd(new NodeRemoveLoop<Integer>(4));
        operations.insertAtEnd(new NodeRemoveLoop<Integer>(5));
        operations.insertAtEnd(new NodeRemoveLoop<Integer>(6));
        operations.insertAtEnd(new NodeRemoveLoop<Integer>(7));
        operations.insertAtEnd(new NodeRemoveLoop<Integer>(8));
        operations.makeLoop(4);

        if(operations.detectLoopFloyd())
            operations.removeLoopFloyd();
        
        operations.display();
    }
}
