package Array_Problems;

public class P55_StringCompression {
    // TC : O(N)
    static int stringCompress(String str, int n)
    {
        StringBuilder s = new StringBuilder();
        int count = 0, i = 0, j = 0;
        while(i<n)
        {
            count = 0;
            j=i;
            while(j<n)
            {
                if(str.charAt(i) == str.charAt(j))
                {
                    count++;
                    j++;
                }
                else
                    break;
            }
            // Case 1: When count is 1
			if (count == 1) 
            {
	    		// System.out.print(str.charAt(i));
                s.append(str.charAt(i));
            } 
            // Case 2: When count is more than 1
            else 
            {
                // System.out.print(str.charAt(i)); 
                // System.out.print(count);
                s.append(str.charAt(i));
                s.append(count);
            }
            i = i + count;
        }
        str = s.toString();
        System.out.println(str);
        return str.length();
    }
    
    public static void main(String[] args) {
        String str = "aabbbbcccfffffad";
        int n = str.length();
        System.out.println(stringCompress(str, n));
    }
}
