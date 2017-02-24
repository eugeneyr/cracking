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
        String oldNeutral = "";
        String oldActive = "";
        InterpreterState state = this.initialState;
        long cycle = 0L;
        while (true) {
            Class<? extends InterpreterState> nextStateClass = state.actionAndTransition(context);

//            if (!oldNeutral.equals(neutralString.toString()) || !oldActive.equals(activeString.toString())) {
//                System.out.println(String.format(
//                        "[%d] %s: %s <---> %s", cycle,
//                        state.getClass().getName(),
//                        neutralString.toString(), activeString.toString()));
//            }

            oldNeutral = context.getNeutralString().toString();
            oldActive = context.getActiveString().toString();
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
        }
    }

    public static void main(String[] args) {
        Processor sm = new Processor(args);
        int exitStatus = sm.run();
        System.exit(exitStatus);
    }
}
