package info.lynxnet.etudes.trac.states;

import info.lynxnet.etudes.trac.Constants;
import info.lynxnet.etudes.trac.Context;
import info.lynxnet.etudes.trac.StackElement;

public class InterpreterState5 extends InterpreterStateBase {
    @Override
    public boolean precondition(Context context) {
        return context.getActiveString().length() > 0;
    }

    @Override
    public Class<? extends InterpreterState> actionAndTransition(Context context) {
        if (context.getActiveString().indexOf(Constants.ACTIVE_FUNCTION_MARKER) == 0) {
            // beginning of an active function
            StackElement current = new StackElement(true, context.getNeutralString().length());
            context.getCallStack().push(current);
            context.getActiveString().delete(0, Constants.ACTIVE_FUNCTION_MARKER.length());
            return InterpreterState1.class;
        } else {
            return InterpreterState6.class;
        }
    }
}
