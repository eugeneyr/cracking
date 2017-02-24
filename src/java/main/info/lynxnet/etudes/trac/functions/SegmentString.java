package info.lynxnet.etudes.trac.functions;

import info.lynxnet.etudes.trac.*;

public class SegmentString implements BuiltInFunction {
    public static final String FUNCTION_MNEMONICS = "ss";
    public static final String FUNCTION_NAME = "Segment String";

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
        if (stackElement.getArguments().size() > 2) {
            Lexem nameArg = stackElement.getArguments().get(1);

            Form form = context.getFormStorage().get(nameArg.getValue());
            if (form != null) {
                int ordinal = 1;
                for (Lexem arg : stackElement.getArguments().subList(2, stackElement.getArguments().size())) {
                    String val = arg.getValue();
                    form.setPointer(0);
                    if (val != null && val.length() > 0) {
                        while (form.getPointer() < form.getBody().length() && form.getBody().substring(form.getPointer()).contains(val)) {
                            int pos = form.getPointer() + form.getBody().substring(form.getPointer()).indexOf(val);
                            if (!form.hasMarkers(pos, val.length())) {
                                form.adjustOffsets(pos + val.length(), -val.length());
                                FormMarker marker = new FormMarker(ordinal, pos);
                                form.getMarkers().add(marker);
                                form.getBody().delete(pos, pos + val.length());
                                form.setPointer(form.getPointer() + pos);
                            } else {
                                int pointerVal = form.getLastMarkerOffsetInRange(pos, val.length());
                                form.setPointer(pointerVal);
                            }
                        }
                    }
                    ordinal++;
                }
                form.setPointer(0);
            }
        }
        return new ExecutionResult(stackElement.isActive(), "");
    }
}