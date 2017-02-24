package info.lynxnet.etudes.trac;

import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumericUtils {
    static final Pattern INT_PATTERN = Pattern.compile("^(.*?)(([\\-\\+]?)(\\d+))$");
    static final Pattern DECIMAL_PATTERN = Pattern.compile("^(.*?)(([\\-\\+]?)(\\d*)\\.(\\d+))$");
    static final Pattern BOOLEAN_PATTERN = Pattern.compile("^(.*?)([01]+)$");

    protected static PrefixedNumber parsePrefixedNumber(String value, Pattern regex, int radix) {
        String prefix = "";
        BigInteger numValue = BigInteger.ZERO;
        if (value != null) {
            Matcher matcher = regex.matcher(value);
            if (matcher.matches()) {
                numValue = new BigInteger(matcher.group(2), radix);
                prefix = matcher.group(1);
            }
        }
        return new PrefixedNumber(prefix, numValue);
    }

    public static int parseRadix(String radix) {
        int rv = 0;
        if (radix != null && radix.length() == 1) {
            char letter = radix.charAt(0);
            if (letter >= '1' && letter <= '9') {
                rv = letter - '0' + 1;
            } else if (letter >= 'A' && letter <= 'Z') {
                rv = letter - 'A' + 11;
            } else if (letter >= 'a' && letter <= 'z') {
                rv = letter - 'a' + 11;
            }
        }
        return rv;
    }

    public static PrefixedNumber getValueAsNumber(String value) {
        return parsePrefixedNumber(value, INT_PATTERN, 10);
    }

    public static PrefixedNumber getValueAsNumberInRadix(String value, String radix) {
        int radixVal = parseRadix(radix);
        if (radixVal > 0) {
            String range;
            char radChar = radix.charAt(0);
            if (radChar <= '9') {
                range = String.format("0-%c", radChar);
            } else {
                range = String.format("0-9A-%ca-%c", Character.toUpperCase(radChar), Character.toLowerCase(radChar));
            }
            Pattern pattern = Pattern.compile(String.format("^(.*?)(([\\-\\+]?)([%s]+))$", range));
            if (pattern.matcher(value).matches()) {
                return parsePrefixedNumber(value, pattern, radixVal);
            }
        }
        return new PrefixedNumber(value, null);
    }

    public static PrefixedNumber getValueAsBoolean(String value ) {
        return parsePrefixedNumber(value, BOOLEAN_PATTERN, 2);
    }

    public static String extractBoolean(String value) {
        if (value != null) {
            Matcher matcher = BOOLEAN_PATTERN.matcher(value);
            if (matcher.matches()) {
                return matcher.group(2);
            }
        }
        return "";
    }

}
