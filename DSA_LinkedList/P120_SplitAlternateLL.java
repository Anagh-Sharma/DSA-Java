package DSA_LinkedList;
/*
 * - Two node objects a and b.
 * - Node a: initialized with reference of start.
 * - Node b: initialized with reference of start -> next.
 * - Traverse the linked list with step size two using the 
 *   node objects a and b, simultaneously concatenating the 
 *   nodes to a and b.
 */

class NodeListSplit
{
    int data;
    NodeListSplit next;
    public NodeListSplit(int data) 
    {
        this.data = data;
        this.next = null;
    }
}

public class P120_SplitAlternateLL
{
    NodeListSplit start;

    void insertAtEnd(NodeListSplit node) {
        if(start == null) 
        {
            start = node;
            return;
        }

        if(start.next == null) {
            start.next = node;
            return;
        }
        
        NodeListSplit current = start;
        while(current.next != null) {
            current = current.next;
        }
        current.next = node;
    }

    static void splitAlternate(P120_SplitAlternateLL original, P120_SplitAlternateLL oddNodes, P120_SplitAlternateLL evenNodes)
    {
        NodeListSplit traversalOdd = original.start;
        NodeListSplit traversalEven = original.start.next;
        
        while(true)
        {
            oddNodes.insertAtEnd(new NodeListSplit (traversalOdd.data));
            /*
             * - With an even source list, traversalOdd will be null after assignment.
             * - With an odd source list, traversalOdd's next will be null, and the loop stops.
             */
            if(traversalOdd.next != null)
                traversalOdd = traversalOdd.next.next;
            else
                break;
            
            evenNodes.insertAtEnd(new NodeListSplit (traversalEven.data));
            /*
             * - With an even source list, traversalEven's next will be null, and the loop stops.
             * - With an odd source list, traversalEven will be null after assignment.
             */
            if(traversalEven.next != null)
                traversalEven = traversalEven.next.next;
            else
                break;
        }
    }

    void display() 
    {
        NodeListSplit temp = start;
        while(temp != null) 
        {
            System.out.print(temp.data+", ");
            temp = temp.next;
        }
        System.out.println();
    }

    public static void main(String[] args) 
    {
        P120_SplitAlternateLL original = new P120_SplitAlternateLL();
        P120_SplitAlternateLL oddNodes = new P120_SplitAlternateLL();
        P120_SplitAlternateLL evenNodes = new P120_SplitAlternateLL();

        original.insertAtEnd(new NodeListSplit (1));
        original.insertAtEnd(new NodeListSplit (2));
        original.insertAtEnd(new NodeListSplit (3));
        original.insertAtEnd(new NodeListSplit (4));
        original.insertAtEnd(new NodeListSplit (5));
        original.insertAtEnd(new NodeListSplit (6));
        original.insertAtEnd(new NodeListSplit (7));
        original.insertAtEnd(new NodeListSplit (8));

        splitAlternate(original, oddNodes, evenNodes);
        System.out.println("The original linked list:");
        original.display();
        System.out.println("The first linked list after split:");
        oddNodes.display();
        System.out.println("The second linked list after split:");
        evenNodes.display();
    }
}
