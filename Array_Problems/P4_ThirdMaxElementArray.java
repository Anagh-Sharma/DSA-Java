package Array_Problems;

public class P4_ThirdMaxElementArray {

    // Naive Approach: This function finds the first and second largest 
    // elements then excludes them in the search for the third largest
    // TC : O(N)
    static void thirdLargest_approach_1(int arr[], int n) {
        int max = -128;
        for(int i = 0; i < n; i++) {
            if(arr[i] > max) {
                max = arr[i];
            }
        }
        int secondMax = -128;
        for(int i = 0; i < n; i++) {
            if(arr[i] != max) {
                if(arr[i] > secondMax) {
                    secondMax = arr[i];
                }
            }
        }
        int thirdMax = -128;
        for(int i = 0; i < n; i++) {
            if(arr[i] != max && arr[i] != secondMax) {
                if(arr[i] > thirdMax) {
                    thirdMax = arr[i];
                }
            }
        }
        System.out.println("Third Largest : " + thirdMax);
    }

    // Efficient approach: This function stores the first, 
    // second, and third largest elements at each iteration
    // TC : O(N)
    static void thirdLargest_approach_2(int arr[], int n) {
        // The variables store the first, second, and third largest elements
        int max = -1;
        int secondMax = -1;
        int thirdMax = -1;
        for(int i = 0; i < n; i++) {
            if(arr[i] > max) {
                thirdMax = secondMax;
                secondMax = max;
                max = arr[i];
            }

            if(arr[i] != max) {
                if(arr[i] > secondMax) {
                    thirdMax = secondMax;
                    secondMax = arr[i];
                }
            }

            if(arr[i] != max && arr[i] != secondMax) {
                if(arr[i] > thirdMax) {
                    thirdMax = arr[i];
                }
            }
        }
        if(n == 1)
            thirdMax = max;
        else if(n<3)
            thirdMax = secondMax;

        System.out.println("Third Largest : " + thirdMax);
    }

    // TC : O(N)
    static int thirdLargest_approach_3(int nums[], int n)
    {
        int max = 0;
        long currMax = 0;
        Long lastMax = Long.MAX_VALUE;// last max is largest possible num as all elements will be
        // smaller and thus will be selected in the if statement of the nested for loop
        // For the next lastMax will be set to curr max or the found maximum element as
        // the max num of the next iteration will have to smaller than this.
        int thirdMax = 0;
        for(int i = 0; i<3; i++)
        {
            currMax = Long.MIN_VALUE;// current max is smallest possible num
            // find num larger than curr max but smaller than lastmax
            for (int j = 0; j < nums.length; j++)
            {
                if (nums[j] < lastMax && nums[j] > currMax) 
                    currMax = nums[j];
            }
            
            if(i==0)// Store maximum
                max = (int)currMax;
            if(i==2)
            {
                if(currMax == Long.MIN_VALUE)// Return maximum if no third maximum
                    thirdMax = max;
                else
                    thirdMax = (int)currMax;
            }
            // Lastmax for next iteration is current max 
            lastMax = currMax;
        }
        return thirdMax;
    }

    public static void main(String[] args) {
        int arr[] = {3, 2, 1};
        int n = arr.length;
        thirdLargest_approach_1(arr, n);
        thirdLargest_approach_2(arr, n);
        System.out.println("Third Largest : "+thirdLargest_approach_3(arr, n));
    }
}
