package info.lynxnet.etudes.trac.functions;

import info.lynxnet.etudes.trac.Context;
import info.lynxnet.etudes.trac.StackElement;

public class TraceOff implements BuiltInFunction {
    public static final String FUNCTION_NAME = "tf";
    @Override
    public String getName() {
        return FUNCTION_NAME;
    }

    @Override
    public ExecutionResult execute(StackElement stackElement, Context context) {
        context.getConfiguration().setTrace(false);
        return new ExecutionResult(stackElement.isActive(), "");
    }
}