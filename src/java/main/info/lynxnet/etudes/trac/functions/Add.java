package info.lynxnet.etudes.trac.functions;

import info.lynxnet.etudes.trac.*;

import java.math.BigInteger;

public class Add implements BuiltInFunction {
    public static final String FUNCTION_MNEMONICS = "ad";
    public static final String FUNCTION_NAME = "Add";

    @Override
    public String getMnemonics() {
        return FUNCTION_MNEMONICS;
    }

    @Override
    public String getCategory() {
        return FunctionCategory.ARYTHMETIC;
    }

    @Override
    public String getName() {
        return FUNCTION_NAME;
    }

    @Override
    public ExecutionResult execute(StackElement stackElement, Context context) {
        StringBuilder sb = new StringBuilder();
        if (stackElement.getArguments().size() > 2) {
            PrefixedNumber a = NumericUtils.getValueAsNumber(stackElement.getArgumentValue(1));
            PrefixedNumber b = NumericUtils.getValueAsNumber(stackElement.getArgumentValue(2));
            BigInteger sum = a.getNumber().add(b.getNumber());
            sb.append(a.getPrefix()).append(sum);
        }
        return new ExecutionResult(stackElement.isActive(), sb.toString());
    }
}