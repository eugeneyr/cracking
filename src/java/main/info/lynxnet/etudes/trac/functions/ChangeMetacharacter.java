package info.lynxnet.etudes.trac.functions;

import info.lynxnet.etudes.trac.*;

public class ChangeMetacharacter implements BuiltInFunction {
    public static final String FUNCTION_NAME = "cm";
    @Override
    public String getName() {
        return FUNCTION_NAME;
    }

    @Override
    public ExecutionResult execute(StackElement stackElement, Context context) {
        if (stackElement.getArguments().size() > 1) {
            Lexem arg = stackElement.getArguments().get(1);
            if (arg.getValue() != null && arg.getValue().length() > 0) {
                context.setMetacharacter(arg.getValue().charAt(0));
            }
        }
        return new ExecutionResult(stackElement.isActive(), "");
    }
}