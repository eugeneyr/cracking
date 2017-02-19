package info.lynxnet.etudes.trac.functions;

import info.lynxnet.etudes.trac.Form;
import info.lynxnet.etudes.trac.StackElement;

import java.util.Map;

public class DeleteDefinition implements BuiltInFunction {
    public static final String FUNCTION_NAME = "dd";

    @Override
    public String getName() {
        return FUNCTION_NAME;
    }

    @Override
    public ExecutionResult execute(StackElement stackElement, Map<String, Form> formStorage) {
        stackElement.getArguments().subList(1, stackElement.getArguments().size()).stream().forEach(
                x -> {
                    if (formStorage.containsKey(x.getValue())) {
                        formStorage.remove(x.getValue());
                    }
                }
        );
        return new ExecutionResult(stackElement.isActive(), "");
    }
}