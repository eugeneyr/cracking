package info.lynxnet.etudes.trac.functions;

import info.lynxnet.etudes.trac.Context;
import info.lynxnet.etudes.trac.NumericUtils;
import info.lynxnet.etudes.trac.StackElement;

public class BooleanComplement implements BuiltInFunction {
    public static final String FUNCTION_MNEMONICS = "bc";
    public static final String FUNCTION_NAME = "Boolean Complement";

    @Override
    public String getMnemonics() {
        return FUNCTION_MNEMONICS;
    }

    @Override
    public String getCategory() {
        return FunctionCategory.BOOLEAN;
    }

    @Override
    public String getName() {
        return FUNCTION_NAME;
    }

    @Override
    public ExecutionResult execute(StackElement stackElement, Context context) {
        StringBuilder sb = new StringBuilder();
        if (stackElement.getArguments().size() > 1) {
            String a = NumericUtils.extractBoolean(stackElement.getArgumentValue(1));
            sb.append(BooleanStringMath.complement(a));
        }
        return new ExecutionResult(stackElement.isActive(), sb.toString());
    }
}