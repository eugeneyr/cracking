package info.lynxnet.etudes.trac;

public class State0 extends StateBase {
    public State0(StateMachine stateMachine) {
        super(stateMachine);
    }

    @Override
    public boolean precondition() {
        return true;
    }

    @Override
    public Class<? extends State> actionAndTransition() {
        this.stateMachine.getNeutralString().setLength(0);
        this.stateMachine.getActiveString().setLength(0);
        this.stateMachine.setScanPointer(0);
        this.stateMachine.getActiveString().append(Constants.INITIAL_ACTIVE_STRING);
        return State1.class;
    }
}
