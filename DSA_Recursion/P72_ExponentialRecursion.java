package DSA_Recursion;

public class P72_ExponentialRecursion {
    static int exponentiation(int base, int exp)
    {
        /*
         * Base case:
         * When exponent is 0, the result is 1
         */
        if(exp==0)
            return 1;
        /*
         * Recursive case:
         * Returns base multiplied to recursive call the the method with base raised to exponent - 1.
         */
        return base * exponentiation(base, exp-1);
    }
    public static void main(String[] args) {
        int base = 4;
        int exp = 3;
        System.out.println(exponentiation(base, exp));
    }
}
