package info.lynxnet.etudes.trac;

public class FormSegment implements FormElement {
    private String value;

    public FormSegment(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
