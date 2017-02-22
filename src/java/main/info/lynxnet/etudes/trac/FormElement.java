package info.lynxnet.etudes.trac;

import java.io.Serializable;

public interface FormElement extends Serializable {
    int getOffset();

    void setOffset(int offset);
}
