package DSA_Stack;

class NodeStack<T>
{
    T data;
    NodeStack<T> prev;
    NodeStack<T> next;
    public NodeStack(T data)
    {
        this.data = data;
        this.prev = null;
        this.next = null;
    }
}

public class P127_StackLLIntro<T> 
{
    int capacity;
    NodeStack<T> head = null;
    NodeStack<T> tail = null;
    int top = -1;

    int getSize(){
        return top;
    }

    boolean isEmpty(){
        return top == -1;
    }

    void push(T data){
        NodeStack<T> newElement = new NodeStack<>(data);
        if(isEmpty())
        {
            head = tail = newElement;
            top++;
        }
        else if(!(getSize() == capacity-1))
        {
            /*
             * Storing the reference of the new node in 
             * the current tail node's next member and
             * the tail's reference in the new node's 
             * previous member.
             */
            tail.next = newElement;
            newElement.prev = tail;

            /*
             * Storing the reference of the new node in 
             * tail as the new tail node.
             */
            tail = newElement;
        }
        else
            throw new RuntimeException("Stack is full !");
    }

    T pop(){
        if(isEmpty())
        {
            throw new RuntimeException("Stack is Empty...");
        }
        /*Save the last element for returning the value. */
        T removedData = tail.data;
        /*After popping the second last element is the new tail */

        /*Save the reference of the second last node. */
        NodeStack<T> newTail = tail.prev;
        /*Setting current tail's previous memeber to null. */
        tail.prev = null;
        /*Setting the new tail's next member as null. */
        newTail.next = null;
        /*Storing the reference of the new tail node in the tail object. */
        tail = newTail;
        return removedData;
    }

    T peek(){
        if(isEmpty())
        {
            throw new RuntimeException("Stack is Empty...");
        }
        /*Save the last element for returning the value. */
        T topData = tail.data;
        return topData;
    }

    public static void main(String[] args) {
        P127_StackLLIntro<String> ct = new P127_StackLLIntro<>();
        ct.capacity = 5;
        ct.push("hello");
        ct.push("hey");
        ct.push("hi");

        System.out.println(ct.pop());
        System.out.println(ct.peek());
    }
}
