package info.lynxnet.etudes.trac.states;

import info.lynxnet.etudes.trac.FunctionEvaluator;
import info.lynxnet.etudes.trac.StackElement;
import info.lynxnet.etudes.trac.StateMachine;
import info.lynxnet.etudes.trac.functions.ExecutionResult;

public class InterpreterState8 extends InterpreterStateBase {
    public InterpreterState8(StateMachine stateMachine) {
        super(stateMachine);
    }

    @Override
    public boolean precondition() {
        return this.stateMachine.getActiveString().length() > 0
                && this.stateMachine.getActiveString().charAt(0) == ')';
    }

    @Override
    public Class<? extends InterpreterState> actionAndTransition() {
        if (precondition()) {
            // a closing parenthesis
            if (this.stateMachine.getCallStack().empty()) {
                return InterpreterState0.class;
            }
            this.stateMachine.getActiveString().deleteCharAt(0);
            StackElement current = this.stateMachine.getCallStack().pop();
            current.completeArgument(stateMachine);
            this.stateMachine.getNeutralString().delete(
                    current.getOffset(), this.stateMachine.getNeutralString().length());
            ExecutionResult result = FunctionEvaluator.evaluate(current);
            if (result.isActive()) {
                this.stateMachine.getActiveString().insert(0, result.getValue());
            } else {
                this.stateMachine.getNeutralString().append(result.getValue());
            }

            return InterpreterState1.class;
        } else {
            return InterpreterState9.class;
        }
    }
}
