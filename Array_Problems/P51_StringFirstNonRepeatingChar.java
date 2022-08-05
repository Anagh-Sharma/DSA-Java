package Array_Problems;
/*
 * Check if both preceding and succeeding characters of a character are different
 */
public class P51_StringFirstNonRepeatingChar {
    /*
     * - Convert given string to character array.
     * - Iterate over each element in the character array.
     * - The first element whose first index (from indexOf() ) 
     *   and last index (from lastIndexOf() ) are the same, that 
     *   element is the first non-repeating element.
     */
    // TC : O(N)
    static void firstLastIndexMethods(String str)
    {
        for(char i :str.toCharArray())
        {
            if(str.indexOf(i) == str.lastIndexOf(i)) 
            {
                System.out.println("First non-repeating character is: "+i);
                break;
            }
        }
    }

    // TC : O(N)
    /*
     * - Create a hash map of size 256 to store the count of each element’s ASCII value.
     * - Parse the string and store the counts.
     * - Parse the string and return the first character whose ASCII value’s count is 1 
     *   in the hash map.
     */
    static void firstLastIndexHashmap(String str)
    {
        int hash[] = new int[256];
        for(char i :str.toCharArray())
        {
            hash[(byte)i]++;
        }
        for(char i :str.toCharArray())
        {
            if(hash[(byte)i] == 1)
            {
                System.out.println("First non-repeating character is: "+i);
                break;
            }
        }
        
    }

    public static void main(String[] args) {
        String str = "aabbcdeffadb";
        firstLastIndexMethods(str);
        firstLastIndexHashmap(str);
    }   
}
