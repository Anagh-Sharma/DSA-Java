package DSA_Recursion;

public class P94_TowerOfHanoiRecursion {
    static void towersOfHanoi(int disks, int start, int end)
    {
        if(disks == 1)
            System.out.println(start+" -> "+end);
        
        else
        {
            int other = 6 - (start + end);
            /*
             * - Move N – 1 disks from start to spare:	F(N – 1, start, spare)
             * - This corresponds to recursive call.
             */
            towersOfHanoi(disks - 1, start, other);
            /*
             * - Move Nth disk from start to end:	F(N, start, end)
             * - This corresponds to simply printing this move.
             */
            System.out.println(start+" -> "+end);
            /*
             * - Move N – 1 disks from spare to end:	F(N – 1, spare, end)
             * - This corresponds to recursive call.
             */
            towersOfHanoi(disks - 1, other, end);
        }
    }
    public static void main(String[] args) {
        towersOfHanoi(3, 1, 3);
    }
}
