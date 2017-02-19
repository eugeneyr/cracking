package info.lynxnet.etudes.trac.functions;

import info.lynxnet.etudes.trac.Form;
import info.lynxnet.etudes.trac.Lexem;
import info.lynxnet.etudes.trac.StackElement;

import java.util.Map;

public class Halt implements BuiltInFunction {
    public static final String FUNCTION_NAME = "hl";
    @Override
    public String getName() {
        return FUNCTION_NAME;
    }

    @Override
    public ExecutionResult execute(StackElement stackElement, Map<String, Form> formStorage) {
        System.exit(0);
        return new ExecutionResult(stackElement.isActive(), "");
    }
}