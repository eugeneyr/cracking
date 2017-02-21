package info.lynxnet.etudes.trac.functions;

import info.lynxnet.etudes.trac.Context;
import info.lynxnet.etudes.trac.StackElement;

public class ListNames implements BuiltInFunction {
    public static final String FUNCTION_NAME = "ln";

    @Override
    public String getName() {
        return FUNCTION_NAME;
    }

    @Override
    public ExecutionResult execute(StackElement stackElement, Context context) {
        String separator = stackElement.getArgumentValue(1);
        return new ExecutionResult(stackElement.isActive(), String.join(separator, context.getFormStorage().keySet()));
    }
}