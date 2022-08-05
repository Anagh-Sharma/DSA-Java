package DSA_Stack;

import java.util.Stack;

public class P141_PostfixToPrefixStack {
    static boolean isOperator(char x)
    {
        switch(x)
        {
            case '+':
            case '-':
            case '*':
            case '/':
            case '^':
                return true;
        }
        return false;
    }

    static String postfixToPrefix(String exp)
    {
        Stack<String> stack = new Stack<>();
        int n = exp.length();
        
        /*Iterate through the characters of the prefix expression from left to right. */
        for(int i = 0; i < n; i++)
        {
            char c = exp.charAt(i);
            /*
             * If the character parsed is an operator:
             * - The top two elements (operands) on the stack 
             *   are popped and stored as String objects.
             * - String is constructed as:
             *   (operand 1(popped) + operator(current character) + operand 2(popped))
             * - Push the constructed string onto the stack as this is now an operand
             */
            if (isOperator(c))
            {
                String op1 = stack.pop();
                String op2 = stack.pop();
                String temp = c + op2 + op1;
                stack.push(temp);
            }
            /*Else, if an operand is parsed: Push it onto the stack. */
            else
            {
                stack.push(c + "");
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {
        String exp = "ABC*+D+";
        System.out.println("Prefix : " + postfixToPrefix(exp));
    }
}
