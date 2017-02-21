package info.lynxnet.etudes.trac.functions;

import info.lynxnet.etudes.trac.Context;
import info.lynxnet.etudes.trac.StackElement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadCharacter implements BuiltInFunction {
    public static final String FUNCTION_NAME = "rc";

    @Override
    public String getName() {
        return FUNCTION_NAME;
    }

    @Override
    public ExecutionResult execute(StackElement stackElement, Context context) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder result = new StringBuilder();
        System.out.print("\n>> ");
        try {
            int character = reader.read();
            if (character >= -1) {
                result.append(Character.toChars(character));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ExecutionResult(stackElement.isActive(), result.toString());
    }
}