package info.lynxnet.etudes.trac.functions;

import info.lynxnet.etudes.trac.Context;
import info.lynxnet.etudes.trac.StackElement;

public class BooleanShift implements BuiltInFunction {
    public static final String FUNCTION_NAME = "bs";

    @Override
    public String getName() {
        return FUNCTION_NAME;
    }

    @Override
    public ExecutionResult execute(StackElement stackElement, Context context) {
        StringBuilder sb = new StringBuilder();
        if (stackElement.getArguments().size() > 1) {
            String a = stackElement.getArguments().get(1).extractBoolean();
            int bits = stackElement.getArgumentIntValue(2);
            sb.append(BooleanStringMath.shift(a, bits));
        }
        return new ExecutionResult(stackElement.isActive(), sb.toString());
    }
}