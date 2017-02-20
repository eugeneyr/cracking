package info.lynxnet.etudes.trac.functions;

import info.lynxnet.etudes.trac.Form;
import info.lynxnet.etudes.trac.StackElement;

import java.util.Map;

public class Equality implements BuiltInFunction {
    public static final String FUNCTION_NAME = "eq";

    @Override
    public String getName() {
        return FUNCTION_NAME;
    }

    @Override
    public ExecutionResult execute(StackElement stackElement, Map<String, Form> formStorage) {
        StringBuilder sb = new StringBuilder();
        if (stackElement.getArguments().size() > 1) {
            String a = stackElement.getArgumentValue(1);
            String b = stackElement.getArgumentValue(2);
            String t = stackElement.getArgumentValue(3);
            String f = stackElement.getArgumentValue(4);
            sb.append(a.equals(b)? t : f);
        }
        return new ExecutionResult(stackElement.isActive(), sb.toString());
    }
}