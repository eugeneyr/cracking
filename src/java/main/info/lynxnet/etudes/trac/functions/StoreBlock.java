package info.lynxnet.etudes.trac.functions;

import info.lynxnet.etudes.trac.*;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class StoreBlock implements BuiltInFunction {
    public static final String FUNCTION_MNEMONICS = "sb";
    public static final String FUNCTION_NAME = "Store Block";

    @Override
    public String getMnemonics() {
        return FUNCTION_MNEMONICS;
    }

    @Override
    public String getCategory() {
        return FunctionCategory.STORAGE;
    }

    @Override
    public String getName() {
        return FUNCTION_NAME;
    }

    @Override
    public ExecutionResult execute(StackElement stackElement, Context context) {
        if (stackElement.getArguments().size() > 2) {
            String fileFormName = stackElement.getArgumentValue(1);
            List<Form> formsToStore = new ArrayList<>();
            String blockDirPathName = String.format(
                    "%s%s%s",
                    context.getDataDir(),
                    File.separator, context.getBlockSubdir());
            Path dirPath = FileSystems.getDefault().getPath(blockDirPathName);
            try {
                dirPath = Files.createDirectories(dirPath);
                Path tempFile = Files.createTempFile(dirPath, "trc", ".blk");
                for (int i = 2; i < stackElement.getArguments().size(); i++) {
                    String formName = stackElement.getArgumentValue(i);
                    Form form = context.getFormStorage().get(formName);
                    if (form != null) {
                        formsToStore.add(form);
                    }
                }
                try (ObjectOutputStream oos = new ObjectOutputStream(
                        new BufferedOutputStream(
                                new FileOutputStream(tempFile.toFile())))) {
                    oos.writeObject(formsToStore.toArray(new Form[formsToStore.size()]));
                    Form fileForm = new Form(fileFormName, new StringBuilder(tempFile.toAbsolutePath().toString()));
                    context.getFormStorage().put(fileFormName, fileForm);
                    for (Form form: formsToStore) {
                        context.getFormStorage().remove(form.getName());
                    }
                } catch (Throwable t) {
                    t.printStackTrace(System.err);
                }

            } catch (IOException e) {
                e.printStackTrace(System.err);
            }
        }
        return new ExecutionResult(stackElement.isActive(), "");
    }
}