package info.lynxnet.etudes.trac.functions;

import info.lynxnet.etudes.trac.Form;
import info.lynxnet.etudes.trac.FormMarker;
import info.lynxnet.etudes.trac.Lexem;
import info.lynxnet.etudes.trac.StackElement;

import java.util.Map;

public class SegmentString implements BuiltInFunction {
    public static final String FUNCTION_NAME = "ss";

    @Override
    public String getName() {
        return FUNCTION_NAME;
    }

    @Override
    public String execute(StackElement stackElement, Map<String, Form> formStorage) {
        if (stackElement.getArguments().size() > 2) {
            Lexem nameArg = stackElement.getArguments().get(1);

            Form form = formStorage.get(nameArg.getValue());
            if (form != null) {
                int ordinal = 1;
                for (Lexem arg : stackElement.getArguments().subList(2, stackElement.getArguments().size())) {
                    String val = arg.getValue();
                    form.setPointer(0);
                    if (val != null && val.length() > 0) {
                        while (form.getBody().substring(form.getPointer()).contains(val)) {
                            int pos = form.getPointer() + form.getBody().substring(form.getPointer()).indexOf(val);
                            if (!form.hasMarkers(pos, val.length())) {
                                form.adjustOffsets(pos + val.length(), -val.length());
                                FormMarker marker = new FormMarker(ordinal, pos);
                                form.getElements().add(marker);
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
            }
        }
        return "";
    }
}