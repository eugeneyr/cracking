package info.lynxnet.etudes.trac;

import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

public class Context {
    Map<String, Form> formStorage = new TreeMap<>();
    Configuration configuration;
    private String[] commandLine;
    Stack<StackElement> callStack = new Stack<>();
    StringBuilder neutralString = new StringBuilder();
    StringBuilder activeString = new StringBuilder();

    public Context(String[] commandLine) {
        this.commandLine = commandLine;
        configuration = new Configuration(commandLine);
    }

    public Stack<StackElement> getCallStack() {
        return callStack;
    }

    public StringBuilder getNeutralString() {
        return neutralString;
    }

    public StringBuilder getActiveString() {
        return activeString;
    }

    public Map<String, Form> getFormStorage() {
        return formStorage;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public String[] getCommandLine() {
        return commandLine;
    }


}
