package info.lynxnet.etudes.trac;

public class State6 extends StateBase {
    public State6(StateMachine stateMachine) {
        super(stateMachine);
    }

    @Override
    public boolean precondition() {
        return this.stateMachine.getActiveString().length() > 0;
    }

    @Override
    public Class<? extends State> actionAndTransition() {
        if (this.stateMachine.getActiveString().indexOf(Constants.NEUTRAL_FUNCTION_MARKER) == 0) {
            // beginning of a neutral function
            StackElement current = new StackElement(false, this.stateMachine.getNeutralString().length());
            this.stateMachine.setCurrentStackElement(current);
            this.stateMachine.getCallStack().push(current);
            this.stateMachine.getActiveString().delete(0, Constants.NEUTRAL_FUNCTION_MARKER.length());
            return State1.class;
        } else {
            return State7.class;
        }
    }
}
