package info.lynxnet.etudes.trac.functions;

import info.lynxnet.etudes.trac.Configuration;
import info.lynxnet.etudes.trac.Form;
import info.lynxnet.etudes.trac.Lexem;
import info.lynxnet.etudes.trac.StackElement;

import java.util.Map;

public class DefineString implements BuiltInFunction {
    public static final String FUNCTION_NAME = "ds";
    @Override
    public String getName() {
        return FUNCTION_NAME;
    }

    @Override
    public String execute(StackElement stackElement, Map<String, Form> formStorage) {
        if (stackElement.getArguments().size() > 2) {
            Lexem nameArg = stackElement.getArguments().get(1);
            Lexem bodyArg = stackElement.getArguments().get(2);
            Form form = new Form(nameArg.getValue(), new StringBuilder(bodyArg.getValue()));
            formStorage.put(form.getName(), form);
        }
        return "";
    }
}