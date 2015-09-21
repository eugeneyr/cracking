package info.lynxnet.cracking.chapter5;

import java.util.ArrayList;
import java.util.List;

/*

*/

public class BitFlipper53 {
    public static int findLongestRunOfOnes(int value) {
        int max = 0;
        int counter = 0;
        while (value != 0) {
            if ((value & 1) == 0) {
                // the run was broken
                if (max < counter) {
                    max = counter;
                }
                counter = 0;
            } else {
                counter++;
            }
            value = value >>> 1;
        }
        if (max < counter) {
            max = counter;
        }
        return max;
    }

    public static int findFlipPosition(int value) {
        int maxRun = findLongestRunOfOnes(value);
        int winner = -1;
        for (int i = 0; i < Math.min(Integer.highestOneBit(value) + 1, 32); i++) {
            if ((1 << i & value) == 0) {
                int newRun = findLongestRunOfOnes(value | (1 << i));
                if (newRun > maxRun) {
                    winner = i;
                    maxRun = newRun;
                }
            }
        }
        return winner;
    }

    public static int findMaxFlippedRun(int value) {
        int maxRun = findLongestRunOfOnes(value);
        for (int i = 0; i < Math.min(Integer.highestOneBit(value) + 1, 32); i++) {
            if ((1 << i & value) == 0) {
                int newRun = findLongestRunOfOnes(value | (1 << i));
                if (newRun > maxRun) {
                    maxRun = newRun;
                }
            }
        }
        return maxRun;
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>() {{
            add(0b011011101111);
            add(0);
            add(1);
            add(0b101);
            add(0b1101101);
        }};

        for (Integer val : list) {
            System.out.println(String.format("Winner position for %s (%d) is %d", Integer.toBinaryString(val), val, findFlipPosition(val)));
            System.out.println(String.format("Winner run length for %s is %d", Integer.toBinaryString(val), findMaxFlippedRun(val)));
            int flipped = val | (1 << findFlipPosition(val));
            System.out.println(String.format("Flipped: %s (%d)", Integer.toBinaryString(flipped), flipped));
        }
    }

}
