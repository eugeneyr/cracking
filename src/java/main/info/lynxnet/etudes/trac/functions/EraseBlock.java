package info.lynxnet.etudes.trac.functions;

import info.lynxnet.etudes.trac.Context;
import info.lynxnet.etudes.trac.Form;
import info.lynxnet.etudes.trac.StackElement;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;

public class EraseBlock implements BuiltInFunction {
    public static final String FUNCTION_NAME = "eb";

    @Override
    public String getName() {
        return FUNCTION_NAME;
    }

    @Override
    public ExecutionResult execute(StackElement stackElement, Context context) {
        if (stackElement.getArguments().size() > 1) {
            String fileFormName = stackElement.getArgumentValue(1);
            Form fileForm = context.getFormStorage().get(fileFormName);
            if (fileForm != null) {
                try {
                    Files.deleteIfExists(FileSystems.getDefault().getPath(fileForm.getBody().toString()));
                    context.getFormStorage().remove(fileFormName);
                } catch (IOException e) {
                    e.printStackTrace(System.err);
                }
            }
        }
        return new ExecutionResult(stackElement.isActive(), "");
    }
}