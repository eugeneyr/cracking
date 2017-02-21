package info.lynxnet.etudes.trac.functions;

import info.lynxnet.etudes.trac.Context;
import info.lynxnet.etudes.trac.StackElement;

public class DeleteAll implements BuiltInFunction {
    public static final String FUNCTION_NAME = "da";
    @Override
    public String getName() {
        return FUNCTION_NAME;
    }

    @Override
    public ExecutionResult execute(StackElement stackElement, Context context) {
        context.getFormStorage().clear();
        return new ExecutionResult(stackElement.isActive(), "");
    }
}