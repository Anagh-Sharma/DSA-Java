package Array_Problems;

public class P56_StringToInteger {
    static void stringToInteger_Final(String str, int n)
    {
        /*Sign variable: stores the sign, only if it occurs first. */
        int sign = 1;
        /* notParsed: Boolean value used to ensure that numbers are parsed consecutively, 
         * that sign value is parsed before numbers, and characters after the consecutive 
         * numbers are not parsed. 
         */
        boolean notParsed = true;
        /*Num variable to store the integer. */
        int num = 0;
        /*Iterate over the given string.*/
        for(int i=0; i<n; i++)
        {
            /*
             * If notParsed is true and the character is '-', then: Set sign to -1.
             */
            if(notParsed == true && str.charAt(i) == '-')
                sign = -1;
            /*
             * If the character is a number(if it is greater than or equal to 0 and smaller than or equal to 9):
             * Recompute num as:
             * num = num * 10 + (number)
             * Set notParsed as false.
             * Continue to the next iteration
             */
            if(str.charAt(i) >= '0' && str.charAt(i) <= '9')
            {
                num = num * 10 + (str.charAt(i) - '0');
                notParsed = false;
                continue;
            }
            /*
             * Else If notParsed is false:
             * break from the loop
             * This else if block is only reachable when:
             * - Character of the current iteration is non-numeral
             * - Atleast a single numeral character has already been parsed
             *   as notParsed is set to false in the block above that is 
             *   reachable only when a numeral is found.
             */
            else if(notParsed == false)
                break;
        }
        num *= sign;
        System.out.println(num);
    }
    static int stringToInteger(String str, int n)
    {
        /*Used to flag the sign at the beginning of the pruned string. */
        int flagSign = 0;
        /*Pruning non-numeral characters from the string. */
        str = str.replaceAll("\\s+","");
        str = str.replaceAll("[a-zA-Z]","");
        /*Check if negative sign exists, replace it with empty space and raise the flag. */
        if(str.charAt(0) == '-')
        {
            str = str.replace("-","");
            flagSign = 1;
        }
        
        /*Initialize counter variable th loop and an variable to store the number*/
        int num = 0, counter = 0;
        /*
         * While loop conditions:
         * If counter has not reached beyond the length of the string
         * If the character at the current counter is either equal to or greater
         * than 0 and smaller than or equal to 9.
         */
        while (counter<str.length()  && str.charAt(counter) >= '0' && str.charAt(counter) <= '9') 
        {
            /*Calculate the number ata an iteration using the formula:
             * num = num * 10 + New number
             */
            num = num * 10 + (str.charAt(counter) - '0');
            counter++;
        }
        
        /*Finally, if flag is 1, multiply num with -1. */
        if(flagSign == 1)
            num = -num;
        
        return num;
    }

    static int toi(String str) {
        int sign = 1;
        int result = 0;
        int index = 0;
        int n = str.length();

        // Trimming all whitespaces from the beginning
        while(index < n && str.charAt(index) == ' ') {
            index++;
        }

        if(index < n && str.charAt(index) == '-') {
            sign = -1;
            index++;
        }
        else if(index < n && str.charAt(index) == '+') {
            sign = 1;
            index++;
        }

        // Traversing digits one by one and stop if character is not digit
        // while(index < n && Character.isDigit(str.charAt(index))) {
        while(index < n) {
            if(!Character.isDigit(str.charAt(index))) {
                continue;
            }
            int digit = str.charAt(index) - '0';

            // checking overflow and underflow condition
            if((result > Integer.MAX_VALUE / 10) || (result == Integer.MAX_VALUE / 10 && digit > Integer.MAX_VALUE % 10)) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            result = 10 * result + digit;
            index++;
        }
        return result * sign;
    }

    

    public static void main(String[] args) {
        String str = "   Asp-344aabb   cdeNffadb";
        int n = str.length();
        // System.out.println(stringToInteger(str, n));
        // System.out.println(toi(str));
        stringToInteger_Final(str, n);
    }
}
