package DSA_LinkedList;
/*
 * - When a new element is to be cached:
 * - If its not in the list:
 *   - If the number of items present is less than 
 *     capacity:
 *     Put the element in the tail
 *   - Else:
 *     Evict the the head element and put the new 
 *     element in the tail.
 * 
 * - Else if the element is in the list:
 *   - Remove the element in its position and put 
 *     the element in the tail.
 * 
 * - For checking if an element is present and if present, 
 *   storing its node's reference can be done using a hash map.
 */

import java.util.HashMap;

class NodeLRU
{
    int data;
    NodeLRU prev;
    NodeLRU next;
    public NodeLRU(int data)
    {
        this.data = data;
        this.prev = null;        
        this.next = null;
    }
}

public class P125_LRU_LL {
    int capacity, items;
    NodeLRU head, tail;
    HashMap<Integer, NodeLRU> mapDataNode = new HashMap<>();

    NodeLRU cacheDataLRU(int data)
    {
        System.out.println("New data:   "+data);
        /*
         * If the element is in the list:
         * - Remove the element in its position 
         *   and put the element in the tail.
         */
        if(mapDataNode.containsKey(data))
        {
            /*Copying reference of the node containing the element. */
            NodeLRU oldNode = mapDataNode.get(data);
            /*
             * Copying the references for nodes preceding 
             * and succeeding the node containing the 
             * element.
             */
            NodeLRU oldNodePrev = oldNode.prev;
            NodeLRU oldNodeNext = oldNode.next;

            /*Removing the node with the element from its current position. */

            /*If the node is the head node, its previous node is null. */
            if(oldNodePrev == null)
            {
                /*Setting the previously second node as the new head node. */
                head = oldNodeNext;           
                /*Setting the new head node's previous reference to null. */
                head.prev = null;
                /*Setting the head node's next reference to null. */
                oldNode.next = null;
                                
            }
            /*If the node is the not head node. */
            else
            {
                /*
                 * Linking the previous node with the 
                 * next and the next with the previous.
                 */
                oldNodePrev.next = oldNodeNext;
                oldNodeNext.prev = oldNodePrev;
                /*Removing next and previous references from the node. */
                oldNode.prev = null;
                oldNode.next = null;
            }
            /*Putting the node with the element in the tail. */
            tail.next = oldNode;
            oldNode.prev = tail;
            tail = oldNode;
        }
        /*
         * If the element is not in the list:
         * - If the number of items present is less than 
         *   capacity:
         *   - Put the element in the tail
         * - Else:
         *   - Evict the the head element and put the new element 
         *     in the tail.
         */
        else
        {
            /*Make a new with the element as the data */
            NodeLRU newNode = new NodeLRU(data);
            /*Map the element to the new node in the map.*/
            mapDataNode.put(data, newNode);

            /*
             * If the capacity is not full simply link the 
             * new node at the tail. 
             */
            if(items < capacity)
            {
                /*If the node is first, make it head and tail. */
                if(head == null)
                {
                    head = tail = newNode;
                    items++;
                }
                /*
                 * Else:
                 * - Copy the reference of the 
                 *   new node to the next member of tail 
                 *   and the reference of tail in the 
                 *   previous member of the new node.
                 * - Make the new node the new tail.
                 * - Increment the count of items.
                 */
                else
                {
                    tail.next = newNode;
                    newNode.prev = tail;
                    tail = newNode;
                    items++;
                }
            }
            /*
             * Else: When the is full: EVICT THE HEAD NODE
             * - The new head is the next reference of the 
             *   old head.
             * - The head's associated entry is deleted from 
             *   the map.
             * - The new head's previous is set to null to 
             *   delete the reference for the old head.
             * - Copy the reference of the 
             *   new node to the next member of tail 
             *   and the reference of tail in the 
             *   previous member of the new node. 
             * - Make the new node the new tail. 
             */
            else
            {
                mapDataNode.remove(head.data);
                head = head.next;
                head.prev = null;

                tail.next = newNode;
                newNode.prev = tail;
                tail = newNode;
            }
        }
        return head;
    }

    /*Display: data, capacity, items */
    void display()
    {
        NodeLRU traverse = head;
        System.out.println("The current cache data by most recent usage (Ascending):");
        while(traverse != null)
        {
            System.out.print(traverse.data+", ");
            traverse = traverse.next;
        }
        System.out.println();
        System.out.println("Capacity: "+capacity+"  ;  "+"Items: "+items);
        System.out.println();
    }

    public static void main(String[] args) 
    {
        P125_LRU_LL cacheList = new P125_LRU_LL();
        cacheList.capacity = 4;
        cacheList.items = 0;

        cacheList.cacheDataLRU(1);
        cacheList.display();

        cacheList.cacheDataLRU(2);
        cacheList.display();

        cacheList.cacheDataLRU(3);
        cacheList.display();

        cacheList.cacheDataLRU(4);
        cacheList.display();

        cacheList.cacheDataLRU(1);
        cacheList.display();

        cacheList.cacheDataLRU(5);
        cacheList.display();

        cacheList.cacheDataLRU(6);
        cacheList.display();

        cacheList.cacheDataLRU(5);
        cacheList.display();

    }
}
