package info.lynxnet.etudes.trac.functions;

import info.lynxnet.etudes.trac.*;

import java.util.List;
import java.util.Map;

public class PrintForm implements BuiltInFunction {
    public static final String FUNCTION_NAME = "pf";

    @Override
    public String getName() {
        return FUNCTION_NAME;
    }

    @Override
    public ExecutionResult execute(StackElement stackElement, Map<String, Form> formStorage) {
        if (stackElement.getArguments().size() > 1) {
            Lexem nameArg = stackElement.getArguments().get(1);

            Form form = formStorage.get(nameArg.getValue());
            if (form != null) {
                List<FormElement> segmented = form.segment();
                if (form.getPointer() == 0) {
                    System.out.print("<^>");
                }
                for (FormElement element: segmented) {
                    if (element instanceof FormSegment) {
                        if (form.getPointer() > element.getOffset()
                                && form.getPointer() <= element.getOffset() + ((FormSegment) element).getValue().length()) {
                            int relPos = form.getPointer() - element.getOffset();
                            System.out.print(((FormSegment)element).getValue().substring(0, relPos));
                            System.out.print("<^>");
                            System.out.print(((FormSegment)element).getValue().substring(relPos));
                        } else {
                            System.out.print(((FormSegment) element).getValue());
                        }
                    } else if (element instanceof FormMarker) {
                        System.out.print(String.format("<%d>", ((FormMarker) element).getOrdinal()));
                    }
                }
            }
            System.out.println();
        }
        return new ExecutionResult(stackElement.isActive(), "");
    }
}