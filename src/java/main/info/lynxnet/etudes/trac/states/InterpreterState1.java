package info.lynxnet.etudes.trac.states;

import info.lynxnet.etudes.trac.StateMachine;

public class InterpreterState1 extends InterpreterStateBase {
    public InterpreterState1(StateMachine stateMachine) {
        super(stateMachine);
    }

    @Override
    public boolean precondition() {
        return true;
    }

    @Override
    public Class<? extends InterpreterState> actionAndTransition() {
        if (this.stateMachine.getActiveString().length() == 0) {
            return InterpreterState0.class;
        }
        return InterpreterState2.class;
    }
}
