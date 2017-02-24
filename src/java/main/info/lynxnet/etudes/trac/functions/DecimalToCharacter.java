package info.lynxnet.etudes.trac.functions;

import info.lynxnet.etudes.trac.Context;
import info.lynxnet.etudes.trac.StackElement;

public class DecimalToCharacter implements BuiltInFunction {
    public static final String FUNCTION_MNEMONICS = "dc";
    public static final String FUNCTION_NAME = "Decimal to Character";

    @Override
    public String getMnemonics() {
        return FUNCTION_MNEMONICS;
    }

    @Override
    public String getCategory() {
        return FunctionCategory.STRING;
    }

    @Override
    public String getName() {
        return FUNCTION_NAME;
    }

    @Override
    public ExecutionResult execute(StackElement stackElement, Context context) {
        String string = stackElement.getArgumentValue(1);
        StringBuilder sb = new StringBuilder();
        if (string.length() > 0) {
            try {
                int codePoint = Integer.parseInt(string);
                sb.append(Character.toChars(codePoint));
            } catch (NumberFormatException e) {
                e.printStackTrace(System.err);
            }
        }
        return new ExecutionResult(stackElement.isActive(), sb.toString());
    }
}