package info.lynxnet.etudes.trac.functions;

import info.lynxnet.etudes.trac.Context;
import info.lynxnet.etudes.trac.StackElement;

public interface BuiltInFunction {
    String getMnemonics();
    String getCategory();
    String getName();
    ExecutionResult execute(StackElement stackElement, Context context);
}
