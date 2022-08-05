package DSA_Recursion;

public class P73_SumOfDigits {
    static int sumDigits(int num)
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
         * modulus 10 to get the digit at rightmost face value and added to the 
         * returning value total and the next smaller number is computed using 
         * division by 10.
         */
        return num%10 + sumDigits(num/10);
    }
    public static void main(String[] args) {
        int num = 5439;
        System.out.println(sumDigits(num));
    }
}
