package info.lynxnet.etudes.trac.functions;

public class BooleanStringMath {
    public static String padZeroLeft(String value, int length) {
        if (value == null) {
            value = "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length - value.length(); i++) {
            sb.append('0');
        }
        sb.append(value);
        return sb.toString();
    }

    public static String padZeroRight(String value, int length) {
        if (value == null) {
            value = "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(value);
        for (int i = 0; i < length - value.length(); i++) {
            sb.append('0');
        }
        return sb.toString();
    }

    public static char charOr(char a, char b) {
        return a == '1' || b == '1' ? '1' : '0';
    }

    public static char charAnd(char a, char b) {
        return a == '1' && b == '1' ? '1' : '0';
    }

    public static String union(String a, String b) {
        int length = Math.max(a.length(), b.length());
        a = padZeroLeft(a, length);
        b = padZeroLeft(b, length);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(charOr(a.charAt(i), b.charAt(i)));
        }
        return sb.toString();
    }

    public static String intersection(String a, String b) {
        int length = Math.max(a.length(), b.length());
        a = padZeroLeft(a, length);
        b = padZeroLeft(b, length);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(charAnd(a.charAt(i), b.charAt(i)));
        }
        return sb.toString();
    }

    public static String complement(String a) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < a.length(); i++) {
            sb.append(a.charAt(i) == '1' ? '0' : '1');
        }
        return sb.toString();
    }

    public static String shift(String a, int n) {
        if (Math.abs(n) >= a.length()) {
            return padZeroLeft("", a.length());
        }
        if (n < 0) {
            return padZeroLeft(a.substring(0, a.length() + n), a.length());
        } else {
            return padZeroRight(a.substring(n, a.length()), a.length());
        }
    }

    public static String rotate(String a, int n) {
        if (Math.abs(n) > a.length()) {
            n = n % a.length();
        }
        if (n < 0) {
            return a.substring(a.length() + n, a.length()) + a.substring(0, a.length() + n);
        } else {
            return a.substring(n, a.length()) + a.substring(0, n);
        }
    }
}
