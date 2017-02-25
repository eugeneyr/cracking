package info.lynxnet.etudes.trac;

import info.lynxnet.etudes.trac.functions.BuiltInFunction;
import info.lynxnet.etudes.trac.functions.ExecutionResult;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class FunctionEvaluator {
    public static Map<String, BuiltInFunction> BUILTINS = new HashMap<>();

    static {
        Reflections refs = new Reflections("info.lynxnet.etudes.trac.functions");
        Set<Class<? extends BuiltInFunction>> funcClasses = refs.getSubTypesOf(BuiltInFunction.class);
        for (Class<? extends BuiltInFunction> clz : funcClasses) {
            try {
                BuiltInFunction func = clz.newInstance();
                if (BUILTINS.containsKey(func.getMnemonics())) {
                    System.err.println(
                            String.format("Warning: mnemonics collision detected for #(%s)", func.getMnemonics()));
                }
                BUILTINS.put(func.getMnemonics(), func);
            } catch (InstantiationException e) {
                e.printStackTrace(System.err);
            } catch (IllegalAccessException e) {
                e.printStackTrace(System.err);
            }
        }
    }

    private FunctionEvaluator() {
    }

    private static FunctionEvaluator instance;

    public static synchronized FunctionEvaluator getInstance() {
        if (instance == null) {
            instance = new FunctionEvaluator();
        }
        return instance;
    }

    public ExecutionResult evaluate(StackElement stackElement, Context context) {
        BuiltInFunction function = BUILTINS.get(stackElement.getArguments().get(0).getValue());
        if (function != null) {
            return function.execute(stackElement, context);
        } else {
            System.err.println(String.format("Function not found: %s", stackElement.getArguments().get(0).getValue()));
        }
        return new ExecutionResult(stackElement.isActive(), "");
    }
}
