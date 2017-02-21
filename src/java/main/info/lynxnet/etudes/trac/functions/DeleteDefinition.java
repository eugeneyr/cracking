package info.lynxnet.etudes.trac.functions;

import info.lynxnet.etudes.trac.Context;
import info.lynxnet.etudes.trac.StackElement;

public class DeleteDefinition implements BuiltInFunction {
    public static final String FUNCTION_NAME = "dd";

    @Override
    public String getName() {
        return FUNCTION_NAME;
    }

    @Override
    public ExecutionResult execute(StackElement stackElement, Context context) {
        stackElement.getArguments().subList(1, stackElement.getArguments().size()).stream().forEach(
                x -> {
                    if (context.getFormStorage().containsKey(x.getValue())) {
                        context.getFormStorage().remove(x.getValue());
                    }
                }
        );
        return new ExecutionResult(stackElement.isActive(), "");
    }
}