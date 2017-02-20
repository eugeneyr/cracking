package info.lynxnet.etudes.trac.functions;

import info.lynxnet.etudes.trac.Form;
import info.lynxnet.etudes.trac.StackElement;

import java.util.Map;

public class BooleanUnion implements BuiltInFunction {
    public static final String FUNCTION_NAME = "bu";

    @Override
    public String getName() {
        return FUNCTION_NAME;
    }

    @Override
    public ExecutionResult execute(StackElement stackElement, Map<String, Form> formStorage) {
        StringBuilder sb = new StringBuilder();
        if (stackElement.getArguments().size() > 2) {
            String a = stackElement.getArguments().get(1).extractBoolean();
            String b = stackElement.getArguments().get(2).extractBoolean();
            sb.append(BooleanStringMath.union(a, b));
        }
        return new ExecutionResult(stackElement.isActive(), sb.toString());
    }
}