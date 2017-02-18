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

    @Override
    public int getOffset() {
        return offset;
    }

    @Override
    public void setOffset(int offset) {
        this.offset = offset;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FormMarker{");
        sb.append("value=").append(value);
        sb.append(", offset=").append(offset);
        sb.append('}');
        return sb.toString();
    }
}
