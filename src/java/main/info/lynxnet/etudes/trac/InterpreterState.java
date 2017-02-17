package info.lynxnet.etudes.trac;

interface InterpreterState {
    boolean precondition();
    boolean isInitial();
    Class<? extends InterpreterState> actionAndTransition();
}
