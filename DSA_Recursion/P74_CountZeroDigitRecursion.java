package DSA_Recursion;

public class P74_CountZeroDigitRecursion {
    static int countZeroNum(int num)
    {
        /*
         * Base case:
         * When the number becomes 0, 0 is returned.
         */
        if(num==0)
            return 0;
        
        /*
         * Recursive case:
         * Each number of different face values are recursively computed using 
         * modulus 10 to get the digit at rightmost face value and if number is
         * 0 then, 1 is added to the return statement else nothing is added next 
         * smaller number is computed using division by 10.
         */
        if(num%10==0)
            return 1 + countZeroNum(num/10);
        else
            return countZeroNum(num/10);
    }
    public static void main(String[] args) {
        int num = 100010;
        System.out.println(countZeroNum(num));
    }
}
