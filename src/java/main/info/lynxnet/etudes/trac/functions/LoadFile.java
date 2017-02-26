package info.lynxnet.etudes.trac.functions;

import info.lynxnet.etudes.trac.Context;
import info.lynxnet.etudes.trac.StackElement;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class LoadFile implements BuiltInFunction {
    public static final String FUNCTION_MNEMONICS = "ld";
    public static final String FUNCTION_NAME = "Load File";

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
        Path path = FileSystems.getDefault().getPath(fileName);
        if (Files.exists(path)) {
            try {
                return new ExecutionResult(stackElement.isActive(), new String(Files.readAllBytes(path)));
            } catch (IOException e) {
                e.printStackTrace(System.err);
            }
        }
        return new ExecutionResult(true, stackElement.getArgumentValue(2));
    }
}