package info.lynxnet.etudes.trac.functions;

import info.lynxnet.etudes.trac.Context;
import info.lynxnet.etudes.trac.NumericUtils;
import info.lynxnet.etudes.trac.PrefixedNumber;
import info.lynxnet.etudes.trac.StackElement;

import java.math.BigInteger;

public class Subtract implements BuiltInFunction {
    public static final String FUNCTION_MNEMONICS = "su";
    public static final String FUNCTION_NAME = "Subtract";

    @Override
    public String getMnemonics() {
        return FUNCTION_MNEMONICS;
    }

    @Override
    public String getCategory() {
        return FunctionCategory.ARITHMETIC;
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
            BigInteger result = a.getNumber().subtract(b.getNumber());
            sb.append(a.getPrefix()).append(result);
        }
        return new ExecutionResult(stackElement.isActive(), sb.toString());
    }
}