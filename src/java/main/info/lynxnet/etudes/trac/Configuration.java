package info.lynxnet.etudes.trac;

public class Configuration {
    private char metacharacter = Constants.METACHARACTER;
    private String initialActiveString = Constants.INITIAL_ACTIVE_STRING;
    private boolean trace = false;

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

    public Configuration(String[] commandLine) {
    }

    public void setInitialActiveString(String initialActiveString) {
        this.initialActiveString = initialActiveString;
    }

    public String getInitialActiveString() {
        return initialActiveString;
    }
}
