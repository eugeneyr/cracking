package info.lynxnet.etudes.trac;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

import org.reflections.*;

public class StateMachine {
    private Stack<StackElement> callStack = new Stack<>();
    private StackElement currentStackElement;

    private StringBuilder neutralString = new StringBuilder();
    private StringBuilder activeString = new StringBuilder();
    private int scanPointer = 0;
    private InterpreterState initialState;

    private List<InterpreterState> interpreterStates = new ArrayList<>();
    private Map<Class<? extends InterpreterState>, InterpreterState> stateCache = new HashMap<>();

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

    public List<InterpreterState> getInterpreterStates() {
        return interpreterStates;
    }

    public StateMachine() {
        Reflections refs = new Reflections("info.lynxnet.etudes.trac");
        Set<Class<? extends InterpreterStateBase>> stateClasses = refs.getSubTypesOf(InterpreterStateBase.class);
        for (Class<? extends InterpreterStateBase> clz : stateClasses) {
            //System.out.println(String.format("Found InterpreterState: %s", clz.getName()));
            try {
                Constructor ctr = clz.getConstructor(StateMachine.class);
                InterpreterState state = (InterpreterState) ctr.newInstance(this);
                this.interpreterStates.add(state);
                this.stateCache.put(clz, state);
                if (state.isInitial()) {
                    this.initialState = state;
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    public int run(String[] args) {
        if (this.initialState == null) {
            System.err.println("Invalid state machine configuration: missing initial state");
            return -1;
        }
        InterpreterState state = this.initialState;
        long cycle = 0L;
        while (true) {
            Class<? extends InterpreterState> nextStateClass = state.actionAndTransition();
            System.out.println(String.format(
                    "[%d] %s: %s <---> %s", cycle,
                    state.getClass().getName(),
                    this.neutralString.toString(), this.activeString.toString()));
            if (nextStateClass == null) {
                System.err.println(String.format("State %s transitioned to a null", state.getClass().getName()));
                return -1;
            }
            InterpreterState nextState = this.stateCache.get(nextStateClass);
            if (nextState == null) {
                System.err.println(String.format("State %s was not pre-instantiated", nextStateClass.getName()));
                return -1;
            }
            state = nextState;
            cycle++;
            // temp!
            if (cycle > 64) {
                return 0;
            }
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
        int exitStatus = sm.run(args);
        System.exit(exitStatus);
    }
}
