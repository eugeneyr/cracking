package info.lynxnet.etudes.trac.functions;

public class ExecutionResult {
    private boolean active;
    private String value;

    public ExecutionResult(boolean active, String value) {
        this.active = active;
        this.value = value;
    }

    public boolean isActive() {
        return active;
    }

    public String getValue() {
        return value;
    }
}
