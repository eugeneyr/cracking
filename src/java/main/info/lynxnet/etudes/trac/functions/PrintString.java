package info.lynxnet.etudes.trac.functions;

import info.lynxnet.etudes.trac.*;

public class PrintString implements BuiltInFunction {
    public static final String FUNCTION_NAME = "ps";
    @Override
    public String getName() {
        return FUNCTION_NAME;
    }

    @Override
    public ExecutionResult execute(StackElement stackElement, Context context) {
        for (Lexem lexem : stackElement.getArguments().subList(1, stackElement.getArguments().size())) {
            if (Constants.LINE_BREAK.equals(lexem.getValue())) {
                System.out.println();
            } else {
                System.out.print(lexem.getValue());
            }
        }
        return new ExecutionResult(stackElement.isActive(), "");
    }
}