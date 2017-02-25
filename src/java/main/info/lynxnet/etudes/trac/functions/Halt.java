package info.lynxnet.etudes.trac.functions;

import info.lynxnet.etudes.trac.Context;
import info.lynxnet.etudes.trac.StackElement;

public class Halt implements BuiltInFunction {
    public static final String FUNCTION_MNEMONICS = "hl";
    public static final String FUNCTION_NAME = "Halt";

    @Override
    public String getMnemonics() {
        return FUNCTION_MNEMONICS;
    }

    @Override
    public String getCategory() {
        return FunctionCategory.IO;
    }

    @Override
    public String getName() {
        return FUNCTION_NAME;
    }

    @Override
    public ExecutionResult execute(StackElement stackElement, Context context) {
        context.setExitCode(stackElement.getArgumentIntValue(1));
        context.setExit(true);
        return new ExecutionResult(stackElement.isActive(), "");
    }
}