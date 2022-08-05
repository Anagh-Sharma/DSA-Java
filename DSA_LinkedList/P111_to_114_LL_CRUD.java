package DSA_LinkedList;

class NodeCRUD<T>
{
    /*To store the data of a fixed type. Linked lists store data of homogenous type. */
    T data;

    /*Object of Node, used to store the reference of the next Node object. */
    NodeCRUD<T> next;

    public NodeCRUD(T data)
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
 * Generic class used for using type parameters for NodeCRUDE class objects.
 */
public class P111_to_114_LL_CRUD<T>
{
    /*
     * NodeCRUD object start used to store reference 
     * of the first node of the Linked List created 
     * by chaining NodeCRUD objects.
     */
    private NodeCRUD<T> start;

    int get_count() 
    {
        /*NodeCRUD object stores reference of start. */
        NodeCRUD<T> temp = start;
        int counter = 0;
        /*
         * The NodeCRUD objects liked to start are traversed 
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

    void insertAtBeg(NodeCRUD<T> node) {
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
         * Else:
         * The reference of current start is stored 
         * in the next member of the current NodeCRUD 
         * object.
         */
        node.next = start;
        /*
         * The reference of the current NodeCRUD 
         * object is stored in start.
         */
        start = node;
    }

    void insertAtEnd(NodeCRUD<T> node) {
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
        NodeCRUD<T> current = start;
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

    void insertAtMid(NodeCRUD<T> node, int pos) 
    {
        /*Find the length of the linked list. */
        int count = get_count();
        /*If position for insertion exceeds length of the linked list: Throw exception */
        if(pos > count) {
            throw new RuntimeException("Position is not valid...");
        }
        
        /*
         * If start stores null pointer: 
         * The current NodeCRUD object is 
         * first to be stored and so its 
         * reference is is copied to start.
         */
        if(start == null) {
            start = node;
            return;
        }
        /*
         * If the NodeCRUD object is to be 
         * inserted at the beginning, the 
         * appropriate function call is 
         * made.
         */
        if(pos == 0) {
            insertAtBeg(node);
        }

        /*
         * If the position is greater than 1.
         * Traverse the linked list to the NodeCRUD 
         * object at position - 1.
         * - Store that NodeCRUD object's next reference 
         *   in the current NodeCRUD object's next.
         * - Store the current NodeCRUD object's reference 
         *   in that that NodeCRUD object's next reference.
         */
        if(pos > 1) 
        {
            int i = 1;
            /*Copy start reference to temp. */
            NodeCRUD<T> temp = start;
            /*Traverse the LL using the temp reference until before position. */
            while(i < pos) 
            {
                temp = temp.next;
                i++;
            }
            node.next = temp.next;
            temp.next = node;
        }
    }

    void deleteInMid(int pos) {
        /*If start stores null reference, the LL is empty. */
        if(start == null) {
            System.out.println("List is Empty...");
            return;
        }
        
        /*
         * If position for deletion is 0.
         * Copy the reference stored in start's 
         * next member to start and return from 
         * function call.
         */
        NodeCRUD<T> temp = null;
        if(pos == 0) {
            temp = start.next;
            start = temp;
            return;
        }

        /*
         * Else: 
         * Traverse the linked list to the NodeCRUD 
         * object at position - 1.
         * Copy the reference stored in that NodeCRUD 
         * object's next's next member to this object's 
         * next.
         */
        int i = 1;
        temp = start;
        while(i < pos) {
            temp = temp.next;
            i++;
        }
        temp.next = temp.next.next;

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

        /*
         * Else:
         * Figuratively, reverse the direction of the pointers, i.e., 
         * instead of the first NodeCRUD object storing the reference 
         * of the second NodeCRUD object in its next member, the next 
         * member of the second NodeCRUD object will store the reference
         * of the first NodeCRUD object.
         * - store the reference of start node in currentNode NodeCRUD object.
         * - prevNode: create NodeCRUD object will null reference.
         * - nextNode: Store the reference of the next member of the currentNode.
         * - Store prevNode reference in the next member of the currentNode.
         * - Store currentNode reference in prevNode.
         * - store nextNode reference in currentNode
         */
        /*Initialize the currentNode to store the reference of start NodeCRUD object. */
        NodeCRUD<T> currentNode = start;
        /*Initialize the prevNode to store the reference null. */
        NodeCRUD<T> prevNode = null;// P -> null
        /*Iterate untill the reference stored in currentNode, isn't null. */
        while(currentNode != null) 
        {
            /*
             * Store the reference of the second object in nextNode object 
             * to save it for further iteration. The currentNode will be set 
             * the second node referenced by currentNode.next and saved in 
             * nextNode for th next iteration in the last line of the code 
             * block.
             */
            NodeCRUD<T> nextNode = currentNode.next;
            /*
             * - In the next member of currentNode store reference of the 
             *   first or prevNode.
             * - This reverses the pointer direction as, the next memeber of 
             *   currentNode is pointing at prevNode instead of the second 
             *   NodeCRUD object.
             *   For the first iteration prevNode is null.
             *   For the second iteration, the prevNode stores the
             *   reference for the first node.
             */
            currentNode.next = prevNode;

            /*
             * For the next iteration, re-referencing prevNode 
             * and currentNode to refere to the first and second  
             * nodes respectively.
             */
            /*
             * In prevNode store the reference of currentNode.
             * This stores the reference of the first or current 
             * NodeCRUD object in prevNode for the next iteration.
             */
            prevNode = currentNode;
            /*
             * In currentNode store the reference of nextNode.
             * For the first iteration:
             * This stores the reference of the second or next 
             * NodeCRUD object in currentNode for the next iteration.
             */
            currentNode = nextNode;
        }
        start = prevNode;
    }


    NodeCRUD<T> reverseIterateRec(NodeCRUD<T> currentNode, NodeCRUD<T> prevNode) 
    {
        if(currentNode == null) 
        {
            return prevNode;
        }
        NodeCRUD<T> nextNode = currentNode.next;
        currentNode.next = prevNode;
        return reverseIterateRec(nextNode, currentNode);
    }

    /*
     * Two NodeCRUD objects are used to complete this operation:
     * - Slow and Fast
     * - Both are initialized to store the reference of the start node.
     * - The Fast NodeCRUD object is incrementally changed to store the 
     *   reference of the next's next reference. So Fast: N1 -> N3.
     * - The Slow NodeCRUD object is incrementally changed to store the 
     *   reference of the next's reference, So N1 -> N2.
     * - Since, Fast incrementally jumps two nodes and Slow jumps one node,
     *   when Fast reaches the last node, Slow reaches the middle node.
     */
    void midPoint() 
    {
        NodeCRUD<T> slow;
        NodeCRUD<T> fast;
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
        NodeCRUD<T> temp = start;
        for(int i = 1; i < x; i++) {
            temp = temp.next;
        }
        System.out.println("Kth Node  Data is : " + temp.data);
    }

    /*
     * Two NodeCRUD objects are used to complete this operation:
     * - p and q, both are initialized to store the reference of the start node.
     * - p object reference is traversed till k.
     * - Both p and q are traversed till p reaches the last node.
     * - When p reaches the last node, q reaches node at number
     *   k counted from the end, because both p and q references
     *   are changed equal number of times and when p reaches the 
     *   end q has k nodes left to reach the end.
     */
    void findKthFromEndApproach_2(int k) {
        NodeCRUD<T> p;
        NodeCRUD<T> q;
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
        NodeCRUD<T> temp = start;
        while(temp != null) 
        {
            System.out.println(temp.data);
            temp = temp.next;
        }
    }
    public static void main(String[] args) 
    {
        P111_to_114_LL_CRUD<Integer> operations = new P111_to_114_LL_CRUD<>();
        operations.insertAtBeg(new NodeCRUD<Integer>(12));
        operations.insertAtBeg(new NodeCRUD<Integer>(14));
        operations.insertAtBeg(new NodeCRUD<Integer>(17));
        operations.insertAtEnd(new NodeCRUD<Integer>(11));
        operations.insertAtEnd(new NodeCRUD<Integer>(49));
        operations.insertAtEnd(new NodeCRUD<Integer>(51));
        operations.insertAtMid(new NodeCRUD<Integer>(100), 3);

        operations.display();

        operations.deleteInMid(3);
        System.out.println("List After Deletion at 3rd index...");
        operations.display();

        System.out.println("List After Reverse Iteration...");
        operations.reverseIterate();
        operations.display();

        System.out.println("Mid Point....");
        operations.midPoint();

        System.out.println("============================");
        operations.display();

        operations.findKthFromEnd(3);
        operations.findKthFromEndApproach_2(3);
    }
}
