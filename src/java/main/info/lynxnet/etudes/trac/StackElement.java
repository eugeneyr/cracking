package info.lynxnet.etudes.trac;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
        if (arguments == null || arguments.length == 0) {
            this.arguments.add(new Lexem(offset, null, false));
        } else {
            this.arguments.addAll(Arrays.asList(arguments));
        }
    }

    public Lexem completeArgument(StateMachine stateMachine) {
        if (arguments.size() == 0) {
            throw new IllegalStateException("The stack element has no arguments");
        }
        Lexem arg = arguments.get(arguments.size() - 1);
        arg.setValue(stateMachine.getNeutralString().substring(arg.getOffset()));
        arg.setCompleted(true);
        return arg;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(active ? "#(" : "##(");
        sb.append(String.join(",", arguments.stream().map(Lexem::getValue).collect(Collectors.toList())));
        sb.append(") [offset=");
        sb.append(offset).append(']');
        return sb.toString();
    }

    public String getArgumentValue(int idx) {
        if (idx >= 0 && idx < arguments.size()) {
            return arguments.get(idx).getValue();
        }
        return "";
    }

    public int getArgumentIntValue(int idx) {
        String strVal = getArgumentValue(idx);
        try {
            return Integer.parseInt(strVal);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

}
