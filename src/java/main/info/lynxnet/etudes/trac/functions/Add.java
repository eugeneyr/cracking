package info.lynxnet.etudes.trac.functions;

import info.lynxnet.etudes.trac.Context;
import info.lynxnet.etudes.trac.Lexem;
import info.lynxnet.etudes.trac.StackElement;

import java.math.BigInteger;

public class Add implements BuiltInFunction {
    public static final String FUNCTION_NAME = "ad";

    @Override
    public String getName() {
        return FUNCTION_NAME;
    }

    @Override
    public ExecutionResult execute(StackElement stackElement, Context context) {
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