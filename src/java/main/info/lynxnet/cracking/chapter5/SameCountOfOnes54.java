package info.lynxnet.cracking.chapter5;

public class SameCountOfOnes54 {
    static String lpad(String s, char c, int length) {
        if (s == null) {
            return null;
        }
        StringBuilder builder = new StringBuilder("");
        int lengthIWant = length - s.length();
        while (builder.length() < lengthIWant) {
            builder.append(c);
        }
        builder.append(s);
        return builder.toString();
    }

    static int countOnes(int value) {
        int count = 0;
        while (value != 0) {
            value = value & (value - 1);
            count++;
        }
        return count;
    }

    static void printSeries(int noOfOnes, int limit) {
        for (int i = 0; i < limit; i++) {
            if (countOnes(i) == noOfOnes) {
                System.out.println(String.format("%d: %s", i, lpad(Integer.toBinaryString(i), '0', 32)));
            }
        }
    }

    public static void main(String[] args) {
        printSeries(4, 512);
    }

}
