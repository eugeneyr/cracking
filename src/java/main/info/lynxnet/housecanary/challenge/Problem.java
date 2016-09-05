package info.lynxnet.housecanary.challenge;

public class Problem {
    private long[][] data;
    private int windowCols;
    private int windowRows;

    public Problem(long[][] data, int windowRows, int windowCols) {
        this.data = data;
        this.windowCols = windowCols;
        this.windowRows = windowRows;
    }

    public long[][] getData() {
        return data;
    }

    public void setData(long[][] data) {
        this.data = data;
    }

    public int getWindowCols() {
        return windowCols;
    }

    public int getWindowRows() {
        return windowRows;
    }
}
