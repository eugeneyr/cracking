package info.lynxnet.etudes.trac;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StackElement {
    private boolean active;
    private List<Lexem> arguments = new ArrayList<>();

    public List<Lexem> getArguments() {
        return arguments;
    }

    public boolean isActive() {
        return active;
    }

    public StackElement(boolean active, Lexem... arguments) {
        this.active = active;
        this.arguments.addAll(Arrays.asList(arguments));
    }
}
