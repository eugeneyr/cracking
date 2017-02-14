package info.lynxnet.etudes.trac;

public class State7 extends StateBase {
    public State7(StateMachine stateMachine) {
        super(stateMachine);
    }

    @Override
    public boolean precondition() {
        return this.stateMachine.getActiveString().length() > 0
                && this.stateMachine.getActiveString().charAt(0) == '#'
                && this.stateMachine.getActiveString().indexOf(Constants.ACTIVE_FUNCTION_MARKER) != 0
                && this.stateMachine.getActiveString().indexOf(Constants.NEUTRAL_FUNCTION_MARKER) != 0;
    }

    @Override
    public Class<? extends State> actionAndTransition() {
        if (precondition()) {
            // a # that does not start a function
            this.stateMachine.getActiveString().deleteCharAt(0);
            this.stateMachine.getNeutralString().append('#');
            return State1.class;
        } else {
            return State8.class;
        }
    }
}
