package info.lynxnet.etudes.trac.functions;

import info.lynxnet.etudes.trac.*;

import java.util.List;
import java.util.Map;

public class CallString implements BuiltInFunction {
    public static final String FUNCTION_NAME = "cl";

    @Override
    public String getName() {
        return FUNCTION_NAME;
    }

    @Override
    public ExecutionResult execute(StackElement stackElement, Map<String, Form> formStorage) {
        StringBuilder result = new StringBuilder();
        if (stackElement.getArguments().size() > 2) {
            Lexem nameArg = stackElement.getArguments().get(1);

            Form form = formStorage.get(nameArg.getValue());
            if (form != null) {
                List<FormElement> segmented = form.segment();

                for (FormElement element: segmented) {
                    if (element instanceof FormSegment) {
                        result.append(((FormSegment) element).getValue());
                    } else if (element instanceof FormMarker) {
                        result.append(stackElement.getArgumentValue(((FormMarker) element).getOrdinal() + 1));
                    }
                }
            }
        }
        return new ExecutionResult(stackElement.isActive(), result.toString());
    }
}