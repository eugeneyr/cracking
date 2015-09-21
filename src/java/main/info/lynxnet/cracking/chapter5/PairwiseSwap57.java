package info.lynxnet.cracking.chapter5;

/**
 * Write a program to swap odd and even bits in an integer with as few instructions as possible
 * (e.g., bit 0 and bit 1 are swapped, bit 2 and bit 3 are swapped, and so on)
 */
public class PairwiseSwap57 {
    private static final int ODDS = 0b01010101010101010101010101010101;
    private static final int EVENS = 0b10101010101010101010101010101010;

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


    public static int swapBitPairs(int number) {
        return (((number >>> 1) & ODDS) | (number << 1) & EVENS);

        // 00101101 => (00010110 & 0101010101) | (01011010 & 1010101010) =
        //              00010100               |  00001010
    }

    public static void main(String[] argv) {
        System.out.println(lpad(Integer.toBinaryString(0b101101), '0', 32));
        System.out.println(lpad(Integer.toBinaryString(swapBitPairs(0b101101)), '0', 32));

        System.out.println(lpad(Integer.toBinaryString(ODDS), '0', 32));
        System.out.println(lpad(Integer.toBinaryString(swapBitPairs(ODDS)), '0', 32));

        System.out.println(lpad(Integer.toBinaryString(EVENS), '0', 32));
        System.out.println(lpad(Integer.toBinaryString(swapBitPairs(EVENS)), '0', 32));
    }
}
