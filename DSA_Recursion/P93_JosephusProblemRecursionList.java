package DSA_Recursion;

import java.util.ArrayList;

public class P93_JosephusProblemRecursionList {
    /*
     * Recursion sub-problem:
     * - Skip a constant number of indexes, including the current index.
     * - Remove the element at the last index of the skipped indices.
     * - Traverse the array in a circular manner and repeat the process for the
     *   index element after the removed element.
     */
    static int josephusListRecursion(int currentPerson, int skipPeople, ArrayList<Integer> peopleAlive) 
    {
        /*
         * Base case:
         * When the size of the list becomes 1, return the lone element.
         */
        if (peopleAlive.size() == 1) 
        {
          return peopleAlive.get(0);
        }
        /*
         * - Update currentPerson index:
         * - The modulus helpd traverse the array in a circular manner 
         *   during recursion converting the number exceeding the size 
         *   to a valid index.
         * - currentPerson reaches the last index of the skipped sequence.
         *   So, if the currentPerson or current index is 0 then, 
         *   currentPerson will be changed to 1 when the step size is 2
         *   where the 0 index is also included.
         * - This last index of this sequence of N needs to be removed.
         */
        currentPerson = ((currentPerson + skipPeople) % peopleAlive.size());
        /*
         * - The last person of the sequence at currentPerson is removed.
         */
        peopleAlive.remove(currentPerson);
        /*
         * Recursive case:
         * After removing the element at currentIndex, the new element at
         * that index is the element after the previously removed element.
         * The recursive call is applied to it.
         */
        return josephusListRecursion(currentPerson, skipPeople, peopleAlive);
    }
    /*
     * - The number of people reduces by 1 every time as a person is removed so the 
     *   reduced number of people is always:                N - 1
     * - Let the starting position be:		                i
     * - Then the removed person is at:		                [i + (k - 1)] % N
     *   - Since, the current person is also counted in the sequence of size k, therefore, 
     *     the steps moved ahead are k-1. 
     *   - The modulus is taken to allow circular traversal when the index exceeds the size N.
     * - The person from where the counting will start next will be after the person who 
     *   was removed last. This person is at the index:     [i + (k - 1)] % N + 1
     * - After the first person gets removed the number of people becomes N – 1, the current 
     *   person is where the removed person was:	        [1 + (k - 1)] % N + 1
     *   Here, i = 1 and J(N, k) problem becomes J(N – 1, k) problem, i.e., the problem becomes 
     *   smaller.
     * - When N = 1, the solution is J(1, k) = 1 as only one person at index 1 is remaining.
     * - This is the smallest part of the problem, i.e., base case.
     * 
     * - Therefore if J(i, k) function that returns an index reached after starting from and 
     *   including index i and moving k times, and the returned index acts as the starting 
     *   point for the next similar traversal, then a recursive solution can be defined, 
     *   wherein, the i value is a recursive call to the next smaller problem which returns 
     *   this problem’s starting index.
     * - The starting index for the recursive call of size N is defined by the recursive call 
     *   for size N – 1, and so on until the base case is reached wherein the size is 1 and the 
     *   result is also 1 as the first element (or person) is the only one remaining and thus 
     *   is the solution.
     * - This can be defined as:		J (N, k) = (J (N – 1, k) + (k – 1)) % N + 1
     * - With:			            	J (1, k) = 1
     */
    static int josephusRecursion(int n, int k)
    {
        /*
         * Base case:
         * When the total number of people are 1 than the element 
         * remaining is the solution.
         */
        if(n == 1)
            return 1;
        /*
         * The remaining index for a certain size after traversing the 
         * entire group of that size with the give skip size is computed
         * by otaining the index of 
         */
        return (josephusRecursion(n - 1, k) + (k - 1)) % n + 1;
    }

    public static void main(String[] args) 
    {
        int totalPeople = 5;
        /*
         * Indexing starts from 0 and the first N-1 people will be skipped using the formula:
         * currentperson = (currentperson + skipPeople) % total people remaining
         */
        int currentPerson = 0;
        /*
         * skipPeople, N: 
         * - The people skipped includes the current person as well.
         * - The last person to be skipped will be removed.
         */
        int skipPeople = 2;
        ArrayList<Integer> peopleAlive = new ArrayList<>();
        for (int i = 1; i <= totalPeople; i++) 
        {
            peopleAlive.add(i);
        }
        /*
         * As, the skipped people includes the element at the current index as well
         * and not N numbers after it, therefore, "skipPeople" is subtracted by 1
         * to include the element itself. 
         * The skipping starts from the current index, therefore the size of increment
         * has to be given size - 1. Otherwise, the incremented index will be N places
         * after the currrent index, instead of, N place from the current index.
         */
        System.out.println(josephusListRecursion(currentPerson, skipPeople-1, peopleAlive));
        System.out.println(josephusRecursion(10, skipPeople));
    }
}
