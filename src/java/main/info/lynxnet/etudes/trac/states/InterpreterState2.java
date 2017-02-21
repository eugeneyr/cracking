package info.lynxnet.etudes.trac.states;

import info.lynxnet.etudes.trac.Context;

public class InterpreterState2 extends InterpreterStateBase {
    @Override
    public boolean precondition(Context context) {
        return context.getActiveString().length() > 0
                && Character.isSpaceChar(context.getActiveString().charAt(0))
                && context.getActiveString().charAt(0) != ' ';
    }

    @Override
    public Class<? extends InterpreterState> actionAndTransition(Context context) {
        char ch = context.getActiveString().charAt(0);
        if (Character.isSpaceChar(ch) && ch != ' ') {
            context.getActiveString().deleteCharAt(0);
            return InterpreterState1.class;
        }
        return InterpreterState3.class;
    }
}
