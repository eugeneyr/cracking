package info.lynxnet.etudes.trac;

import java.util.*;

public class StateMachine {
    private Stack<StackElement> callStack = new Stack<>();
    private StackElement currentStackElement;

    private StringBuilder neutralString = new StringBuilder();
    private StringBuilder activeString = new StringBuilder();
    private int scanPointer = 0;

    private List<State> states = new ArrayList<>();
    private Map<Class<? extends State>, State> stateCache = new HashMap<>();

    public int getScanPointer() {
        return scanPointer;
    }

    public void setScanPointer(int scanPointer) {
        this.scanPointer = scanPointer;
    }

    public Stack<StackElement> getCallStack() {
        return callStack;
    }

    public StringBuilder getNeutralString() {
        return neutralString;
    }

    public StringBuilder getActiveString() {
        return activeString;
    }

    public List<State> getStates() {
        return states;
    }

    public StateMachine() {
        for (Class clz : StateBase.class.getClasses()) {
            System.out.println(clz.getName());
        }
    }

    public StackElement getCurrentStackElement() {
        return currentStackElement;
    }

    public void setCurrentStackElement(StackElement currentStackElement) {
        this.currentStackElement = currentStackElement;
    }

    public static void main(String[] args) {
        StateMachine sm = new StateMachine();
    }
}
