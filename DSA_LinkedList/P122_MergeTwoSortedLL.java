package DSA_LinkedList;

class NodeListsMerge
{
    int data;
    NodeListsMerge next;
    public NodeListsMerge(int data) 
    {
        this.data = data;
        this.next = null;
    }
}

public class P122_MergeTwoSortedLL 
{
    NodeListsMerge start, tail;

    void insertAtEnd(NodeListsMerge node) {
        if(start == null) 
        {
            start = tail = node;
            return;
        }

        if(start.next == null) {
            start.next = node;
            tail = node;
            return;
        }
        tail.next = node;
        tail = node;
    }

    /*
     * The function creats a result linked lists by
     * creating new node objects and appending them 
     * to the new a passed linked list objects.
     * - Deep copy
     */
    static void mergeSortedLL(P122_MergeTwoSortedLL resultLL, P122_MergeTwoSortedLL firstLL, P122_MergeTwoSortedLL secondLL)
    {
        NodeListsMerge traverseFirst = firstLL.start;
        NodeListsMerge traverseSecond = secondLL.start;
        
        /*
         * Traverse the lists and add the data of their 
         * nodes to the result linked lists according 
         * to the conditions defined in the loop. 
         */
        while(traverseFirst != null || traverseSecond != null)
        {
            /* 
             * If the nodes of the first linked list are exhausted, 
             * add the remaining nodes of the second linked list. 
             */
            if(traverseFirst == null)
            {
                resultLL.insertAtEnd(new NodeListsMerge (traverseSecond.data));
                traverseSecond = traverseSecond.next;
            }
            /* 
             * If the nodes of the second linked list are exhausted, 
             * add the remaining nodes of the first linked list. 
             */
            else if(traverseSecond == null)
            {
                resultLL.insertAtEnd(new NodeListsMerge (traverseFirst.data));
                traverseFirst = traverseFirst.next;
            }
            /* 
             * If the data of a node of the first linked list 
             * is smaller or equal to the the data of node of 
             * the second linked list, add the data of the 
             * first liked list to the result linked list. 
             */
            else if(traverseFirst.data <= traverseSecond.data)
            {
                resultLL.insertAtEnd(new NodeListsMerge (traverseFirst.data));
                traverseFirst = traverseFirst.next;
            }
            /* 
             * Else the data of a node of the second linked 
             * list is greater than the the data of node of 
             * the first linked list, and the data of the 
             * second liked list is added to the result 
             * linked list. 
             */
            else
            {
                resultLL.insertAtEnd(new NodeListsMerge (traverseSecond.data));
                traverseSecond = traverseSecond.next;
            }
        }
    }

    /*
     * The function creates a result linked lists by
     * manipulation of the node object references 
     * of the source lists.
     * - Not a deep copy
     * - Since this is not a deep copy, the operation 
     *   to build the result linked list modify the 
     *   orginal linked lists.
     */
    static NodeListsMerge mergeSorted(P122_MergeTwoSortedLL firstLL, P122_MergeTwoSortedLL secondLL)
    {
        NodeListsMerge traverseFirst = firstLL.start;
        NodeListsMerge traverseSecond = secondLL.start;
        NodeListsMerge resultHead = null;
        NodeListsMerge resultTail = null;
        /*The first node of the result is smaller first node of the two input linked lists */
        if(traverseFirst.data <= traverseSecond.data)
        {
            resultHead = traverseFirst;
            traverseFirst = traverseFirst.next;
        }
        else
        {
            resultHead = traverseSecond;
            traverseSecond = traverseSecond.next;
        }
        /*When there is only one node in the list, head and tail are the same. */
        resultTail = resultHead;

        /*
         * Further nodes are added to the result from the 
         * tail according to the conditions in the loop. 
         */
        /*
         * Traverse the lists and add the data of their 
         * nodes to the result linked lists according 
         * to the conditions defined in the loop. 
         */
        while(traverseFirst != null || traverseSecond != null)
        {
            /* 
             * If the nodes of the first linked list are exhausted, 
             * add the remaining nodes of the second linked list. 
             */
            if(traverseFirst == null)
            {
                resultTail.next = traverseSecond;
                resultTail = traverseSecond;
                traverseSecond = traverseSecond.next;
            }
            /* 
             * If the nodes of the second linked list are exhausted, 
             * add the remaining nodes of the first linked list. 
             */
            else if(traverseSecond == null)
            {
                resultTail.next = traverseFirst;
                resultTail = traverseFirst;
                traverseFirst = traverseFirst.next;
            }
            /* 
             * If the data of a node of the first linked list 
             * is smaller or equal to the the data of node of 
             * the second linked list, add the data of the 
             * first liked list to the result linked list. 
             */
            else if(traverseFirst.data <= traverseSecond.data)
            {
                resultTail.next = traverseFirst;
                resultTail = traverseFirst;
                traverseFirst = traverseFirst.next;
            }
            /* 
             * Else the data of a node of the second linked 
             * list is greater than the the data of node of 
             * the first linked list, and the data of the 
             * second liked list is added to the result 
             * linked list. 
             */
            else
            {
                resultTail.next = traverseSecond;
                resultTail = traverseSecond;
                traverseSecond = traverseSecond.next;
            }
        }
        return resultHead;
    }

    void display() 
    {
        NodeListsMerge temp = start;
        while(temp != null) 
        {
            System.out.print(temp.data+", ");
            temp = temp.next;
        }
        System.out.println();
    }

    public static void main(String[] args) 
    {
        // P122_MergeTwoSortedLL resultLL = new P122_MergeTwoSortedLL();
        P122_MergeTwoSortedLL firstLL = new P122_MergeTwoSortedLL();
        P122_MergeTwoSortedLL secondLL = new P122_MergeTwoSortedLL();

        firstLL.insertAtEnd(new NodeListsMerge (4));
        firstLL.insertAtEnd(new NodeListsMerge (8));
        firstLL.insertAtEnd(new NodeListsMerge (15));
        firstLL.insertAtEnd(new NodeListsMerge (19));
        secondLL.insertAtEnd(new NodeListsMerge (7));
        secondLL.insertAtEnd(new NodeListsMerge (9));
        secondLL.insertAtEnd(new NodeListsMerge (10));
        secondLL.insertAtEnd(new NodeListsMerge (16));

        System.out.println("The first sorted linked list:");
        firstLL.display();
        System.out.println("The second sorted linked list:");
        secondLL.display();

        System.out.println("The merged sorted linked list:");
        NodeListsMerge resultHead = mergeSorted(firstLL, secondLL);
        while(resultHead != null)
        {
            System.out.print(resultHead.data+", ");
            resultHead = resultHead.next;
        }
        System.out.println();


        // mergeSortedLL(resultLL, firstLL, secondLL);
        // System.out.println("The merged sorted linked list:");
        // resultLL.display();
    }
}
