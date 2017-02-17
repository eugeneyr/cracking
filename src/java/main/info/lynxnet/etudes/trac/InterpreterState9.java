package info.lynxnet.etudes.trac;

public class InterpreterState9 extends InterpreterStateBase {
    public InterpreterState9(StateMachine stateMachine) {
        super(stateMachine);
    }

    @Override
    public boolean precondition() {
        return this.stateMachine.getActiveString().length() > 0;
    }

    @Override
    public Class<? extends InterpreterState> actionAndTransition() {
        if (precondition()) {
            // a # that does not start a function
            char ch = this.stateMachine.getActiveString().charAt(0);
            this.stateMachine.getActiveString().deleteCharAt(0);
            this.stateMachine.getNeutralString().append(ch);
            return InterpreterState1.class;
        } else {
            return InterpreterState0.class;
        }
    }
}
