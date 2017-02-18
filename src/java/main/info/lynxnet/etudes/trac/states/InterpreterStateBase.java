package info.lynxnet.etudes.trac.states;

import info.lynxnet.etudes.trac.StateMachine;

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
