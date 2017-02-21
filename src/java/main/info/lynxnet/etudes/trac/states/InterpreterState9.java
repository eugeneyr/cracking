package info.lynxnet.etudes.trac.states;

import info.lynxnet.etudes.trac.Context;

public class InterpreterState9 extends InterpreterStateBase {
    @Override
    public boolean precondition(Context context) {
        return context.getActiveString().length() > 0;
    }

    @Override
    public Class<? extends InterpreterState> actionAndTransition(Context context) {
        if (precondition(context)) {
            // a # that does not start a function
            char ch = context.getActiveString().charAt(0);
            context.getActiveString().deleteCharAt(0);
            context.getNeutralString().append(ch);
            return InterpreterState1.class;
        } else {
            return InterpreterState0.class;
        }
    }
}
