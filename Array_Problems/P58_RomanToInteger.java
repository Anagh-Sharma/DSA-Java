package Array_Problems;
/*
 * 1. Convert input Roman char to int
 * 2. If the current int value is larger or equal to
 *    the next int value then add to the running sum
 * 3. Otherwise subtract the next value from the current
 *    and add the resultant to the running sum.
 */
public class P58_RomanToInteger {

    /* Define a method that returns int value corresponding to the passed Roman character. */
    static int value(char r)
    {
        if (r == 'I')
            return 1;
        if (r == 'V')
            return 5;
        if (r == 'X')
            return 10;
        if (r == 'L')
            return 50;
        if (r == 'C')
            return 100;
        if (r == 'D')
            return 500;
        if (r == 'M')
            return 1000;
        return -1;
    }

    static void romanToInteger(String str)
    {
        /*res: stores the final converted value. */
        int res = 0; 
        /*Iterate over the input Roman character string. */
        for (int i = 0; i < str.length(); i++) {
            // s1: Variables stores the symbol at current iteration
            int s1 = value(str.charAt(i));
            /*
             * If the current character is not the last
             * character of the string, then:
             * appropriate arithmetic is performed.
             */
            if (i+1<str.length()) 
            {
                // s2: Variables stores the symbol at next to current iteration
                int s2 = value(str.charAt(i + 1));
                /*
                 * If s1 >= s2:
                 * Add s1 to res.
                 */
                if (s1>=s2) 
                {
                    res = res + s1;
                }
                /*
                 * Else:
                 * Add s2 to res
                 * Subtract s1 to res
                 * Increment the iteration of character in the string by 2, i.e., beyond both
                 * s1 and s2.
                 */
                else 
                {
                    res = res + s2 - s1;
                    /*
                     * This increment skips over the value
                     * next to the current i iteration.
                     * So, the s2 that has been already
                     * read is skipped over and i moves
                     * by 2 steps.
                     */
                    i++;
                }
            }
            /*
             * If it is the last int value then
             * it is added to the running sum.
             */
            else 
            {
                res = res+s1;      
            }
        }
        System.out.println(res);
    }

    public static void main(String[] args) { 
        String str = "MCMXCIV";
        romanToInteger(str);
    }
}
