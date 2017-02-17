package info.lynxnet.etudes.trac;

public class InterpreterState6 extends InterpreterStateBase {
    public InterpreterState6(StateMachine stateMachine) {
        super(stateMachine);
    }

    @Override
    public boolean precondition() {
        return this.stateMachine.getActiveString().length() > 0;
    }

    @Override
    public Class<? extends InterpreterState> actionAndTransition() {
        if (this.stateMachine.getActiveString().indexOf(Constants.NEUTRAL_FUNCTION_MARKER) == 0) {
            // beginning of a neutral function
            StackElement current = new StackElement(false, this.stateMachine.getNeutralString().length());
            this.stateMachine.setCurrentStackElement(current);
            this.stateMachine.getCallStack().push(current);
            this.stateMachine.getActiveString().delete(0, Constants.NEUTRAL_FUNCTION_MARKER.length());
            return InterpreterState1.class;
        } else {
            return InterpreterState7.class;
        }
    }
}
