package DSA_Queue;

class Queue
{
    /*Array backed queue */
    int queueArray[];
    /*The head index is where the elements are dequeued from. */
    int head;
    /*The rear is the index where the elements are queued from. */
    int rear;
    /*The size maintains the record of the number of elements in the queue. */
    int size;
    /*Constructor of the Queue is passed the size of the array backing the queue. */
    Queue(int len) 
    {
        queueArray = new int[len];
        /*When the queue is empty, head and rear cannot hold any index. */
        head = rear = -1;
        size = 0;
    }

    int getSize() {
        return size;
    }

    boolean isEmpty() {
        return size == 0;
    }

    int getFront() {
        if(isEmpty()) {
            throw new RuntimeException("Queue is Empty...");
        }
        return queueArray[head];
    }

    void enqueue(int data) 
    {
        /*When inserting the first element: Head initialized to the first index. */
        if(isEmpty()) 
        {
            head = 0;
        }
        /*Rear and size are incremented before adding a new element. */
        rear++;
        size++;
        /*
         * When the rear index reaches beyond the indices of the array:
         * Throw exception: Queue is full.
         */
        if(rear == queueArray.length)
        {
            throw new RuntimeException("Queue is full...");
        }
        System.out.println("Enqueueing "+data+" at index "+rear);

        queueArray[rear] = data;
    }

    int dequeue() 
    {
        if(isEmpty()) 
        {
            throw new RuntimeException("Queue is Empty...");
        }
        /*Store the element at the head of the queue. */
        int temp = queueArray[head];

        /*Increment the head to the next element of the array. */
        head++;
        /*
         * Decrement size, to determine if the queue is empty.
         */
        size--;
        
        /*
         * When the size becomes 0, i.e., all elements have 
         * been dequeued, both head and rear are changed to 
         * -1.
         */
        if(size == 0) 
        {
            head = rear = -1;
        }
        return temp;
    }

    void print()  {
        System.out.println("Printing and dequeueing elements.");
        while(!isEmpty()) {
            System.out.println("Dequeueing: "+dequeue());
        }
    }
}

public class P144_QueueIntro {
    public static void main(String[] args) {
        Queue q = new Queue(5);
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);
        q.enqueue(5);

        q.print();

        q.enqueue(2);
        q.enqueue(3);

        q.print();

    }
}
