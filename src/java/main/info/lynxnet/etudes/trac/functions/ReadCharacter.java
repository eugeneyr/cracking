package info.lynxnet.etudes.trac.functions;

import info.lynxnet.etudes.trac.Constants;
import info.lynxnet.etudes.trac.Form;
import info.lynxnet.etudes.trac.StackElement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.stream.Stream;

public class ReadCharacter implements BuiltInFunction {
    public static final String FUNCTION_NAME = "rc";

    @Override
    public String getName() {
        return FUNCTION_NAME;
    }

    @Override
    public String execute(StackElement stackElement, Map<String, Form> formStorage) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder result = new StringBuilder();
        try {
            int character = reader.read();
            if (character >= -1) {
                result.append(Character.toChars(character));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}