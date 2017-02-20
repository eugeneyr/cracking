package info.lynxnet.etudes.trac.functions;

import info.lynxnet.etudes.trac.Form;
import info.lynxnet.etudes.trac.Lexem;
import info.lynxnet.etudes.trac.StackElement;

import java.math.BigInteger;
import java.util.Map;

public class Add implements BuiltInFunction {
    public static final String FUNCTION_NAME = "ad";

    @Override
    public String getName() {
        return FUNCTION_NAME;
    }

    @Override
    public ExecutionResult execute(StackElement stackElement, Map<String, Form> formStorage) {
        StringBuilder sb = new StringBuilder();
        if (stackElement.getArguments().size() > 2) {
            Lexem.PrefixedNumber a = stackElement.getArguments().get(1).getValueAsNumber();
            Lexem.PrefixedNumber b = stackElement.getArguments().get(2).getValueAsNumber();
            BigInteger sum = a.getNumber().add(b.getNumber());
            sb.append(a.getPrefix()).append(sum);
        }
        return new ExecutionResult(stackElement.isActive(), sb.toString());
    }
}