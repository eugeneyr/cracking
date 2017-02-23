package info.lynxnet.etudes.trac.states;

import info.lynxnet.etudes.trac.Context;
import info.lynxnet.etudes.trac.FunctionEvaluator;
import info.lynxnet.etudes.trac.StackElement;
import info.lynxnet.etudes.trac.functions.ExecutionResult;

import java.io.IOException;

public class InterpreterState8 extends InterpreterStateBase {
    @Override
    public boolean precondition(Context context) {
        return context.getActiveString().length() > 0
                && context.getActiveString().charAt(0) == ')';
    }

    @Override
    public Class<? extends InterpreterState> actionAndTransition(Context context) {
        if (precondition(context)) {
            // a closing parenthesis
            if (context.getCallStack().empty()) {
                return InterpreterState0.class;
            }
            context.getActiveString().deleteCharAt(0);
            StackElement current = context.getCallStack().pop();
            current.completeArgument(context);
            context.getNeutralString().delete(
                    current.getOffset(), context.getNeutralString().length());
            if (context.isTrace()) {
                System.err.println(current.toString());
                try {
                    int character = System.in.read();
                    if (!Character.isWhitespace(character)) {
                        return InterpreterState0.class;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            ExecutionResult result = FunctionEvaluator.evaluate(current, context);
            if (result.isActive()) {
                context.getActiveString().insert(0, result.getValue());
            } else {
                context.getNeutralString().append(result.getValue());
            }

            return InterpreterState1.class;
        } else {
            return InterpreterState9.class;
        }
    }
}
