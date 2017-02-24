package info.lynxnet.etudes.trac.functions;

import info.lynxnet.etudes.trac.Context;
import info.lynxnet.etudes.trac.NumericUtils;
import info.lynxnet.etudes.trac.PrefixedNumber;
import info.lynxnet.etudes.trac.StackElement;


public class ChangeRadix implements BuiltInFunction {
    public static final String FUNCTION_NAME = "cx";

    @Override
    public String getName() {
        return FUNCTION_NAME;
    }

    @Override
    public ExecutionResult execute(StackElement stackElement, Context context) {
        StringBuilder sb = new StringBuilder();
        if (stackElement.getArguments().size() > 3) {
            int radix1 = NumericUtils.parseRadix(stackElement.getArgumentValue(1));
            int radix2 = NumericUtils.parseRadix(stackElement.getArgumentValue(2));
            if (radix1 > 0 && radix2 > 0) {
                PrefixedNumber number = NumericUtils.getValueAsNumberInRadix(stackElement.getArgumentValue(3), stackElement.getArgumentValue(1));
                sb.append(number.getPrefix());
                if (number.getNumber() != null) {
                    sb.append(number.getNumber().toString(radix2));
                }
            }
        }
        return new ExecutionResult(stackElement.isActive(), sb.toString());
    }
}