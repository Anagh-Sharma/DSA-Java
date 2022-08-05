package Array_Problems;

public class P48_RunningLengthEncoding_RLE {
    // Stores cumulative count of all the occurrences of a character in a string.
    static void hashRLE(String str, int n)
    {
        // 1. Converting String to integer array
        int[] arr = new int[n];
        for(int i=0; i<n; i++)
        {
            arr[i] = str.charAt(i);
        }

        // 2. Hash to store count of each element
        int hash[] = new int[256];
        for(int j=0; j<n; j++)
        {
            hash[arr[j]]++; 
        }           

        // 3. Print character and its count
        for(int k=0; k<256; k++)
        {
            if(hash[k] != 0)
            {
                System.out.print((char)k+""+hash[k]);
            }
        }
    }
    // Stores count of consecutive similar characters.
    static void hashRLE_approach2(String str, int n)
    {
        // Length variable: to store the length of same-letter strings
        int length = 0;
        String res = "";
        for(int i=0; i<str.length(); i++)
        {
            /*
             * Initialize count variable to 1 at each 
             * iteration, as every element at least occurs 
             * once.
             */
            length = 1;
            /*
             *   Nested while loop:
             * - If the next element index does not exceed the 
             *   string and next element is same as the current 
             *   element:
             * - Increment the count and move to the next iteration.
             * - Else, store or print the present count and element.
             */
            while(i < str.length()-1 && str.charAt(i+1) == str.charAt(i))
            {
                length++;
                i++;
            }
            res += str.charAt(i);
            res += String.valueOf(length);
        }
        System.out.println(res);
    }

    // TC : O(N)
    static int stringCompress(String str, int n)
    {
        StringBuilder s = new StringBuilder();
        int count = 0, i = 0, j = 0;
        while(i<n)
        {
            count = 0;
            j=i;
            while(j<n)
            {
                if(str.charAt(i) == str.charAt(j))
                {
                    count++;
                    j++;
                }
                else
                    break;
            }
            // Case 1: When count is 1
			if (count == 1) 
            {
	    		// System.out.print(str.charAt(i));
                s.append(str.charAt(i));
            } 
            // Case 2: When count is more than 1
            else 
            {
                // System.out.print(str.charAt(i)); 
                // System.out.print(count);
                s.append(str.charAt(i));
                s.append(count);
            }
            i = i + count;
        }
        str = s.toString();
        System.out.println(str);
        return str.length();
    }

    static void hashRLE_approach3(String str, int n)
    {
        // Length variable: to store the length of same-letter strings
        int length = 0;
        // 
        // StringBuilder str = new StringBuilder(str1);
        // // str.append('X');
        /*
         * res: Mutable StringBuilder object to store result
         */
        StringBuilder res = new StringBuilder();
        for(int i=0; i<str.length()-1; i++)
        {
            /*
             * Keep counting elements until
             * the next element is not equal
             * to the current or when the
             * string reaches the second last
             * element.
             */
            length++;
            /*
             * When the string reaches the second 
             * last element, append the current
             * element the current count.
             * 
             * The last element is ignored as the loop
             * only stores the length of consecutive 
             * letter substrings when the next element 
             * is different, and there is no element 
             * after the last element.
             */
            if(str.charAt(i+1) != str.charAt(i)||i==str.length()-2)
            {
                res.append(str.charAt(i));
                res.append(length);
                length = 0;
            }
        }
        /*
         * For the last element:
         */
        /*
         * When the last and the secodn last element are same:
         * Remove and store the the last element of res, then
         * increment it by one and then append this to the
         * res StringBuilder object.
         */
        if(str.charAt(str.length()-1) == str.charAt(str.length()-2))
        {
            Character finalLength = res.charAt(res.length()-1);
            res.deleteCharAt(res.length()-1);
            res.append(Character.getNumericValue(finalLength) + 1);
        }
        /*
         * When the last and the second last element are not same:
         * Append the last element and count 1 to the StringBuilder
         * object res.
         */
        else
        {
            res.append(str.charAt(str.length()-1));
            res.append(1);
        }
        System.out.println(res);
    }
    
    public static void main(String[] args) {
        String str = "aabbbbdddddyyz";
        int n = str.length();
        hashRLE_approach2(str, n);
        // hashRLE_approach3(str, n);
    }
}
