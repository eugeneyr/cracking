package info.lynxnet.etudes.trac;

import java.util.Stack;

public class StateMachine {

    private Stack<StackElement> callStack;

    private StringBuilder neutralString = new StringBuilder();
    private StringBuilder activeString = new StringBuilder();
}
