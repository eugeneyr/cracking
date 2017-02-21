package info.lynxnet.etudes.trac.functions;

import info.lynxnet.etudes.trac.Context;
import info.lynxnet.etudes.trac.StackElement;

public class Halt implements BuiltInFunction {
    public static final String FUNCTION_NAME = "hl";
    @Override
    public String getName() {
        return FUNCTION_NAME;
    }

    @Override
    public ExecutionResult execute(StackElement stackElement, Context context) {
        System.exit(0);
        return new ExecutionResult(stackElement.isActive(), "");
    }
}