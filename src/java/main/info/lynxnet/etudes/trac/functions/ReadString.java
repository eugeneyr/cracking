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
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder result = new StringBuilder();
        String line = null;
        do {
            try {
                System.out.print("\n>>> ");
                line = reader.readLine();
                if (line != null) {
                    if (line.indexOf(context.getConfiguration().getMetacharacter()) >= 0) {
                        result.append(line.substring(0, line.indexOf(context.getConfiguration().getMetacharacter())));
                        break;
                    }
                    result.append(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        } while (line != null);

        return new ExecutionResult(stackElement.isActive(), result.toString());
    }
}