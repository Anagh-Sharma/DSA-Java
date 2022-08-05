package DSA_LinkedList;

class NodeDoubly<T>
{
    /*To store the data of a fixed type. Linked lists store data of homogenous type. */
    T data;

    /*Object of Node, used to store the reference of the previous Node object. */
    NodeDoubly<T> prev;
    /*Object of Node, used to store the reference of the next Node object. */
    NodeDoubly<T> next;

    public NodeDoubly(T data)
    {
        /*
         * This keyword is a reference to the memebers 
         * of the object whose method or constructor is 
         * called.
         */
        this.data = data;
        /*The reference to the previous Node is by default set to null. */
        this.prev = null;        
        /*The reference to the next Node is by default set to null. */
        this.next = null;
    }
}

public class P117_1_DoublyLLCRUD<T>
{
    /*
     * NodeDoubly object start used to store reference 
     * of the first node of the Linked List created 
     * by chaining NodeDoubly objects.
     */
    private NodeDoubly<T> start;

    int get_count() 
    {
        /*NodeDoubly object stores reference of start. */
        NodeDoubly<T> temp = start;
        int counter = 0;
        /*
         * The NodeDoubly objects liked to start are traversed 
         * and counter is incremented untill the next link 
         * is the one pointing at null.
         */
        while(temp != null) 
        {
            temp = temp.next;
            counter++;
        }
        return counter;
    }

    void insertAtBeg(NodeDoubly<T> node) {
        /*
         * If the start points at null, then the NodeDoubly 
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
         * Else:
         * The reference of current start is stored 
         * in the next member of the current NodeDoubly 
         * object.
         */
        start.prev = node;
        node.next = start;
        /*
         * The reference of the current NodeDoubly 
         * object is stored in start.
         */
        start = node;
    }

    void insertAtEnd(NodeDoubly<T> node) {
        /*
         * If the start points at null, then the NodeDoubly 
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
         * If the next points at null, then the NodeDoubly 
         * object being inserted is the second, therefore 
         * its reference is stored in next of start and 
         * function returns.
         */
        if(start.next == null) {
            start.next = node;
            node.prev = start;
            return;
        }
        
        /*
         * Else: Search for the last NodeDoubly 
         * object.
         * Traverse the entire linked list by 
         * traversing the NodeDoubly objects 
         * stored in the next object members 
         * to search for the NodeDoubly object 
         * with next member storing null.
         */
        NodeDoubly<T> current = start;
        while(current.next != null) {
            current = current.next;
        }
        /*
         * Store the reference of the current 
         * NodeDoubly object in the next member 
         * of the NodeDoubly object whose next 
         * has null reference stored in it 
         * searched in the last step.
         */
        current.next = node;
        node.prev = current;
    }

    void insertAtMid(NodeDoubly<T> node, int pos) 
    {
        /*Find the length of the linked list. */
        int count = get_count();
        /*If position for insertion exceeds length of the linked list: Throw exception */
        if(pos > count) {
            throw new RuntimeException("Position is not valid...");
        }
        
        /*
         * If start stores null pointer: 
         * The current NodeDoubly object is 
         * first to be stored and so its 
         * reference is is copied to start.
         */
        if(start == null) {
            start = node;
            return;
        }
        /*
         * If the NodeDoubly object is to be 
         * inserted at the beginning, the 
         * appropriate function call is 
         * made.
         */
        if(pos == 0) {
            insertAtBeg(node);
        }

        /*
         * If the position is greater than 1.
         * Traverse the linked list to the NodeDoubly 
         * object at position - 1.
         * - Store that NodeDoubly object's next reference 
         *   in the current NodeDoubly object's next.
         * - Store the current NodeDoubly object's reference 
         *   in that that NodeDoubly object's next reference.
         */
        if(pos > 1) 
        {
            int i = 1;
            /*Copy start reference to temp. */
            NodeDoubly<T> temp = start;
            /*Traverse the LL using the temp reference until before position. */
            while(i < pos) 
            {
                temp = temp.next;
                i++;
            }
            /*Insert the node between temp and temp.next. */
            temp.next.prev = node;
            node.next = temp.next;
            temp.next = node;
            node.prev = temp;
        }
    }

    void deleteInMid(int pos) 
    {
        /*If start stores null reference, the LL is empty. */
        if(start == null) {
            System.out.println("List is Empty...");
            return;
        }
        
        /*
         * If position for deletion is 0.
         * Copy the reference stored in start's 
         * next member to start, set the previous 
         * pointer to null and return from the 
         * function call.
         */
        NodeDoubly<T> temp = null;
        if(pos == 0) 
        {
            temp = start.next;
            start = temp;
            start.prev = null;
            return;
        }

        /*
         * Else: 
         * Traverse the linked list to the NodeDoubly 
         * object at position - 1.
         * Copy the reference stored in that NodeDoubly 
         * object's next's next member to this object's 
         * next.
         */
        int i = 0;
        temp = start;
        while(i < pos-1) {
            temp = temp.next;
            i++;
        }
        /*Store the reference of the next to next node in current node's next. */
        temp.next = temp.next.next;
        /*Store the reference of the current node in the changed next node's previous. */
        temp.next.prev = temp;
    }

    void reverseIterate() {
        /*If start holds null reference, the list is empty. */
        if(start == null) {
            System.out.println("List is Empty...");
            return;
        }
        /*If start's next member holds null reference, the list has only one node. */
        if(start.next == null) {
            System.out.println("List contains only one item...");
            return;
        }

        NodeDoubly<T> fromLeft = start;
        NodeDoubly<T> fromRight = null;

        /*Iterate untill the reference stored in next member isn't null. */
        while(fromLeft.next != null) 
        {
            fromLeft = fromLeft.next;   
        }
        /*Store the reference pf the last node to iterate backwards from the last node. */
        fromRight = fromLeft;

        /*Iterate untill the reference stored in previous member isn't null. */        
        while(fromRight != null) 
        {
            System.out.print(fromRight.data+", ");
            fromRight = fromRight.prev;   
        }
        System.out.println();
    }

    /*
     * Two NodeDoubly objects are used to complete this operation:
     * - Slow and Fast
     * - Both are initialized to store the reference of the start node.
     * - The Fast NodeDoubly object is incrementally changed to store the 
     *   reference of the next's next reference. So Fast: N1 -> N3.
     * - The Slow NodeDoubly object is incrementally changed to store the 
     *   reference of the next's reference, So N1 -> N2.
     * - Since, Fast incrementally jumps two nodes and Slow jumps one node,
     *   when Fast reaches the last node, Slow reaches the middle node.
     */
    void midPoint() 
    {
        NodeDoubly<T> slow;
        NodeDoubly<T> fast;
        slow = fast = start;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            // System.out.println(slow.data + "," + fast.data);
        }
        System.out.println("Mid Point is : " + slow.data);
    }
    
    /*
     * The node linked before k nodes from the end is found from
     * traversing the linked list until n-k node, where n is the 
     * length of the linked list.
     */
    void findKthFromEnd(int k) {
        int n = get_count();
        int x = n-k+1;
        NodeDoubly<T> temp = start;
        for(int i = 1; i < x; i++) {
            temp = temp.next;
        }
        System.out.println("Kth Node Data is : " + temp.data);
    }

    /*
     * Two NodeDoubly objects are used to complete this operation:
     * - p and q, both are initialized to store the reference of the start node.
     * - p object reference is traversed till k.
     * - Both p and q are traversed till p reaches the last node.
     * - When p reaches the last node, q reaches node at number
     *   k counted from the end, because both p and q references
     *   are changed equal number of times and when p reaches the 
     *   end q has k nodes left to reach the end.
     */
    void findKthFromEndApproach_2(int k) {
        NodeDoubly<T> p;
        NodeDoubly<T> q;
        p = q = start;
        for(int i = 1; i <= k; i++) {
            p = p.next;
        }
        while(p != null) {
            p = p.next;
            q = q.next;
        }
        System.out.println("Kth Node data is : " + q.data);
    }

    
    void display() 
    {
        NodeDoubly<T> temp = start;
        while(temp != null) 
        {
            System.out.print(temp.data+ ", ");
            temp = temp.next;
        }
    }
    public static void main(String[] args) 
    {
        P117_1_DoublyLLCRUD<Integer> operations = new P117_1_DoublyLLCRUD<>();
        operations.insertAtEnd(new NodeDoubly<Integer>(1));
        operations.insertAtEnd(new NodeDoubly<Integer>(2));
        operations.insertAtEnd(new NodeDoubly<Integer>(3));
        operations.insertAtEnd(new NodeDoubly<Integer>(4));
        operations.insertAtEnd(new NodeDoubly<Integer>(5));
        operations.insertAtEnd(new NodeDoubly<Integer>(6));
        operations.insertAtEnd(new NodeDoubly<Integer>(7));
        operations.insertAtEnd(new NodeDoubly<Integer>(8));

        System.out.println("Original list: ");
        operations.display();

        System.out.println();
        operations.insertAtBeg(new NodeDoubly<Integer>(9));
        operations.display();


        System.out.println();
        System.out.println();
        operations.deleteInMid(3);
        System.out.println("List After Deletion at 3rd index: ");
        operations.display();

        System.out.println();
        System.out.println();
        System.out.println("List After Reverse Iteration: ");
        operations.reverseIterate();


        System.out.println();
        operations.display();

        System.out.println();
        operations.midPoint();
        operations.findKthFromEnd(3);
        operations.findKthFromEndApproach_2(3);
    }
}
