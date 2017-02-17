package info.lynxnet.etudes.trac;

public class InterpreterState7 extends InterpreterStateBase {
    public InterpreterState7(StateMachine stateMachine) {
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
    public Class<? extends InterpreterState> actionAndTransition() {
        if (precondition()) {
            // a # that does not start a function
            this.stateMachine.getActiveString().deleteCharAt(0);
            this.stateMachine.getNeutralString().append('#');
            return InterpreterState1.class;
        } else {
            return InterpreterState8.class;
        }
    }
}
