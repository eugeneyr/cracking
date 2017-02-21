package info.lynxnet.etudes.trac.states;

public abstract class InterpreterStateBase implements InterpreterState {
    @Override
    public boolean isInitial() {
        return false;
    }
}
