package info.lynxnet.etudes.trac;

public abstract class StateBase implements State {
    protected StateMachine stateMachine;

    public StateBase(StateMachine stateMachine) {
        this.stateMachine = stateMachine;
    }

    public StateMachine getStateMachine() {
        return stateMachine;
    }

    public void setStateMachine(StateMachine stateMachine) {
        this.stateMachine = stateMachine;
    }
}
