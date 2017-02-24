package info.lynxnet.etudes.trac.functions;

import info.lynxnet.etudes.trac.Context;
import info.lynxnet.etudes.trac.StackElement;

public class TraceOff implements BuiltInFunction {
    public static final String FUNCTION_MNEMONICS = "tf";
    public static final String FUNCTION_NAME = "Trace Off";


    @Override
    public String getMnemonics() {
        return FUNCTION_MNEMONICS;
    }

    @Override
    public String getCategory() {
        return FunctionCategory.DEBUG;
    }

    @Override
    public String getName() {
        return FUNCTION_NAME;
    }

    @Override
    public ExecutionResult execute(StackElement stackElement, Context context) {
        context.setTrace(false);
        return new ExecutionResult(stackElement.isActive(), "");
    }
}