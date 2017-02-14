package info.lynxnet.etudes.trac;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StackElement {
    private boolean active;
    private List<Lexem> arguments = new ArrayList<>();
    private int offset;

    public List<Lexem> getArguments() {
        return arguments;
    }

    public boolean isActive() {
        return active;
    }

    public int getOffset() {
        return offset;
    }

    public StackElement(boolean active, int offset, Lexem... arguments) {
        this.active = active;
        this.offset = offset;
        this.arguments.addAll(Arrays.asList(arguments));
    }
}
