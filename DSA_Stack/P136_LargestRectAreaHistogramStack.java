package DSA_Stack;

import java.util.Stack;

public class P136_LargestRectAreaHistogramStack {
    static int[] prevSmaller(int arr[], int n) 
    {
        int s[] = new int[n];
        /*
         * Stack contains elements of the array and  
         * elements are pushed and popped to calculate 
         * the previous smaller element.
         */
        Stack<Integer> st = new Stack<>();
        for(int i = 0; i < n; i++) 
        {
            /*
             * The elements are popped until an element 
             * is greater than or equal to the current element.
             */
            while(!st.isEmpty() && arr[st.peek()] >= arr[i]) 
            {
                st.pop();
            }
            /*
             * - The popping of elements above stops when 
             *   the an element smaller than the current 
             *   element is on top of the stack.
             * - When the stack is empty: all preceeding
             *   elements were greater or equal to the current 
             *   element as they were all popped in this iteration
             *   or previous iterations.
             * - When the stack is not empty: The top element is 
             *   smaller than current element as it was not popped 
             *   in this iteration earlier. This is the previous 
             *   smaller element for this index.
             */
            s[i] = (st.isEmpty()) ? 0 : st.peek()+1;
            st.push(i);
        }
        return s;
    }

    static int[] nextSmaller(int arr[], int n) 
    {
        int s[] = new int[n];
        /*
         * Stack contains elements of the array and  
         * elements are pushed and popped to calculate 
         * the next greater element.
         */
        Stack<Integer> st = new Stack<>();
        for(int i = n-1; i >= 0; i--) 
        {
            /*
             * The elements are popped until an element  
             * is greater than or equal to the current element.
             */
            while(!st.isEmpty() && arr[st.peek()] >= arr[i]) 
            {
                st.pop();
            }
            /*
             * - The popping of elements above stops when 
             *   the an element smaller than the current 
             *   element is on top of the stack.
             * - When the stack is empty: all succeeding
             *   elements were greater or equal to the current 
             *   element as they were all popped in this iteration
             *   or previous iterations.
             * - When the stack is not empty: The top element is 
             *   larger than current element as it was not popped 
             *   in this iteration earlier. This is the next 
             *   smaller element for this index.
             */
            s[i] = (st.isEmpty()) ? n-1 : st.peek()-1;
            st.push(i);
        }
        return s;
    }

    public static void main(String[] args) {
        int arr[] = {6, 2, 5, 4, 5, 6};
        int n = arr.length;
        // int s[] = new int[n];
        System.out.println();

        System.out.println("The given array is: ");
        for(int i : arr) {
            System.out.print(i + ", ");
        }
        System.out.println();

        int prev[] = prevSmaller(arr, n);
        System.out.println("The previous smaller elements at each index are: ");
        for(int i : prev) {
            System.out.print(i + ", ");
        }
        
        int next[] = nextSmaller(arr, n);
        System.out.println();
        System.out.println("The next smaller elements at each index are: ");
        for(int i : next) {
            System.out.print(i + ", ");
        }

        int max = Integer.MIN_VALUE;
        for(int i=0; i<n; i++)
        {
            if(max < (arr[i] * (next[i] - prev[i] + 1)))
                max = arr[i] * (next[i] - prev[i] + 1);
        }

        System.out.println();
        System.out.println("The maximum area rectangle: "+max);
    }
}
