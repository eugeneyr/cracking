package info.lynxnet.etudes.trac.functions;

import info.lynxnet.etudes.trac.Form;
import info.lynxnet.etudes.trac.StackElement;

import java.util.Map;

public class DeleteAll implements BuiltInFunction {
    public static final String FUNCTION_NAME = "da";
    @Override
    public String getName() {
        return FUNCTION_NAME;
    }

    @Override
    public ExecutionResult execute(StackElement stackElement, Map<String, Form> formStorage) {
        formStorage.clear();
        return new ExecutionResult(stackElement.isActive(), "");
    }
}