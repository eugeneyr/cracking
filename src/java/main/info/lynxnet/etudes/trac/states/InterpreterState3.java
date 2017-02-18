package info.lynxnet.etudes.trac.states;

import info.lynxnet.etudes.trac.Constants;
import info.lynxnet.etudes.trac.StateMachine;

public class InterpreterState3 extends InterpreterStateBase {
    public InterpreterState3(StateMachine stateMachine) {
        super(stateMachine);
    }

    @Override
    public boolean precondition() {
        return this.stateMachine.getActiveString().length() > 0;
    }

    @Override
    public Class<? extends InterpreterState> actionAndTransition() {
        char ch = this.stateMachine.getActiveString().charAt(0);
        if (ch == Constants.OPENING_BRACKET) {
            // find the closing parenthesis, move what's between them to the neutral string and go back to State1
            int parenCount = 1;
            int pointer = 1;
            while (pointer < this.stateMachine.getActiveString().length()) {
                ch = this.stateMachine.getActiveString().charAt(pointer);
                switch (ch) {
                    case Constants.OPENING_BRACKET:
                        parenCount++;
                        break;
                    case Constants.CLOSING_BRACKET:
                        parenCount--;
                        if (parenCount < 0) {
                            // abort - unbalanced parentheses
                            return InterpreterState0.class;
                        } else if (parenCount == 0) {
                            // found the matching parenthesis
                            this.stateMachine.getNeutralString().append(
                                    this.stateMachine.getActiveString().substring(1, pointer));
                            this.getStateMachine().getActiveString().delete(0, pointer + 1);
                            return InterpreterState1.class;
                        }
                        break;
                }
                pointer++;
            }
            // if we are here, the closing parenthesis search fell off the active string. Reset everything.
            return InterpreterState0.class;
        }
        return InterpreterState4.class;
    }
}
