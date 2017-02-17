package info.lynxnet.etudes.trac;

public class InterpreterState5 extends InterpreterStateBase {
    public InterpreterState5(StateMachine stateMachine) {
        super(stateMachine);
    }

    @Override
    public boolean precondition() {
        return this.stateMachine.getActiveString().length() > 0;
    }

    @Override
    public Class<? extends InterpreterState> actionAndTransition() {
        if (this.stateMachine.getActiveString().indexOf(Constants.ACTIVE_FUNCTION_MARKER) == 0) {
            // beginning of an active function
            StackElement current = new StackElement(true, this.stateMachine.getNeutralString().length());
            this.stateMachine.setCurrentStackElement(current);
            this.stateMachine.getCallStack().push(current);
            this.stateMachine.getActiveString().delete(0, Constants.ACTIVE_FUNCTION_MARKER.length());
            return InterpreterState1.class;
        } else {
            return InterpreterState6.class;
        }
    }
}
