package DSA_LinkedList;

class NodeDetectLoop<T>
{
    /*To store the data of a fixed type. Linked lists store data of homogenous type. */
    T data;

    /*Object of Node, used to store the reference of the next Node object. */
    NodeDetectLoop<T> next;

    public NodeDetectLoop(T data)
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

public class P115_DetectLoopLL<T>
{
    private NodeDetectLoop<T> start;

    void insertAtEnd(NodeDetectLoop<T> node) {
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
        NodeDetectLoop<T> current = start;
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
        NodeDetectLoop<T> loopEnd = start;
        int counter = 0;
        while(counter < position-1)
        {
            loopEnd = loopEnd.next;
            counter++;
        }

        NodeDetectLoop<T> temp = loopEnd;
        while(temp.next != null)
        {
            temp = temp.next;
        }

        temp.next = loopEnd;
    }

    boolean detectLoopFloyd()
    {
        NodeDetectLoop<T> slow = start;
        NodeDetectLoop<T> fast = start;

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

    void display() 
    {
        NodeDetectLoop<T> temp = start;
        while(temp != null) 
        {
            System.out.println(temp.data);
            temp = temp.next;
        }
    }

    public static void main(String[] args) {
        P115_DetectLoopLL<Integer> operations = new P115_DetectLoopLL<>();
        operations.insertAtEnd(new NodeDetectLoop<Integer>(1));
        operations.insertAtEnd(new NodeDetectLoop<Integer>(2));
        operations.insertAtEnd(new NodeDetectLoop<Integer>(3));
        operations.insertAtEnd(new NodeDetectLoop<Integer>(4));
        operations.insertAtEnd(new NodeDetectLoop<Integer>(5));
        operations.insertAtEnd(new NodeDetectLoop<Integer>(6));
        operations.insertAtEnd(new NodeDetectLoop<Integer>(7));
        operations.insertAtEnd(new NodeDetectLoop<Integer>(8));
        operations.makeLoop(4);
        System.out.println(operations.detectLoopFloyd());
    }
}
