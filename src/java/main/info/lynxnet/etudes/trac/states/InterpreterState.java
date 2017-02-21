package info.lynxnet.etudes.trac.states;

import info.lynxnet.etudes.trac.Context;

public interface InterpreterState {
    boolean precondition(Context context);
    boolean isInitial();
    Class<? extends InterpreterState> actionAndTransition(Context context);
}
