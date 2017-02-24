package info.lynxnet.etudes.trac.functions;

import info.lynxnet.etudes.trac.Context;
import info.lynxnet.etudes.trac.Form;
import info.lynxnet.etudes.trac.StackElement;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;

public class AssignInput implements BuiltInFunction {
    public static final String FUNCTION_MNEMONICS = "ai";
    public static final String FUNCTION_NAME = "Assign Input";

    @Override
    public String getMnemonics() {
        return FUNCTION_MNEMONICS;
    }

    @Override
    public String getCategory() {
        return FunctionCategory.IO;
    }

    @Override
    public String getName() {
        return FUNCTION_NAME;
    }

    @Override
    public ExecutionResult execute(StackElement stackElement, Context context) {
        String fileName = stackElement.getArgumentValue(1);
        if (fileName.length() == 0) {
            context.setInput(System.in);
        } else {
            try {
                InputStream is = new BufferedInputStream(new FileInputStream(fileName));
                context.setInput(is);
            } catch (FileNotFoundException e) {
                return new ExecutionResult(true, stackElement.getArgumentValue(2));
            }
        }
        return new ExecutionResult(stackElement.isActive(), "");
    }
}