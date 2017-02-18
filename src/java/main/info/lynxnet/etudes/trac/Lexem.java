package info.lynxnet.etudes.trac;

public class Lexem {
    private int offset;
    private String value;
    private boolean completed;

    public Lexem(int offset, String value, boolean completed) {
        this.offset = offset;
        this.value = value;
        this.completed = completed;
    }

    public Lexem(int offset, String value) {
        this.offset = offset;
        this.value = value;
        this.completed = true;
    }

    public Lexem(int offset) {
        this.offset = offset;
        this.completed = false;
    }

    public boolean isCompleted() {
        return completed;
    }

    public int getOffset() {
        return offset;
    }

    public String getValue() {
        return value;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Lexem{");
        sb.append("offset=").append(offset);
        sb.append(", value='").append(value).append('\'');
        sb.append(", completed=").append(completed);
        sb.append('}');
        return sb.toString();
    }
}
