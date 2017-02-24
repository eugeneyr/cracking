package info.lynxnet.etudes.trac.functions;

import info.lynxnet.etudes.trac.Context;
import info.lynxnet.etudes.trac.Form;
import info.lynxnet.etudes.trac.Lexem;
import info.lynxnet.etudes.trac.StackElement;

public class DefineString implements BuiltInFunction {
    public static final String FUNCTION_MNEMONICS = "ds";
    public static final String FUNCTION_NAME = "Define String";

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
            Lexem bodyArg = stackElement.getArguments().get(2);
            Form form = new Form(nameArg.getValue(), new StringBuilder(bodyArg.getValue()));
            context.getFormStorage().put(form.getName(), form);
        }
        return new ExecutionResult(stackElement.isActive(), "");
    }
}