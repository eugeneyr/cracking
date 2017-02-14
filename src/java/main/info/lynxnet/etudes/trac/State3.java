package info.lynxnet.etudes.trac;

public class State3 extends StateBase {
    public State3(StateMachine stateMachine) {
        super(stateMachine);
    }

    @Override
    public boolean precondition() {
        return this.stateMachine.getActiveString().length() > 0;
    }

    @Override
    public Class<? extends State> actionAndTransition() {
        char ch = this.stateMachine.getActiveString().charAt(0);
        if (ch == '(') {
            // find the closing parenthesis, move what's between them to the neutral string and go back to State1
            int parenCount = 1;
            int pointer = 1;
            while (pointer < this.stateMachine.getActiveString().length()) {
                ch = this.stateMachine.getActiveString().charAt(pointer);
                switch (ch) {
                    case '(':
                        parenCount++;
                        break;
                    case ')':
                        parenCount--;
                        if (parenCount < 0) {
                            // abort - unbalanced parentheses
                            return State0.class;
                        } else if (parenCount == 0) {
                            // found the matching parenthesis
                            this.stateMachine.getNeutralString().append(
                                    this.stateMachine.getActiveString().substring(1, pointer));
                            this.getStateMachine().getActiveString().delete(0, pointer + 1);
                        }
                        break;
                }
                pointer++;
            }
            // if we are here, the closing parenthesis search fell off the active string. Reset everything.
            return State0.class;
        }
        return State4.class;
    }
}
