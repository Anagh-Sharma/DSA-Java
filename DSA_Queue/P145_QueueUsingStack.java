package DSA_Queue;

import java.util.Stack;

class QueueUsingStack<T>
{
    Stack<T> stackMain = new Stack<>();
    Stack<T> stackSecond = new Stack<>();
    int size = 0;
    int items = 0;

    QueueUsingStack(int size)
    {
        this.size = size;
    }

    boolean isEmpty()
    {
        return items == 0;
    }

    void enqueue(T data)
    {
        items++;
        if(items <= size)
        {
            /*Pop and push all elements from main stack to the secondary stack. */
            while(!stackMain.empty())
                stackSecond.push(stackMain.pop());       
        
            /*Push the element onto the main stack. */
            stackMain.push(data);

            /*Pop and push all elements from second stack to the main stack. */
            while(!stackSecond.empty())
                stackMain.push(stackSecond.pop()); 
        }
    }

    T dequeue()
    {
        items--;
        return stackMain.pop();
    }

    void print()  {
        System.out.println("Printing and dequeueing elements.");
        while(!isEmpty()) 
        {
            System.out.println("Dequeueing: "+dequeue());
        }
    }
}

public class P145_QueueUsingStack {
    public static void main(String[] args) {
        QueueUsingStack<Integer> q = new QueueUsingStack<>(5);
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
