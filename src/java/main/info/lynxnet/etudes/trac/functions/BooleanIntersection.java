package info.lynxnet.etudes.trac.functions;

import info.lynxnet.etudes.trac.Context;
import info.lynxnet.etudes.trac.StackElement;

public class BooleanIntersection implements BuiltInFunction {
    public static final String FUNCTION_NAME = "bi";

    @Override
    public String getName() {
        return FUNCTION_NAME;
    }

    @Override
    public ExecutionResult execute(StackElement stackElement, Context context) {
        StringBuilder sb = new StringBuilder();
        if (stackElement.getArguments().size() > 2) {
            String a = stackElement.getArguments().get(1).extractBoolean();
            String b = stackElement.getArguments().get(2).extractBoolean();
            sb.append(BooleanStringMath.intersection(a, b));
        }
        return new ExecutionResult(stackElement.isActive(), sb.toString());
    }
}