package info.lynxnet.etudes.trac.functions;

import info.lynxnet.etudes.trac.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadString implements BuiltInFunction {
    public static final String FUNCTION_NAME = "rs";

    @Override
    public String getName() {
        return FUNCTION_NAME;
    }

    @Override
    public ExecutionResult execute(StackElement stackElement, Context context) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(context.getInput()));
        StringBuilder result = new StringBuilder();
        if (context.getInput() == System.in) {
            System.out.print("\n>>> ");
        }
        do {
            try {
                int codePoint = reader.read();
                if (codePoint >= -1) {
                    char characters[] = Character.toChars(codePoint);
                    if (characters.length > 0) {
                        if (context.getMetacharacter() == characters[0]) {
                            break;
                        }
                    }
                    result.append(Character.toChars(codePoint));
                } else {
                    // end of input stream
                    context.setInput(System.in);
                    return new ExecutionResult(true, stackElement.getArgumentValue(1));
                }
            } catch (IOException e) {
                e.printStackTrace(System.err);
                context.setInput(System.in);
                return new ExecutionResult(true, stackElement.getArgumentValue(1));
            }
        } while (true);

        return new ExecutionResult(stackElement.isActive(), result.toString());
    }
}