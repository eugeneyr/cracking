package info.lynxnet.etudes.trac.states;

import info.lynxnet.etudes.trac.Context;

public class InterpreterState1 extends InterpreterStateBase {
    @Override
    public boolean precondition(Context context) {
        return true;
    }

    @Override
    public Class<? extends InterpreterState> actionAndTransition(Context context) {
        if (context.getActiveString().length() == 0) {
            return InterpreterState0.class;
        }
        return InterpreterState2.class;
    }
}
