package DSA_Stack;

import java.util.Stack;

public class P138_InfixToPrefix {
    static int precedence(char ch) {
        switch(ch) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
        }
        return -1;
    }

    static String infixToPostfix(String exp) {
        /*The postFixResult string holds the postfix expression, when the function returns. */
        String postFixResult = new String("");
        /*The operators are pushed on and popped from the stack for conversion.  */
        Stack<Character> stack = new Stack<>();

        /*Iterating through the characters of infix expression. */
        for(int i = 0; i < exp.length(); i++) 
        {
            char c = exp.charAt(i);
            /*
             * If character is an operand: 
             * Insert character in output postfix expression.
             */
            if(Character.isLetterOrDigit(c)) 
            {
                postFixResult += c;
            }

            /*If the character is an operator. */

            /*
             * - Else if character is an open bracket, (:
             *   Push ( in stack. 
             */
            else if(c == '(') 
            {
                stack.push(c);
            }
            /*
             * - Else if character is a close bracket, ): 
             *   Pop characters from stack until either an open 
             *   bracket is at that top of the stack or the stack 
             *   is empty. 
             *   The popped characters are appended to the output 
             *   postfix expression in the order they are popped. 
             */
            else if(c == ')') 
            {
                /*Pop and append operators from stack until an opening bracket is at top. */
                while(!stack.isEmpty() && stack.peek() != '(') 
                {
                    postFixResult += stack.pop();
                }
                /*If an opening bracket is not at top, the infix expression is invalid. */
                if(!stack.isEmpty() && stack.peek() != '(') 
                {
                    return "Invalid Expression !";
                }
                /*Else the top character is an opening bracket, and it is popped. */
                else if(!stack.isEmpty())
                {
                    stack.pop();
                }
            }
            /*Else when the parsed character is an operator other than a bracket. */
            else 
            {
                /* 
                 * Pop and append operators until their priority is greater or 
                 * equal to the priority of the parsed character.
                 */
                while(!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) 
                {
                    /*
                     * The top of the stack can not hold a bracket, as 
                     * brackets are accounted for in previous conditions 
                     * and pairs of brackets are popped.
                     */
                    if(stack.peek() == '(' || stack.peek() == ')') 
                    {
                        return "Invalid Expression";
                    }
                    postFixResult += stack.pop();
                }
                /*The operator is pushed onto the stack. */
                stack.push(c);
            }
        }

        /*
         * Operators, other than brackets, can still remain on the stack 
         * and are appended to the postfix expression.
         */
        while(!stack.isEmpty()) 
        {
            if(stack.peek() == '(' || stack.peek() == ')') 
            {
                return "Invalid Expression...";
            }
            postFixResult += stack.pop();
        }
        return postFixResult;
    }

    static String reverseString(String expression)
    {
        char[] stringChar = expression.toCharArray();
        char[] revString = new char[expression.length()];

        for(int i=0, j=expression.length()-1; i<expression.length(); i++, j--)
        {
            char temp = stringChar[i];
            if(temp == '(')
                temp = ')';
            else if(temp == ')')
                temp = '(';
            
            /*Shorter version of the code above: */
            // temp = (stringChar[i] == '(') ? ')' : ((stringChar[i] == ')') ? '(' : stringChar[i]);


            revString[j] = temp;
        }

        return String.valueOf(revString);
    }

    static String infixToPrefix(String expression)
    {
        String result = reverseString(expression);
        result = infixToPostfix(result);
        result = reverseString(result);

        return result;
    }

    public static void main(String[] args) 
    {
        String expression = "(A+B)*(C+D)";
        System.out.println("Infix: "+expression);
        System.out.println("Prefix: "+infixToPrefix(expression));
    }
}
