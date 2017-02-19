package info.lynxnet.etudes.trac.functions;

import info.lynxnet.etudes.trac.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InitialMatch implements BuiltInFunction {
    public static final String FUNCTION_NAME = "in";

    @Override
    public String getName() {
        return FUNCTION_NAME;
    }

    @Override
    public ExecutionResult execute(StackElement stackElement, Map<String, Form> formStorage) {
        StringBuilder result = new StringBuilder();
        if (stackElement.getArguments().size() > 2) {
            Lexem nameArg = stackElement.getArguments().get(1);
            Form form = formStorage.get(nameArg.getValue());
            if (form != null) {
                List<FormElement> segmented = form.segment();
                Lexem valArg = stackElement.getArguments().get(2);

                Optional<FormElement> found = segmented.stream().filter(
                        x -> (x instanceof FormSegment)
                                && (x.getOffset() + ((FormSegment) x).getValue().length() > form.getPointer())
                                && ((FormSegment)x).getValue().contains(valArg.getValue())
                ).min((a, b) -> a.getOffset() - b.getOffset());
                if (!found.isPresent()) {
                    return new ExecutionResult(true, stackElement.getArgumentValue(3));
                }
                FormSegment foundSegment = (FormSegment) found.get();
                result.append(
                        form.getBody().substring(
                                form.getPointer(),
                                foundSegment.getOffset() + foundSegment.getValue().indexOf(valArg.getValue())));
                form.setPointer(
                        foundSegment.getOffset()
                                + foundSegment.getValue().indexOf(valArg.getValue()) + valArg.getValue().length());
            }
        }
        return new ExecutionResult(stackElement.isActive(), result.toString());
    }
}