package info.lynxnet.etudes.trac.functions;

import info.lynxnet.etudes.trac.Context;
import info.lynxnet.etudes.trac.Form;
import info.lynxnet.etudes.trac.StackElement;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;

public class FetchBlock implements BuiltInFunction {
    public static final String FUNCTION_NAME = "fb";

    @Override
    public String getName() {
        return FUNCTION_NAME;
    }

    @Override
    public ExecutionResult execute(StackElement stackElement, Context context) {
        if (stackElement.getArguments().size() > 1) {
            String fileFormName = stackElement.getArgumentValue(1);
            Form fileForm = context.getFormStorage().get(fileFormName);
            if (fileForm != null && Files.exists(FileSystems.getDefault().getPath(fileForm.getBody().toString()))) {
                try (ObjectInputStream ois = new ObjectInputStream(
                        new BufferedInputStream(
                                new FileInputStream(fileForm.getBody().toString())))) {
                    Form[] forms = (Form[]) ois.readObject();
                    for (Form form: forms) {
                        context.getFormStorage().put(form.getName(), form);
                    }
                } catch (Throwable t) {
                    t.printStackTrace(System.err);
                }
            }
        }
        return new ExecutionResult(stackElement.isActive(), "");
    }
}