package info.lynxnet.cracking.chapter5;

/*
Conversion: Write a function to determine the number of bits you would need to flip to convert integer A to integer B.
*/
public class Conversion56 {
    static int countOnes(int value) {
        int count = 0;
        while (value != 0) {
            value = value & (value - 1);
            count++;
        }
        return count;
    }

    public static int findBitDifference(int a, int b) {
        int xorred =  a ^ b;
        return countOnes(xorred);
    }

    public static void main(String[] args) {
        int a = 0b11101;
        int b = 0b01111;
        System.out.println(findBitDifference(a, b));
    }
}
