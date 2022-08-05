package Array_Problems;

import java.util.Arrays;

public class P14_AddSubTwoNumbersAsArrays {

    // TC : O(N)
    static void subtractArrayNums(int arr1[], int arr2[])
    {
        int carry = 0;
        // Condition ? True : False
        int size = arr1.length > arr2.length ? arr1.length : arr2.length;
        int res[] = new int[size];

        for(int i=size-1; i>=0; i--)
        {
            if(arr1[i] < arr2[i])
            {
                res[i] = arr1[i] + 10 + carry - arr2[i];
                carry = -1;
            }
            else
            {
                res[i] = arr1[i] + carry - arr2[i];
                carry = 0;
            }
        }

        System.out.println("First number:");
        System.out.println(Arrays.toString(arr1));
        System.out.println("Second number:");
        System.out.println(Arrays.toString(arr2));
        System.out.println("Result Subtraction:");
        System.out.println(Arrays.toString(res));
    }

    static void addArrayNums(int arr1[], int arr2[])
    {
        int carry = 0;
        // Condition ? True : False
        // Size of resultant array will have aleast as many elements as the longest array
        int size = arr1.length > arr2.length ? arr1.length : arr2.length;
        // Size of resultant array intialized for size+1 to account for carry
        int res[] = new int[size+1];
        int temp = 0;
        // All array pointers initialized to point at their last respective elements
        int i=arr1.length-1, j=arr2.length-1, k=size;
        // All pointers will be decremented together by step size 1
        while(k>0)
        {
            // A temp value stores the summization of carry and 
            // the elements at the respective indices of arrays 1 and 2
            // which are at the same distance from their last elements
            temp = arr1[i] + carry + arr2[j];
            // When there is a need for producing a carry
            if(temp > 9)
            {
                res[k] = temp % 10;
                carry = temp/10;
            }
            else
            {
                res[k] = temp;
                carry = 0;
            }
            i--;
            j--;
            k--;
        }
        if(carry!=0)
            res[0] = carry;

        System.out.println("First number:");
        System.out.println(Arrays.toString(arr1));
        System.out.println("Second number:");
        System.out.println(Arrays.toString(arr2));
        System.out.println("Result Addition:");
        System.out.println(Arrays.toString(res));
    }
    public static void main(String[] args) {
        int arr1[] = {5, 1, 3, 4};
        int arr2[] = {4, 2, 4, 7};
        subtractArrayNums(arr1, arr2);
        addArrayNums(arr1, arr2);
    }
}
