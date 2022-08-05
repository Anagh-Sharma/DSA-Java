package Array_Problems;
/*
 * Compare every character of the strings in the array
 * 
 * 1. Find string with the minimum length as a common
 *    prefix cannot exceed the length of the smallest string.
 * 2. Instantiate a String object to store prefix.
 * 3. Loop 1: To compare every character in each string by 
 *    iterating over every character in any string until the 
 *    minimum length.
 * 4. Loop 2: To check if a character at an index is same
 *    in all strings.
 */
public class P59_LongestCommonPrefix {
    static void commonPrefix(String arr[], int n)
    {
        // 1. Find string with the minimum length
        int min = arr[0].length();
        for (int i=1; i<n; i++)
        {
            if (arr[i].length() < min)
            {
                min = arr[i].length();
            }
        }

        // 2. Instantiate a String object to store prefix.
        String res = "";
        char curr;
        int flag = 0;

        // 3. Loop 1: To compare every character in each string
        for (int i=0; i<min; i++)
        {
            curr = arr[0].charAt(i);
            flag = 0;    
            
            // 4. Loop 2: To compare every character in all strings.
            for (int j=1; j<n; j++)
            {
                if (arr[j].charAt(i)!=curr)
                {
                    flag = 1;
                    break;
                }
            }
            if(flag != 1)
                res += curr;
        }
        if(res.length()>0) 
        {
            System.out.println(res);
        } 
        else 
        {
            System.out.println(-1);
        }
    }
 
    public static void main(String[] args)
    {
        String arr[] = {"flower","flow","flight"};
        int n = arr.length;
        commonPrefix(arr, n);
    }
}
