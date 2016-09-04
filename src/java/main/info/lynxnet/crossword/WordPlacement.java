package info.lynxnet.crossword;

public class WordPlacement {
    private String word;
    private int x;
    private int y;
    private Direction direction;

    public WordPlacement(String word, int x, int y, Direction direction) {
        this.word = word;
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public String getWord() {
        return word;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Direction getDirection() {
        return direction;
    }
}
