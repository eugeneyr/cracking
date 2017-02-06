package info.lynxnet.etudes.trac;

public class Lexem {
    private int offset;
    private String value;

    public Lexem(int offset, String value) {
        this.offset = offset;
        this.value = value;
    }

    public int getOffset() {
        return offset;
    }

    public String getValue() {
        return value;
    }
}
