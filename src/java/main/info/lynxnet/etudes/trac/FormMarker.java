package info.lynxnet.etudes.trac;

public class FormMarker implements FormElement {
    private int value;
    private int offset;

    public FormMarker(int value, int offset) {
        this.value = value;
        this.offset = offset;
    }

    @Override
    public Object getValue() {
        return value;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}
