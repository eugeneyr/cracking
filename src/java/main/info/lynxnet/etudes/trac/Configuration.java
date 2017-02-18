package info.lynxnet.etudes.trac;

public class Configuration {
    private char metacharacter = Constants.METACHARACTER;

    public char getMetacharacter() {
        return metacharacter;
    }

    public void setMetacharacter(char metacharacter) {
        this.metacharacter = metacharacter;
    }

    private Configuration() {
    }

    private static Configuration instance = new Configuration();

    public static Configuration getInstance() {
        return instance;
    }
}
