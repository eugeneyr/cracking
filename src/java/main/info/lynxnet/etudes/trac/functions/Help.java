package info.lynxnet.etudes.trac.functions;

import info.lynxnet.etudes.trac.Context;
import info.lynxnet.etudes.trac.FunctionEvaluator;
import info.lynxnet.etudes.trac.StackElement;

import java.util.HashSet;
import java.util.Set;

public class Help implements BuiltInFunction {
    public static final String FUNCTION_MNEMONICS = "help";
    public static final String FUNCTION_NAME = "Help";

    @Override
    public String getMnemonics() {
        return FUNCTION_MNEMONICS;
    }

    @Override
    public String getCategory() {
        return FunctionCategory.DEBUG;
    }

    @Override
    public String getName() {
        return FUNCTION_NAME;
    }

    @Override
    public ExecutionResult execute(StackElement stackElement, Context context) {
        StringBuilder sb = new StringBuilder();
        Set<String> cats = new HashSet<>();
        String funcName = stackElement.getArgumentValue(1);
        if (FunctionEvaluator.BUILTINS.containsKey(funcName)) {
            BuiltInFunction func = FunctionEvaluator.BUILTINS.get(funcName);
            sb.append(String.format("#(%s)\t%s [%s]\n", func.getMnemonics(), func.getName(), func.getCategory()));
        } else {
            FunctionEvaluator.BUILTINS.values().stream().sorted((a, b) -> {
                int comp = a.getCategory().compareTo(b.getCategory());
                if (comp == 0) {
                    comp = a.getMnemonics().compareTo(b.getMnemonics());
                }
                return comp;
            }).forEach(x -> {
                if (!cats.contains(x.getCategory())) {
                    sb.append("\n * ").append(x.getCategory()).append(" *\n\n");
                    cats.add(x.getCategory());
                }
                sb.append(String.format("#(%s)\t%s\n", x.getMnemonics(), x.getName()));
            });
        }
        System.out.println(sb.toString());
        return new ExecutionResult(stackElement.isActive(), "");
    }
}