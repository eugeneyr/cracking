package info.lynxnet.etudes.trac.functions;

import info.lynxnet.etudes.trac.Form;
import info.lynxnet.etudes.trac.StackElement;

import java.util.Map;

public class ListNames implements BuiltInFunction {
    public static final String FUNCTION_NAME = "ln";

    @Override
    public String getName() {
        return FUNCTION_NAME;
    }

    @Override
    public ExecutionResult execute(StackElement stackElement, Map<String, Form> formStorage) {
        String separator = stackElement.getArgumentValue(1);
        return new ExecutionResult(stackElement.isActive(), String.join(separator, formStorage.keySet()));
    }
}