package info.lynxnet.etudes.trac;

public class State8 extends StateBase {
    public State8(StateMachine stateMachine) {
        super(stateMachine);
    }

    @Override
    public boolean precondition() {
        return this.stateMachine.getActiveString().length() > 0
                && this.stateMachine.getActiveString().charAt(0) == ')';
    }

    @Override
    public Class<? extends State> actionAndTransition() {
        if (precondition()) {
            // a closing parenthesis
            if (this.stateMachine.getCallStack().empty()) {
                return State0.class;
            }
            this.stateMachine.getActiveString().deleteCharAt(0);
            StackElement current = this.stateMachine.getCallStack().pop();
            this.stateMachine.getNeutralString().delete(
                    current.getOffset(), this.stateMachine.getNeutralString().length());
            String result = FunctionEvaluator.evaluate(current);
            if (current.isActive()) {
                this.stateMachine.getActiveString().insert(0, result);
            } else {
                this.stateMachine.getNeutralString().append(result);
            }




            return State1.class;
        } else {
            return State9.class;
        }
    }
}
