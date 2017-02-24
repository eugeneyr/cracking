package info.lynxnet.etudes.trac.functions;

import info.lynxnet.etudes.trac.Context;
import info.lynxnet.etudes.trac.StackElement;

public class QueryMetacharacter implements BuiltInFunction {
    public static final String FUNCTION_MNEMONICS = "qm";
    public static final String FUNCTION_NAME = "Query Metacharacter";

    @Override
    public String getMnemonics() {
        return FUNCTION_MNEMONICS;
    }

    @Override
    public String getCategory() {
        return FunctionCategory.STRING;
    }

    @Override
    public String getName() {
        return FUNCTION_NAME;
    }

    @Override
    public ExecutionResult execute(StackElement stackElement, Context context) {
        return new ExecutionResult(
                stackElement.isActive(),
                Character.toString(context.getMetacharacter()));
    }
}