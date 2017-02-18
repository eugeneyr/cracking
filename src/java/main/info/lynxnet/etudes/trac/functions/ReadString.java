package info.lynxnet.etudes.trac.functions;

import info.lynxnet.etudes.trac.Configuration;
import info.lynxnet.etudes.trac.Constants;
import info.lynxnet.etudes.trac.Form;
import info.lynxnet.etudes.trac.StackElement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class ReadString implements BuiltInFunction {
    public static final String FUNCTION_NAME = "rs";
    @Override
    public String getName() {
        return FUNCTION_NAME;
    }

    @Override
    public String execute(StackElement stackElement, Map<String, Form> formStorage) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder result = new StringBuilder();
        String line = null;
        do {
            try {
                // System.out.print("TRACK>");
                line = reader.readLine();
                if (line != null) {
                    if (line.indexOf(Configuration.getInstance().getMetacharacter()) >= 0) {
                        result.append(line.substring(0, line.indexOf(Configuration.getInstance().getMetacharacter())));
                        break;
                    }
                    result.append(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        } while (line != null);

        return result.toString();
    }
}