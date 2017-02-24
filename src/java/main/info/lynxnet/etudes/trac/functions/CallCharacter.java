package info.lynxnet.etudes.trac.functions;

import info.lynxnet.etudes.trac.Context;
import info.lynxnet.etudes.trac.Form;
import info.lynxnet.etudes.trac.Lexem;
import info.lynxnet.etudes.trac.StackElement;

public class CallCharacter implements BuiltInFunction {
    public static final String FUNCTION_MNEMONICS = "cc";
    public static final String FUNCTION_NAME = "Call Character";

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
        StringBuilder result = new StringBuilder();
        if (stackElement.getArguments().size() > 2) {
            Lexem nameArg = stackElement.getArguments().get(1);

            Form form = context.getFormStorage().get(nameArg.getValue());
            if (form != null) {
                if (form.getPointer() == form.getBody().length()) {
                    return new ExecutionResult(true, stackElement.getArgumentValue(2));
                }
                result.append(form.getBody().charAt(form.getPointer()));
                form.setPointer(form.getPointer() + 1);
            }
        }
        return new ExecutionResult(stackElement.isActive(), result.toString());
    }
}