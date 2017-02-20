package info.lynxnet.etudes.trac;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexem {
    private int offset;
    private String value;
    private boolean completed;

    static final Pattern intPattern = Pattern.compile("^(.*)((\\-?)(\\d+))$");
    static final Pattern decimalPattern = Pattern.compile("^(.*)((\\-?)(\\d*)\\.(\\d+))$");

    public static class PrefixedNumber {
        private String prefix;
        private BigInteger number;

        public PrefixedNumber(String prefix, BigInteger number) {
            this.prefix = prefix;
            this.number = number;
        }

        public String getPrefix() {
            return prefix;
        }

        public BigInteger getNumber() {
            return number;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder();
            if (prefix != null) {
                sb.append("prefix='");
            }
            if (number != null) {
                sb.append(number);
            }
            return sb.toString();
        }
    }

    public Lexem(int offset, String value, boolean completed) {
        this.offset = offset;
        this.value = value;
        this.completed = completed;
    }

    public Lexem(int offset, String value) {
        this.offset = offset;
        this.value = value;
        this.completed = true;
    }

    public Lexem(int offset) {
        this.offset = offset;
        this.completed = false;
    }

    public boolean isCompleted() {
        return completed;
    }

    public int getOffset() {
        return offset;
    }

    public String getValue() {
        return value;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public int getIntValue() {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    public long getLongValue() {
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    public double getDoubleValue() {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    public PrefixedNumber getValueAsNumber() {
        String prefix = "";
        BigInteger numValue = new BigInteger("0");
        if (value != null) {
            Matcher intMatcher = intPattern.matcher(value);
            if (intMatcher.matches()) {
                numValue = new BigInteger(intMatcher.group(2));
                prefix = intMatcher.group(1);
            }
        }
        return new PrefixedNumber(prefix, numValue);
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Lexem{");
        sb.append("offset=").append(offset);
        sb.append(", value='").append(value).append('\'');
        sb.append(", completed=").append(completed);
        sb.append('}');
        return sb.toString();
    }
}
