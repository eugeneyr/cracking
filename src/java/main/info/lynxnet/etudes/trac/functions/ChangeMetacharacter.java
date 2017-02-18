package info.lynxnet.etudes.trac.functions;

import info.lynxnet.etudes.trac.*;

import java.util.Map;

public class ChangeMetacharacter implements BuiltInFunction {
    public static final String FUNCTION_NAME = "cm";
    @Override
    public String getName() {
        return FUNCTION_NAME;
    }

    @Override
    public String execute(StackElement stackElement, Map<String, Form> formStorage) {
        if (stackElement.getArguments().size() > 1) {
            Lexem arg = stackElement.getArguments().get(1);
            if (arg.getValue() != null && arg.getValue().length() > 0) {
                Configuration.getInstance().setMetacharacter(arg.getValue().charAt(0));
            }
        }
        return "";
    }
}