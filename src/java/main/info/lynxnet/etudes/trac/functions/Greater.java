package info.lynxnet.etudes.trac.functions;

import info.lynxnet.etudes.trac.Form;
import info.lynxnet.etudes.trac.Lexem;
import info.lynxnet.etudes.trac.StackElement;

import java.util.Map;

public class Greater implements BuiltInFunction {
    public static final String FUNCTION_NAME = "gr";

    @Override
    public String getName() {
        return FUNCTION_NAME;
    }

    @Override
    public ExecutionResult execute(StackElement stackElement, Map<String, Form> formStorage) {
        StringBuilder sb = new StringBuilder();
        if (stackElement.getArguments().size() > 3) {
            Lexem.PrefixedNumber a = stackElement.getArguments().get(1).getValueAsNumber();
            Lexem.PrefixedNumber b = stackElement.getArguments().get(2).getValueAsNumber();
            String t = stackElement.getArgumentValue(3);
            String f = stackElement.getArgumentValue(4);
            sb.append(a.getNumber().compareTo(b.getNumber()) > 0? t : f);
        }
        return new ExecutionResult(stackElement.isActive(), sb.toString());
    }
}