package info.lynxnet.etudes.trac;

import java.util.*;

import info.lynxnet.etudes.trac.states.InterpreterState;
import info.lynxnet.etudes.trac.states.InterpreterStateBase;
import org.reflections.*;

public class Processor {
    private InterpreterState initialState;
    private List<InterpreterState> interpreterStates = new ArrayList<>();
    private Map<Class<? extends InterpreterState>, InterpreterState> stateCache = new HashMap<>();

    private Context context;

    public List<InterpreterState> getInterpreterStates() {
        return interpreterStates;
    }

    public Processor(String[] commandLine) {
        context = new Context(commandLine);
        loadStates();
    }

    private void loadStates() {
        Reflections refs = new Reflections("info.lynxnet.etudes.trac.states");
        Set<Class<? extends InterpreterStateBase>> stateClasses = refs.getSubTypesOf(InterpreterStateBase.class);
        for (Class<? extends InterpreterStateBase> clz : stateClasses) {
            try {
                InterpreterState state = clz.newInstance();
                this.interpreterStates.add(state);
                this.stateCache.put(clz, state);
                if (state.isInitial()) {
                    this.initialState = state;
                }
            } catch (InstantiationException e) {
                e.printStackTrace(System.out);
            } catch (IllegalAccessException e) {
                e.printStackTrace(System.out);
            }
        }
    }

    public int run() {
        if (this.initialState == null) {
            System.err.println("Invalid state machine configuration: missing initial state");
            return -1;
        }
        if (context.getOutput() == System.out && context.getInput() == System.in) {
            System.out.println("Type\n\t#(help)'\nto get the overview of all available functions.\n" +
                    "Type\n" +
                    "\t#(help,fn)'\n" +
                    "to get the detailed info on a function \"fn\".");
        }
        InterpreterState state = this.initialState;
        while (!context.shouldExit()) {
            Class<? extends InterpreterState> nextStateClass = state.actionAndTransition(context);
            if (nextStateClass == null) {
                System.err.println(String.format("FATAL: State %s transitioned to null", state.getClass().getName()));
                context.setExitCode(-1);
                context.setExit(true);
            }
            InterpreterState nextState = this.stateCache.get(nextStateClass);
            if (nextState == null) {
                System.err.println(String.format("FATAL: State %s was not pre-instantiated", nextStateClass.getName()));
                context.setExitCode(-1);
                context.setExit(true);
            }
            state = nextState;
        }
        context.setInput(System.in);
        context.setOutput(System.out);
        return context.getExitCode();
    }

    public static void main(String[] args) {
        Processor sm = new Processor(args);
        int exitStatus = sm.run();
        System.exit(exitStatus);
    }
}
