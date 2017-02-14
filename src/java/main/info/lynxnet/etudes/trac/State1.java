package info.lynxnet.etudes.trac;

public class State1 extends StateBase {
    public State1(StateMachine stateMachine) {
        super(stateMachine);
    }

    @Override
    public boolean precondition() {
        return true;
    }

    @Override
    public Class<? extends State> actionAndTransition() {
        if (this.stateMachine.getActiveString().length() == 0) {
            return State0.class;
        }
        return State2.class;
    }
}
