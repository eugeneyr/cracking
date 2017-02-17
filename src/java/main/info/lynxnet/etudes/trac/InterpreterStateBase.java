package info.lynxnet.etudes.trac;

public abstract class InterpreterStateBase implements InterpreterState {
    protected StateMachine stateMachine;

    public InterpreterStateBase(StateMachine stateMachine) {
        this.stateMachine = stateMachine;
    }

    public StateMachine getStateMachine() {
        return stateMachine;
    }

    public void setStateMachine(StateMachine stateMachine) {
        this.stateMachine = stateMachine;
    }

    @Override
    public boolean isInitial() {
        return false;
    }
}
