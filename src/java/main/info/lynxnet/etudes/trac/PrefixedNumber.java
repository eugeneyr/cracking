package info.lynxnet.etudes.trac;

import java.math.BigInteger;

public class PrefixedNumber {
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
            sb.append(prefix);
        }
        if (number != null) {
            sb.append(number);
        }
        return sb.toString();
    }
}
