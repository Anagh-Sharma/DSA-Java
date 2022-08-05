package DSA_Recursion;

public class P71_RecursionTypes {
    // Tail Recursion
    static void print1(int n) {
        if(n == 0) {
            return;
        }
        System.out.println(n);
        print1(n - 1);
    }

    // Head Recursion
    static void print2(int n) {
        if(n == 0) {
            return;
        }
        print2(n - 1);
        System.out.println(n);
    }

    // Linear Recursion demonstrating both head and tail recursion
    static void print3(int n) {
        if(n == 0) {
            return;
        }
        System.out.println(n);
        print3(n - 1);
        System.out.println(n);
    }

    // Tree Recursion
    static void fun(int x) 
    {
        System.out.print(x+" ");
        if(x >= 3) 
        {
            return;
        }
        fun(x + 1);
        fun(x + 2);
    }
    
    public static void main(String[] args) {
        print1(5);
        fun(0);
    }
}
