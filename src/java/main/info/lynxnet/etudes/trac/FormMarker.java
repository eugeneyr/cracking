package info.lynxnet.etudes.trac;

public class FormMarker implements FormElement {
    private int value;

    public FormMarker(int value) {
        this.value = value;
    }

    @Override
    public Object getValue() {
        return value;
    }
}
