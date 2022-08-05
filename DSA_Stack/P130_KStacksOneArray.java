package DSA_Stack;

public class P130_KStacksOneArray
{
    /*ArrayList Backed Stack */
    int stacksNum;
    int eachStackSize;
    int totalCapacity;
    /*Store top element's index for each stack. */
    int top[];
    int arr[];
    P130_KStacksOneArray(int stacksNum, int eachStackSize) 
    {
        /*Calculate total capacity of the array as: number of stacks * stack size. */
        this.stacksNum = stacksNum;
        this.eachStackSize = eachStackSize;
        this.totalCapacity = eachStackSize*stacksNum;
        /*
         * Initialize top indices to -1 indicating that the 
         * stack is empty.
         */
        top = new int[stacksNum];
        for(int i=0; i<stacksNum; i++)
        {
            top[i] = - 1;
        }
        /*The array backing the stacks. */
        arr = new int[totalCapacity];
    }

    /*
     * Top indicated the number of elements in a stack:
     * - Index of the top element of the stack
     *   - Stack number - 1 is the index where the index
     *     of the top element of the stack is stored as 
     *     arrays are indexed from 0.
     * - (Top index % each stack size) + 1 gives the number 
     *   of elements in the stack as it gives a normalized
     *   count of indices that are filled.
     */
    int getSize(int stackNum) {
        return (top[stackNum-1]%eachStackSize)+1;
    }

    /*Stack is empty when top is -1. */
    boolean isEmpty(int stackNum) {
        return top[stackNum-1] == -1;
    }

    /*Method to insert an element on top of the stack. */
    public void push(int element, int whichStack) 
    {
        /*Exception: Number of ekements in the ArrayList exceeds the allowed capacity. */
        if(getSize(whichStack) == eachStackSize) 
        {
            throw new RuntimeException("Stack "+whichStack+" is full...");
        }
        /*Initialize the index of the first element of the stack. */
        /*
         * Initialized index preceeds the index in the 
         * array where a stack's first element is inserted 
         * at. A stack's elements are inserted in the array 
         * from left to right, at indices of subarrays equal 
         * to the size of each array.
         */
        if(top[whichStack-1] == -1)
            top[whichStack-1] = ((whichStack-1) * eachStackSize);
        /*Increment top with insertion of an element. */
        else
            top[whichStack-1]++;
        /*Add element to the array backed stack by insertion at the index equal to top. */
        arr[top[whichStack-1]] = element;
        System.out.println("Pushed element: "+element+"     ;   On stack: "+whichStack+"    ;   At index: "+top[whichStack-1]);
    }

    public int pop(int whichStack) 
    {
        /*Exception: When the ArrayList is empty. */
        if(isEmpty(whichStack)) 
        {
            throw new RuntimeException("Stack "+whichStack+" is Empty...");
        }
        /*
         * Save and remove the element from index equal to the 
         * current top, i.e., the last added element at the index 
         * equal to the last altered top value.
         */
        int element = arr[top[whichStack-1]];
        arr[top[whichStack-1]] = 0;
        System.out.println("Popped element: "+element+"     ;   On stack: "+whichStack+"    ;   At index: "+top[whichStack-1]);
        
        /*
         * Decrement top after removing the element.
         * The new top is the index of the second 
         * last added element. 
         */
        top[whichStack-1]--;
        /*
         * If the stack is empty, decrement above will 
         * move it to the index of the preceeding stack 
         * in the array. Initializing to -1 when this 
         * happens:
         */
        if(top[whichStack-1]  == ((whichStack-1) * eachStackSize)-1)
            top[whichStack-1] = -1;
        /*Return the removed element. */
        return element;
    }

    int peek(int whichStack) {
        if(isEmpty(whichStack)) {
            throw new RuntimeException("Stack "+whichStack+" is Empty...");
        }
        /*Return the element at the top index value. */
        return arr[top[whichStack-1]];
    }

    void displayTheWholeArray()
    {
        System.out.println();
        System.out.println("The current array backed stack:");
        for(int i=0; i<totalCapacity; i++)
        {
            System.out.print(arr[i]+", ");
        }
        System.out.println();
        System.out.println("The current top indices of stacks:");
        for(int i=0; i<stacksNum; i++)
        {
            System.out.print(top[i]+", ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int eachStackSize = 3;
        int stacksNum = 4;
        P130_KStacksOneArray st = new P130_KStacksOneArray(stacksNum, eachStackSize);

        st.push(1, 1);
        st.push(2, 2);
        st.push(3, 3);
        st.push(4, 4);
        
        st.push(99, 2);
        st.push(87, 4);
        st.push(43, 1);
        st.push(44, 4);

        st.push(23, 3);
        st.push(35, 1);
        st.push(62, 3);
        st.push(47, 2);

        st.displayTheWholeArray();
        System.out.println();

        st.pop(4);
        st.pop(2);
        st.pop(4);

        st.displayTheWholeArray();

        System.out.println();
        System.out.println("Current number of element(s) in stack 4: "+st.getSize(4));
    }
}
