package info.lynxnet.etudes.trac;

import java.util.Map;

public interface BuiltInFunction {
    String getName();
    String execute(StackElement stackElement, Map<String, Form> formStorage);
}
