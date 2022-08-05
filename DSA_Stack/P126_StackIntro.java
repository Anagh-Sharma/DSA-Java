package DSA_Stack;

import java.util.ArrayList;
import java.util.Stack;

public class P126_StackIntro<T>
{
    /*ArrayList Backed Stack */
    int capacity;
    int top;
    ArrayList<T> arr;
    P126_StackIntro(int capacity) {
        this.capacity = capacity;
        /*
         * Initialized -1 value indicates that the stack is empty, 
         * incremented when elements are pushed. 
         */
        this.top = -1;
        arr = new ArrayList<>();
    }

    /*
     * Top indicated the number of elements: incremented when 
     * elements are pushed, decremented when popped.
     */
    int getSize() {
        return top;
    }

    /*Stack is empty when top is -1. */
    boolean isEmpty() {
        return top == -1;
    }

    /*Method to insert an element on top of the stack. */
    public void push(T element) {
        /*Exception: Number of ekements in the ArrayList exceeds the allowed capacity. */
        if(getSize() == capacity - 1) 
        {
            throw new RuntimeException("Stack is full...");
        }
        /*Increment top with insertion of an element. */
        top++;
        /*Add element to the ArrayList backed stack by insertion at the index equal to top. */
        arr.add(top, element);
    }

    public T pop() 
    {
        /*Exception: When the ArrayList is empty. */
        if(isEmpty()) 
        {
            throw new RuntimeException("Stack is Empty...");
        }
        /*
         * Save and remove the element from index equal to the 
         * current top, i.e., the last added element at the index 
         * equal to the last altered top value.
         */
        T element = arr.remove(top);
        /*
         * Decrement top after removing the element.
         * The new top is the index of the second 
         * last added element. 
         */
        top--;
        /*Return the removed element. */
        return element;
    }

    T peek() {
        if(isEmpty()) {
            throw new RuntimeException("Stack is Empty...");
        }
        /*Return the element at the top index value. */
        return arr.get(top);
    }
    public static void main(String[] args) {
        /*Library-based stack in Java. */
        Stack<String> st = new Stack<>();
        st.push("hello");
        st.push("hi");
        st.push("hey");
        st.push("bye");
        System.out.println(st.pop());
        System.out.println(st.peek());
        
        /*Non-library ArrayList backed stack. */
        P126_StackIntro<String> st1 = new P126_StackIntro<>(5);
        st1.push("hello");
        st1.push("hey");
        st1.push("hi");

        System.out.println(st1.pop());
        System.out.println(st1.peek());
    }
}
