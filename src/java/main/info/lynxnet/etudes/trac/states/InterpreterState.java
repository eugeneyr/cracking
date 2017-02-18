package info.lynxnet.etudes.trac.states;

public interface InterpreterState {
    boolean precondition();
    boolean isInitial();
    Class<? extends InterpreterState> actionAndTransition();
}
