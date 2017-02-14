package info.lynxnet.etudes.trac;

public class State2 extends StateBase {
    public State2(StateMachine stateMachine) {
        super(stateMachine);
    }

    @Override
    public boolean precondition() {
        return this.stateMachine.getActiveString().length() > 0
                && Character.isSpaceChar(this.stateMachine.getActiveString().charAt(0))
                && this.stateMachine.getActiveString().charAt(0) != ' ';
    }

    @Override
    public Class<? extends State> actionAndTransition() {
        char ch = this.stateMachine.getActiveString().charAt(0);
        if (Character.isSpaceChar(ch) && ch != ' ') {
            this.stateMachine.getActiveString().deleteCharAt(0);
            return State1.class;
        }
        return State3.class;
    }
}
