package info.lynxnet.etudes.trac;

public class FormMarker implements FormElement {
    private int ordinal;
    private int offset;

    public FormMarker(int ordinal, int offset) {
        this.ordinal = ordinal;
        this.offset = offset;
    }

    public int getOrdinal() {
        return ordinal;
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
        sb.append("ordinal=").append(ordinal);
        sb.append(", offset=").append(offset);
        sb.append('}');
        return sb.toString();
    }
}
