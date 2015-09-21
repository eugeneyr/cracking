package info.lynxnet.cracking.chapter5;

import java.util.Arrays;

/**
 * Draw Line:
 * A monochrome screen is stored as a single array of bytes, allowing eight consecutive pixels to be stored in one byte.
 * The screen has width w, where w is divisible by 8 (that is, no byte will be split across rows.) The height of the screen,
 * of course, can be derived from the length of the array and the width. Implement a function that draws a horizontal line
 * from (x1, y) to (x2, y).
 */
public class DrawLine58 {
    private static byte getPixel(byte[] screen, int width, int x, int y) {
        byte octet = screen[(y * width + x) / 8];
        return (byte) ((octet >>> (x % 8)) & 1);
    }

    private static void setPixel(byte[] screen, int width, int x, int y, boolean one) {
        byte octet = screen[(y * width + x) / 8];
        screen[(y * width + x) / 8] = one ? (byte) (octet | 1 << (x % 8)) : (byte) (octet & ~(1 << (x % 8)));
    }

    public static void drawLine(byte[] screen, int width, int x1, int x2, int y) {
        if (screen == null || screen.length == 0) {
            throw new IllegalArgumentException("Bad, bad input. Correct.");
        }
        int height = (screen.length * 8) / width;
        if (y < 0 || y >= height) {
            throw new IllegalArgumentException("Y is out of range");
        }
        if (x1 < 0 || x1 >= width || x2 < 0 || x1 >= width || x2 < x1) {
            throw new IllegalArgumentException("X1 or X2 are out of range");
        }
        for (int i = x1; i < x2; i++) {
            setPixel(screen, width, i, y, true);
        }
    }

    static String render(byte[] screen, int width) {
        StringBuilder sb = new StringBuilder();
        int height = (screen.length * 8) / width;
        renderBorder(width, sb);
        sb.append('\n');
        for (int j = 0; j < height; j++) {
            sb.append('|');
            for (int i = 0; i < width; i++) {
                sb.append(getPixel(screen, width, i, j) == 1 ? '*' : ' ');
            }
            sb.append("|\n");
        }
        renderBorder(width, sb);
        return sb.toString();
    }

    private static void renderBorder(int width, StringBuilder sb) {
        sb.append('+');
        for (int i = 0; i < width; i++) {
            sb.append('-');
        }
        sb.append('+');
    }

    public static void main(String[] argv) {
        byte[] screen = new byte[250];
        Arrays.fill(screen, (byte)0);
        drawLine(screen, 80, 4, 44, 7);
        drawLine(screen, 80, 20, 60, 12);
        drawLine(screen, 80, 36, 76, 17);
        System.out.println(render(screen, 80));
    }
}


