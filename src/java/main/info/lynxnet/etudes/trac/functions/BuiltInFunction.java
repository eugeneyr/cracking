package info.lynxnet.etudes.trac.functions;

import info.lynxnet.etudes.trac.Form;
import info.lynxnet.etudes.trac.StackElement;

import java.util.Map;

public interface BuiltInFunction {
    String getName();
    String execute(StackElement stackElement, Map<String, Form> formStorage);
}
