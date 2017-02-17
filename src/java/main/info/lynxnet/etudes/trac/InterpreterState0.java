package info.lynxnet.etudes.trac;

public class InterpreterState0 extends InterpreterStateBase {
    public InterpreterState0(StateMachine stateMachine) {
        super(stateMachine);
    }

    @Override
    public boolean precondition() {
        return true;
    }

    @Override
    public boolean isInitial() {
        return true;
    }

    @Override
    public Class<? extends InterpreterState> actionAndTransition() {
        this.stateMachine.getNeutralString().setLength(0);
        this.stateMachine.getActiveString().setLength(0);
        this.stateMachine.setScanPointer(0);
        this.stateMachine.getActiveString().append(Constants.INITIAL_ACTIVE_STRING);
        return InterpreterState1.class;
    }
}
