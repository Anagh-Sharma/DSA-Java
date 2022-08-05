package DSA_Queue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class P149_GenerateKNumbersFromDigits {
    public static void main(String[] args) {
        int n = 10;
        Queue<String> q = new LinkedList<>();
        ArrayList<String> result = new ArrayList<>();

        /*Initialize the queue with the given digits. */
        q.add("5");
        q.add("6");
        
        /*Iterate the given n number of times. */
        for(int i  = 0; i < n; i++) 
        {
            /*Pop and store the current head element. */
            String current = q.poll();
            /*Print the stored top element. */
            result.add(current);
            /*Append each digit to the stored element and enqueue the result. */
            q.add(current + "5");
            q.add(current + "6");
        }

        System.out.println(result);
    }
}
