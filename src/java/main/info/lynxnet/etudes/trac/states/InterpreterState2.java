package info.lynxnet.etudes.trac.states;

import info.lynxnet.etudes.trac.StateMachine;

public class InterpreterState2 extends InterpreterStateBase {
    public InterpreterState2(StateMachine stateMachine) {
        super(stateMachine);
    }

    @Override
    public boolean precondition() {
        return this.stateMachine.getActiveString().length() > 0
                && Character.isSpaceChar(this.stateMachine.getActiveString().charAt(0))
                && this.stateMachine.getActiveString().charAt(0) != ' ';
    }

    @Override
    public Class<? extends InterpreterState> actionAndTransition() {
        char ch = this.stateMachine.getActiveString().charAt(0);
        if (Character.isSpaceChar(ch) && ch != ' ') {
            this.stateMachine.getActiveString().deleteCharAt(0);
            return InterpreterState1.class;
        }
        return InterpreterState3.class;
    }
}
