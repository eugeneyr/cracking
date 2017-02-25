package info.lynxnet.etudes.trac;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

public class Context {
    private Map<String, Form> formStorage = new TreeMap<>();
    private String[] commandLine;
    private Stack<StackElement> callStack = new Stack<>();
    private StringBuilder neutralString = new StringBuilder();
    private StringBuilder activeString = new StringBuilder();
    private InputStream input = System.in;
    private PrintStream output = System.out;
    private boolean exit;
    private int exitCode;

    private char metacharacter = Constants.METACHARACTER;
    private String initialActiveString = Constants.INITIAL_ACTIVE_STRING;
    private boolean trace = false;
    private String dataDir = Constants.DEFAULT_TRAC_DATA_DIR;
    private String blockSubdir = Constants.BLOCK_SUBDIR;

    public Context(String[] commandLine) {
        this.commandLine = commandLine;
    }

    public boolean isTrace() {
        return trace;
    }

    public void setTrace(boolean trace) {
        this.trace = trace;
    }

    public char getMetacharacter() {
        return metacharacter;
    }

    public void setMetacharacter(char metacharacter) {
        this.metacharacter = metacharacter;
    }

    public void setInitialActiveString(String initialActiveString) {
        this.initialActiveString = initialActiveString;
    }

    public String getInitialActiveString() {
        return initialActiveString;
    }

    public String getDataDir() {
        return dataDir;
    }

    public void setDataDir(String dataDir) {
        this.dataDir = dataDir;
    }

    public String getBlockSubdir() {
        return blockSubdir;
    }

    public void setBlockSubdir(String blockSubdir) {
        this.blockSubdir = blockSubdir;
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

    public String[] getCommandLine() {
        return commandLine;
    }

    public void setInput(InputStream newInput) {
        if (input != null && input != System.in) {
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace(System.err);
            }
        }
        if (newInput == null) {
            newInput = System.in;
        }

        input = newInput;
    }

    public void setOutput(PrintStream newOutput) {
        if (output != null && output != System.out) {
            try {
                output.close();
            } catch (Throwable e) {
                e.printStackTrace(System.err);
            }
        }
        if (newOutput == null) {
            newOutput = System.out;
        }
        output = newOutput;
    }

    public InputStream getInput() {
        return input;
    }

    public PrintStream getOutput() {
        return output;
    }

    public int getExitCode() {
        return exitCode;
    }

    public void setExitCode(int exitCode) {
        this.exitCode = exitCode;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }

    public boolean shouldExit() {
        return this.exit;
    }
}
