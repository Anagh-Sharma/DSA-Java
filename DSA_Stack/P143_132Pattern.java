package DSA_Stack;

import java.util.ArrayList;
import java.util.Stack;

public class P143_132Pattern {
    /*Storing the subsequences. */
    static ArrayList<String> subSeq(String str) 
    {
        /*
         * Base case:
         * - When the entire string has been processed, an empty
         *   string is returned and no other computations take
         *   place.
         */
        if(str.length() == 0) 
        {
            ArrayList<String> emptyString = new ArrayList<>();
            emptyString.add("");
            return emptyString;
        }
        /*The first character of the passed string */
        char currentChar = str.charAt(0);
        /*The substring with the first stored character of the string, removed. */
        String remainingString = str.substring(1);
        /*
         * Recursive case:
         * - All method calls on the stack hold the first
         *   elements of the iteratively smaller substrings
         *   from left to right.
         * - The last call gets an empty string as its returned
         *   value from the base case.
         * - The remaining calls get set of strings from the return
         *   statement at the end of the code.
         */
        ArrayList<String> temp = subSeq(remainingString);
        /*
         * The strings added to the result will be:
         * - The string already in temp (Excluding current character)
         * - String obtained as: string of temp + current character (Including current character)
         */
        ArrayList<String> result = new ArrayList<>();
        for(String s : temp) 
        {
            result.add(s);
            result.add(currentChar + s);
        }
        return result;
    }

    /*Approach 2: Prefix min and Postfix min arrays. */
    static boolean pattern132Array(int[] arr)
    {
        System.out.println("Original array:"); 
        display(arr);

        /*Prefix min array. */
        int[] prefixMin = new int[arr.length];
        prefixMin[0] = arr[0];

        /*Prefix max array. */
        int[] prefixMax = new int[arr.length];
        prefixMax[0] = arr[0];

        for(int i=1; i<arr.length; i++)
        {
            prefixMin[i] = Math.min(arr[i], prefixMin[i-1]);
            prefixMax[i] = Math.max(arr[i], prefixMax[i-1]);
        }

        System.out.println("Prefix min array:"); 
        display(prefixMin);

        System.out.println("Prefix max array:"); 
        display(prefixMax);

        for(int i=2; i<arr.length; i++)
        {
            if(prefixMin[i]<arr[i] && prefixMax[i]>arr[i])
            {
                System.out.println("Pattern: "+prefixMin[i]+"<"+arr[i]+"<"+prefixMax[i]);
                System.out.println("Subsequence: "+prefixMin[i]+""+prefixMax[i]+""+arr[i]);  
                return true;
            }
        }
        return false;
    }

    /*Approach 3: Using prefix min and stack.*/
    static boolean pattern132Stack(int[] arr)
    {
        System.out.println("Original array:"); 
        display(arr);

        /*Prefix min array. */
        int[] prefixMin = new int[arr.length];
        prefixMin[0] = arr[0];
        for(int i=1; i<arr.length; i++)
        {
            prefixMin[i] = Math.min(arr[i], prefixMin[i-1]);
        }
        System.out.println("Prefix min array:"); 
        display(prefixMin);


        Stack<Integer> stack = new Stack<>();

        /*Iterate through the elements of the array from right to left. */
        for(int i=arr.length-1; i>=0; i--)
        {
            /*
             * Select 1st and 2nd elements of the required
             * subsequence, such that:
             * preceding element (From prefix min) < current element
             */
            if(prefixMin[i] < arr[i])
            {
                /*
                 * The third element, occuring after the current element 
                 * (thus pushed onto the stack before the current element's
                 * iteration in the backwards parsing of the input array) 
                 * has to be greater than the selected preceding element, 
                 * such that:
                 * preceding element < succeeding element < current element
                 */
                /*
                 * Popping elements on the stack that are smaller than the 
                 * selected preceding element as such elements cannot be 
                 * selected as the succeeding element.
                 */
                while(!stack.isEmpty() && stack.peek() <= prefixMin[i])
                {
                    stack.pop();
                }
                /*
                 * If the remaining top element is smaller than the current 
                 * element than the condition is satisfied:
                 * preceding element < succeeding element < current element
                 * and this element is a valid succeeding element in the 
                 * subsequence.
                 */
                if(!stack.isEmpty() && stack.peek() < arr[i])
                    return true;

                /*
                 * Pushing the current element onto the stack for the next 
                 * iterations. This element will be checked as a possible 
                 * succeeding element for a preceding element in the array. 
                 */
                stack.push(arr[i]);
            }
        }
        return false;
    }

    static void display(int[] arr)
    {
        for(int num : arr)
        {
            System.out.print(num+", "); 
        }
        System.out.println(); 
    }

    public static void main(String[] args) {
        String nums = "3142";

        System.out.println("Approach 1: Brute force using recursion."); 
        /*Approach 1: Brute force using recursion. */
        /*Create all the subsequences of the numbers in the string */
        ArrayList<String> result = subSeq(nums);
        ArrayList<String> trueResult = new ArrayList<>();

        /*Iterate through all the subsequences of the numbers in the string */
        for(int i=0; i<result.size(); i++)
        {
            /*Check subsequence that has a length of 3. */
            if(result.get(i).length() == 3)
            {
                String subseq = result.get(i);    

                /*Store left, mid and right characters. */
                int left = Character.getNumericValue(subseq.charAt(0));
                int mid = Character.getNumericValue(subseq.charAt(1));
                int right = Character.getNumericValue(subseq.charAt(2));

                /*True if: left < right < mid */
                if((left <= right)&&(right <= mid))
                    trueResult.add(subseq);
            }
        }
        System.out.println("Required subsequence:   "+trueResult);
        System.out.println(); 

        System.out.println("Approach 2: Using Prefix min and Postfix min arrays."); 
        /*Approach 2: Using Prefix min and Postfix min arrays. */
        int[] num = {3, 1, 4, 2};
        System.out.println(pattern132Array(num)); 

        System.out.println(); 
        System.out.println("Approach 3: Using prefix min and stack."); 
        /*Approach 3: Using prefix min and stack.*/
        System.out.println(pattern132Stack(num)); 

    }
}
