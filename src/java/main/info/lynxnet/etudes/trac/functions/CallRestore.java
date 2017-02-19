package info.lynxnet.etudes.trac.functions;

import info.lynxnet.etudes.trac.Form;
import info.lynxnet.etudes.trac.Lexem;
import info.lynxnet.etudes.trac.StackElement;

import java.util.Map;

public class CallRestore implements BuiltInFunction {
    public static final String FUNCTION_NAME = "cr";

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
                form.setPointer(0);
            }
        }
        return new ExecutionResult(stackElement.isActive(), "");
    }
}