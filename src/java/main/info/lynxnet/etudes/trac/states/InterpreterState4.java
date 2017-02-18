package info.lynxnet.etudes.trac.states;

import info.lynxnet.etudes.trac.Lexem;
import info.lynxnet.etudes.trac.StackElement;
import info.lynxnet.etudes.trac.StateMachine;

public class InterpreterState4 extends InterpreterStateBase {
    public InterpreterState4(StateMachine stateMachine) {
        super(stateMachine);
    }

    @Override
    public boolean precondition() {
        return this.stateMachine.getActiveString().length() > 0;
    }

    @Override
    public Class<? extends InterpreterState> actionAndTransition() {
        char ch = this.stateMachine.getActiveString().charAt(0);
        if (ch == ',') {
            // end of a function argument and the beginning of the next one
            StackElement current = this.stateMachine.getCurrentStackElement() == null
                    ? new StackElement(true, this.stateMachine.getNeutralString().length())
                    : this.stateMachine.getCurrentStackElement();
            if (current.getArguments().isEmpty()) {
                current.getArguments().add(new Lexem(current.getOffset(), ""));
            }
            if (this.stateMachine.getCurrentStackElement() == null) {
                this.stateMachine.setCurrentStackElement(current);
                this.stateMachine.getCallStack().push(current);
            } else {
                Lexem prevLexem = current.getArguments().get(current.getArguments().size() - 1);
                if (!prevLexem.isCompleted()) {
                    prevLexem.setValue(this.stateMachine.getNeutralString().substring(prevLexem.getOffset()));
                    prevLexem.setCompleted(true);
                }
            }
            current.completeArgument(stateMachine);
            current.getArguments().add(new Lexem(this.stateMachine.getNeutralString().length()));
            this.stateMachine.getActiveString().deleteCharAt(0);
            return InterpreterState1.class;
        } else {
            return InterpreterState5.class;
        }
    }
}
