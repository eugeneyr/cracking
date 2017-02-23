package info.lynxnet.etudes.trac.functions;

import info.lynxnet.etudes.trac.Context;
import info.lynxnet.etudes.trac.StackElement;

public class CharacterToDecimal implements BuiltInFunction {
    public static final String FUNCTION_NAME = "cd";

    @Override
    public String getName() {
        return FUNCTION_NAME;
    }

    @Override
    public ExecutionResult execute(StackElement stackElement, Context context) {
        String string = stackElement.getArgumentValue(1);
        StringBuilder sb = new StringBuilder();
        if (string.length() > 0) {
            sb.append(Character.codePointAt(string, 0));
        }
        return new ExecutionResult(stackElement.isActive(), sb.toString());
    }
}