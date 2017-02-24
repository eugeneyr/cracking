package info.lynxnet.etudes.trac.functions;

import info.lynxnet.etudes.trac.*;

import java.util.List;

public class PrintForm implements BuiltInFunction {
    public static final String FUNCTION_NAME = "pf";

    @Override
    public String getName() {
        return FUNCTION_NAME;
    }

    @Override
    public ExecutionResult execute(StackElement stackElement, Context context) {
        if (stackElement.getArguments().size() > 1) {
            Lexem nameArg = stackElement.getArguments().get(1);

            Form form = context.getFormStorage().get(nameArg.getValue());
            if (form != null) {
                List<FormElement> segmented = form.segment();
                if (form.getPointer() == 0) {
                    context.getOutput().print("<^>");
                }
                for (FormElement element: segmented) {
                    if (element instanceof FormSegment) {
                        if (form.getPointer() > element.getOffset()
                                && form.getPointer() <= element.getOffset() + ((FormSegment) element).getValue().length()) {
                            int relPos = form.getPointer() - element.getOffset();
                            context.getOutput().print(((FormSegment)element).getValue().substring(0, relPos));
                            context.getOutput().print("<^>");
                            context.getOutput().print(((FormSegment)element).getValue().substring(relPos));
                        } else {
                            context.getOutput().print(((FormSegment) element).getValue());
                        }
                    } else if (element instanceof FormMarker) {
                        context.getOutput().print(String.format("<%d>", ((FormMarker) element).getOrdinal()));
                    }
                }
            }
            context.getOutput().println();
        }
        return new ExecutionResult(stackElement.isActive(), "");
    }
}