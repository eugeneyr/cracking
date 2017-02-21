package info.lynxnet.etudes.trac;

public class Configuration {
    private char metacharacter = Constants.METACHARACTER;
    private String initialActiveString = Constants.INITIAL_ACTIVE_STRING;

    public char getMetacharacter() {
        return metacharacter;
    }

    public void setMetacharacter(char metacharacter) {
        this.metacharacter = metacharacter;
    }

    public Configuration(String[] commandLine) {
    }

    public String getInitialActiveString() {
        return initialActiveString;
    }
}
