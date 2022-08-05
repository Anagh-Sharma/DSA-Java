package Array_Problems;

public class P47_ReverseString {
    static void reverseString(String str)
    {
        char[] arr = str.toCharArray();
        int i=0, j=arr.length-1;
        while(i<j)
        {
            char temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
        System.out.println(arr);
    }

    public static void main(String[] args) {
        String str = "hello how are you";
        reverseString(str);
    }
}
