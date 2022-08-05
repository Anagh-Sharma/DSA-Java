package DSA_Queue;

class NodeQueue
{
    int data;
    NodeQueue next;
    NodeQueue(int data)
    {
        this.data = data;
        this.next = null;
    }
}

public class P147_QueueUsingLL {
    NodeQueue head = null;
    NodeQueue rear = null;
    int size;
    int items = 0;

    P147_QueueUsingLL(int size)
    {
        this.size = size;
    }

    int getSize()
    {
        return items;
    }

    boolean isEmpty()
    {
        return items == 0;
    }

    void enqueue(int data)
    {
        NodeQueue newNode = new NodeQueue(data);

        if(isEmpty())
        {
            head = rear = newNode;
            items++;
            System.out.println("Enqueuing: "+data+" Items: "+items+" Size:"+size);
            return;
        }
        else if(items+1 <= size)
        {
            rear.next = newNode;
            rear = newNode;
            items++;
            System.out.println("Enqueuing: "+data+" Items: "+items+" Size:"+size);
        }
        else
        {
            System.out.println("Queue is full !");
        }
    }

    int dequeue()
    {
        int data = head.data;
        head = head.next;
        items--;
        System.out.println("Dequeuing: "+data+" Items: "+items+" Size:"+size);
        return data;
    }

    void print()  
    {
        System.out.println("Printing and dequeueing elements.");
        while(!isEmpty()) 
        {
            dequeue();
        }
    }

    public static void main(String[] args) 
    {
        P147_QueueUsingLL q = new P147_QueueUsingLL(5);

        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);
        q.enqueue(5);
        q.dequeue();
        q.enqueue(6);
    
        q.print();
    }
}
