package info.lynxnet.etudes.trac.functions;

import info.lynxnet.etudes.trac.*;

public class CallSegment implements BuiltInFunction {
    public static final String FUNCTION_MNEMONICS = "cs";
    public static final String FUNCTION_NAME = "Call Segment";

    @Override
    public String getMnemonics() {
        return FUNCTION_MNEMONICS;
    }

    @Override
    public String getCategory() {
        return FunctionCategory.FORMS;
    }

    @Override
    public String getName() {
        return FUNCTION_NAME;
    }

    @Override
    public ExecutionResult execute(StackElement stackElement, Context context) {
        StringBuilder result = new StringBuilder();
        if (stackElement.getArguments().size() > 2) {
            Lexem nameArg = stackElement.getArguments().get(1);

            Form form = context.getFormStorage().get(nameArg.getValue());
            if (form != null) {
                if (form.getPointer() == form.getBody().length()) {
                    return new ExecutionResult(true, stackElement.getArgumentValue(2));
                }
                int markerOffset = form.getClosestMarkerOffset(form.getPointer());
                result.append(form.getBody().substring(form.getPointer(), markerOffset));
                form.setPointer(markerOffset);
            }
        }
        return new ExecutionResult(stackElement.isActive(), result.toString());
    }
}