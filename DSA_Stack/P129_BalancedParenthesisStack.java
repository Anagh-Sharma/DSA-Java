package DSA_Stack;

import java.util.HashMap;
import java.util.Stack;

/*
 * - Read a bracket:
 * - If the bracket is opening: Insert into stack.
 * - If the bracket is closing: 
 *   Pop the stack element:
 *   - If the popped element is the opening bracket of the read closing bracket:
 *   The parenthesis is balanced up to this point.
 *   - Else: 
 *   The parenthesis is not balanced.
 */
public class P129_BalancedParenthesisStack {
    static boolean isValid(String s) 
    {
        Stack<Character> stack = new Stack<>();
        /*
         * Opening brackets are inserted onto the stack, and when a closing
         * bracket is encountered, stack's top element is popped and if the 
         * brackets are not compatible, the parenthesis is invalid.
         */
        /*
         * Compatible bracket pairs are grouped in pairs in a map.
         */
        HashMap<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');
        /*Iterating through the characters of the string. */
        for(int i = 0; i < s.length(); i++) 
        {
            /*
             * When character is a closing bracket and the stack is empty: Invalid 
             * As, the stack should have has the compatible opening bracket in top.
             */
            if((s.charAt(i) == ']' || s.charAt(i) == '}' || s.charAt(i) == ')') && stack.isEmpty()) 
            {
                return false;
            }
            /*If the character is an opening bracket: push onto the stack. */
            if(s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{') 
            {
                stack.push(s.charAt(i));
            }
            /*Else if the character is a closing bracket */
            else 
            {
                /*Store the opening bracket present on top of the stack. */
                Character topOpeningBracket = stack.pop();

                /*Store the expected closing bracket using map. */
                Character expectedCloseBracket = map.get(topOpeningBracket);

                /*Store the true closing bracket, i.e, current character. */
                Character trueCloseBracket = s.charAt(i);

                if(trueCloseBracket != expectedCloseBracket) 
                {
                    System.out.println("Invalid Brackets !");
                    return false;
                }
            }
        }
        /*
         * If after the iteration above, the function was 
         * not returned, the parenthesis are balanced.
         */
        if(stack.isEmpty()) 
        {
            System.out.println("Valid Brackets !");
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        // String str = "()({})[]";
        String str = "()({})[(]";
        System.out.println(isValid(str));
    }
}
