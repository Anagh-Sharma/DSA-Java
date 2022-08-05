package DSA_Stack;

import java.util.Stack;

public class P131_StockSpanStack {
    static void approach_1(int price[], int n, int s[]) 
    {
        s[0] = 1;
        for(int i = 1; i < n; i++) {
            s[i] = 1;
            for(int j = i - 1; (j >= 0) && (price[i] >= price[j]); j--) {
                s[i]++;
            }
        }
        for(int i : s) {
            System.out.print(i + ", ");
        }
    }

    static int[] approach_2(int price[], int n, int s[]) 
    {
        /*
         * Stack contains indices of the array and  
         * elements are pushed and popped to calculate 
         * the stock span of a index or a day.
         */
        Stack<Integer> st = new Stack<>();
        /*Initialized with element 0 denoting index 0 element of the input array. */
        st.push(0);
        /*Initialized stock span 1 at index 0 as the element <= itself. */
        s[0] = 1;
        /*Iterate through the elements of the array. */
        for(int i = 1; i < n; i++) 
        {
            /*
             * The elements on the stack denote indices.
             * The elements are popped until the index 
             * values they denote hold an element less 
             * than or equal to the current element.
             */
            while(!st.isEmpty() && price[st.peek()] <= price[i]) 
            {
                st.pop();
            }
            /*
             * - The popping of elements above stops when 
             *   the index popped holds an element greater 
             *   than the current element.
             * - When the stack is empty: all preceeding
             *   elements were smaller or equal to the current 
             *   element as they were all popped in this iteration
             *   or previous iterations.
             * - When the stack is not empty: The top element (index) is 
             *   not smaller or equal to the current element as it was not 
             *   popped in this iteration earlier. The stock span 
             *   is the number of elements between this element and the 
             *   current element.
             */
            s[i] = (st.isEmpty()) ? (i + 1) : (i - st.peek());
            st.push(i);
        }
        return s;
    }
    
    public static void main(String[] args) {
        int arr[] = {10,20,15,16,18,9,7,6,25};
        int n = arr.length;
        int s[] = new int[n];
        approach_1(arr, n, s);
        System.out.println();
        System.out.println("==================");
        int res[] = approach_2(arr, n, s);
        for(int i : res) {
            System.out.print(i + ", ");
        }
    }
}
