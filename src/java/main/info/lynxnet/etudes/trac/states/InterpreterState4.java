package info.lynxnet.etudes.trac.states;

import info.lynxnet.etudes.trac.Context;
import info.lynxnet.etudes.trac.Lexem;
import info.lynxnet.etudes.trac.StackElement;

public class InterpreterState4 extends InterpreterStateBase {
    @Override
    public boolean precondition(Context context) {
        return context.getActiveString().length() > 0;
    }

    @Override
    public Class<? extends InterpreterState> actionAndTransition(Context context) {
        char ch = context.getActiveString().charAt(0);
        if (ch == ',') {
            // end of a function argument and the beginning of the next one
            if (!context.getCallStack().empty()) {
                StackElement current = context.getCallStack().peek();
                if (current.getArguments().isEmpty()) {
                    current.getArguments().add(new Lexem(current.getOffset(), ""));
                }

                Lexem prevLexem = current.getArguments().get(current.getArguments().size() - 1);
                if (!prevLexem.isCompleted()) {
                    prevLexem.setValue(context.getNeutralString().substring(prevLexem.getOffset()));
                    prevLexem.setCompleted(true);
                }
                current.completeArgument(context);
                current.getArguments().add(new Lexem(context.getNeutralString().length()));
            }
            context.getActiveString().deleteCharAt(0);
            return InterpreterState1.class;
        } else {
            return InterpreterState5.class;
        }
    }
}
