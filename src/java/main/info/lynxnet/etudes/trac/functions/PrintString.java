package info.lynxnet.etudes.trac.functions;

import info.lynxnet.etudes.trac.Form;
import info.lynxnet.etudes.trac.Lexem;
import info.lynxnet.etudes.trac.StackElement;
import info.lynxnet.etudes.trac.Constants;
import java.util.Map;

public class PrintString implements BuiltInFunction {
    public static final String FUNCTION_NAME = "ps";
    @Override
    public String getName() {
        return FUNCTION_NAME;
    }

    @Override
    public String execute(StackElement stackElement, Map<String, Form> formStorage) {
        for (Lexem lexem : stackElement.getArguments().subList(1, stackElement.getArguments().size())) {
            if (Constants.LINE_BREAK.equals(lexem.getValue())) {
                System.out.println();
            } else {
                System.out.print(lexem.getValue());
            }
        }
        return "";
    }
}