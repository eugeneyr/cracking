package info.lynxnet.etudes.trac.states;

import info.lynxnet.etudes.trac.Context;

public class InterpreterState0 extends InterpreterStateBase {
    @Override
    public boolean precondition(Context context) {
        return true;
    }

    @Override
    public boolean isInitial() {
        return true;
    }

    @Override
    public Class<? extends InterpreterState> actionAndTransition(Context context) {
        context.getNeutralString().setLength(0);
        context.getActiveString().setLength(0);
        context.getActiveString().append(context.getConfiguration().getInitialActiveString());
        return InterpreterState1.class;
    }
}
