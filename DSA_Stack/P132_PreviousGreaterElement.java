package DSA_Stack;

import java.util.Stack;

public class P132_PreviousGreaterElement {
    static int[] prevGreater(int arr[], int n, int s[]) 
    {
        /*
         * Stack contains elements of the array and  
         * elements are pushed and popped to calculate 
         * the previous greater element.
         */
        Stack<Integer> st = new Stack<>();
        for(int i = 0; i < n; i++) 
        {
            /*
             * The elements are popped until an elements  
             * are less than or equal to the current element.
             */
            while(!st.isEmpty() && st.peek() <= arr[i]) 
            {
                st.pop();
            }
            /*
             * - The popping of elements above stops when 
             *   the an element greater than the current 
             *   element is on top of the stack.
             * - When the stack is empty: all preceeding
             *   elements were smaller or equal to the current 
             *   element as they were all popped in this iteration
             *   or previous iterations.
             * - When the stack is not empty: The top element is 
             *   larger than current element as it was not popped 
             *   in this iteration earlier. This is the previous 
             *   greater element for this index.
             */
            s[i] = (st.isEmpty()) ? -1 : st.peek();
            st.push(arr[i]);
        }
        return s;
    }
    
    public static void main(String[] args) {
        int arr[] = {10,20,15,16,18,9,7,6,25};
        int n = arr.length;
        int s[] = new int[n];
        System.out.println();
        int res[] = prevGreater(arr, n, s);
        System.out.println("The given array is: ");
        for(int i : arr) {
            System.out.print(i + ", ");
        }
        System.out.println();
        System.out.println("The previous greater elements at each index are: ");
        for(int i : res) {
            System.out.print(i + ", ");
        }
    }
}
