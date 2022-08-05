package DSA_Stack;

/*
 * - For the two stacks, push and pop elements using a single array.
 * - For the first stack: Push and pop elements from first index to the middle.
 * - For the second stack: Push and pop elements from middle+1 to the end.
 * - Maintain counters for counting the elements inserted into the stacks.
 * - For stack 1, the counter is initialized to -1 and is incremented before 
 *   an element is added. The element is added to the array’s index equal to the counter.
 * - For stack 2, the counter is initialized to size of the array and is decremented 
 *   before an element is added. The element is added to the array’s index equal to the counter.
 * - The stacks are full when the counter of stack 1 becomes equal to the counter of the stack 2.
 */

public class P128_TwoStacksOneArray {
    int arr[];
    int capacity;
    int leftTop;
    int rightTop;

    P128_TwoStacksOneArray(int capacity) {
        arr = new int[capacity];
        this.capacity = capacity;
        leftTop = -1;
        rightTop = capacity;
    }

    void leftPush(int e) {
        if(leftTop < rightTop - 1) {
            leftTop++;
            arr[leftTop] = e;
        }
    }

    void rightPush(int e) {
        if(leftTop < rightTop - 1) {
            rightTop--;
            arr[rightTop] = e;
        }
    }

    int leftPop() {
        if (leftTop == -1) {
            throw new RuntimeException("Left Stack is Empty...");
        }
        int element = arr[leftTop];
        arr[leftTop--] = 0;
        return element;
    }

    int rightPop() {
        if (rightTop == -1) {
            throw new RuntimeException("Right Stack is Empty...");
        }
        int element = arr[rightTop];
        arr[rightTop++] = 0;
        return element;
    }

    void print() {
        for(int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ", ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        P128_TwoStacksOneArray st =  new P128_TwoStacksOneArray(8);
        st.leftPush(10);
        st.leftPush(20);
        st.leftPush(30);
        st.leftPush(90);
        st.leftPush(100);

        st.print();

        st.rightPush(80);
        st.rightPush(50);
        st.rightPush(60);

        st.print();
        st.leftPop();
        st.print();
        st.rightPop();
        st.print();
    }
}
