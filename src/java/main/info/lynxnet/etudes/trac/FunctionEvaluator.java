package info.lynxnet.etudes.trac;

import java.util.HashMap;
import java.util.Map;

public class FunctionEvaluator {
    private static Map<String, BuiltInFunction> BUILTINS = new HashMap<>();
    private static Map<String, Form> FORM_STORAGE = new HashMap<>();

//    static {
//        Collection<all
//        BUILTINS.
//    }

    public static String evaluate(StackElement stackElement) {
        BuiltInFunction function = BUILTINS.get(stackElement.getArguments().get(0));
        System.out.println(String.format("Executing: %s", stackElement.toString()));
        if (function != null) {
            return function.execute(stackElement, FORM_STORAGE);
        }
        return "";
    }
}
