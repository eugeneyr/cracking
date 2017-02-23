package info.lynxnet.etudes.trac.functions;

import info.lynxnet.etudes.trac.Context;
import info.lynxnet.etudes.trac.StackElement;

public class TraceOn implements BuiltInFunction {
    public static final String FUNCTION_NAME = "tn";
    @Override
    public String getName() {
        return FUNCTION_NAME;
    }

    @Override
    public ExecutionResult execute(StackElement stackElement, Context context) {
        context.setTrace(true);
        return new ExecutionResult(stackElement.isActive(), "");
    }
}