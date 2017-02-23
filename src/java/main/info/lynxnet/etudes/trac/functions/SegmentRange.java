package info.lynxnet.etudes.trac.functions;

import info.lynxnet.etudes.trac.*;

import java.util.OptionalInt;

public class SegmentRange implements BuiltInFunction {
    public static final String FUNCTION_NAME = "sr";
    @Override
    public String getName() {
        return FUNCTION_NAME;
    }

    @Override
    public ExecutionResult execute(StackElement stackElement, Context context) {
        int range = 0;
        if (stackElement.getArguments().size() > 1) {
            Lexem nameArg = stackElement.getArguments().get(1);

            Form form = context.getFormStorage().get(nameArg.getValue());
            if (form != null) {
                OptionalInt max = form.getMarkers().stream().mapToInt(FormMarker::getOrdinal).max();
                if (max.isPresent()) {
                    range = max.getAsInt();
                }
            }
        }
        return new ExecutionResult(stackElement.isActive(), Integer.toString(range));
    }
}